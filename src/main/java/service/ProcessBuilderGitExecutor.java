package service;

import exception.GitCommandException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * ProcessBuilder를 사용한 Git 명령 실행 구현체
 */
public class ProcessBuilderGitExecutor implements GitCommandExecutor {

    @Override
    public String execute(String workingDirectory, String... commands) throws GitCommandException {
        try {
            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.directory(new File(workingDirectory));
            pb.redirectErrorStream(true);

            Process process = pb.start();
            String output = readOutput(process);

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new GitCommandException(
                        "Git 명령 실행 실패 (exit code: " + exitCode + ")\n" +
                        "명령: " + String.join(" ", commands) + "\n" +
                        "출력: " + output
                );
            }

            return output;

        } catch (IOException e) {
            throw new GitCommandException("Git 명령 실행 중 IO 에러 발생", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new GitCommandException("Git 명령 실행이 중단되었습니다.", e);
        }
    }

    private String readOutput(Process process) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), "UTF-8"))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}

package service;

import domain.Repository;
import exception.ChangeDetectionException;
import exception.GitCommandException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Git을 사용한 변경사항 감지 구현체
 * Single Responsibility Principle 적용 - 변경사항 감지만 담당
 */
public class GitChangeDetector implements ChangeDetector {
    private final GitCommandExecutor executor;

    public GitChangeDetector(GitCommandExecutor executor) {
        this.executor = Objects.requireNonNull(executor, "GitCommandExecutor는 필수입니다.");
    }

    @Override
    public List<String> detectChanges(Repository repository, String targetBranch) {
        try {
            String diffRange = buildDiffRange(repository.getBaseBranch(), targetBranch);
            String output = executor.execute(
                    repository.getPath().toString(),
                    "git", "diff", "--name-only", diffRange
            );

            return parseOutput(output);

        } catch (GitCommandException e) {
            throw new ChangeDetectionException(
                    "변경사항 감지 실패: " + repository.getName() + " (" + targetBranch + ")",
                    e
            );
        }
    }

    private String buildDiffRange(String baseBranch, String targetBranch) {
        return baseBranch + ".." + targetBranch;
    }

    private List<String> parseOutput(String output) {
        if (output == null || output.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(output.split("\n"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }
}

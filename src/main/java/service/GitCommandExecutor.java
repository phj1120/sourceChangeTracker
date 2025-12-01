package service;

import exception.GitCommandException;

/**
 * Git 명령 실행 인터페이스
 * Dependency Inversion Principle 적용 - 추상화에 의존
 */
public interface GitCommandExecutor {
    /**
     * Git 명령을 실행하고 결과를 반환
     *
     * @param workingDirectory 작업 디렉토리
     * @param commands Git 명령어 배열
     * @return 명령 실행 결과
     * @throws GitCommandException 명령 실행 실패 시
     */
    String execute(String workingDirectory, String... commands) throws GitCommandException;
}

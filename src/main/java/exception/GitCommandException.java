package exception;

/**
 * Git 명령 실행 실패 시 발생하는 예외
 */
public class GitCommandException extends RuntimeException {
    public GitCommandException(String message) {
        super(message);
    }

    public GitCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}

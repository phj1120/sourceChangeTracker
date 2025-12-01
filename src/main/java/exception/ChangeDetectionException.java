package exception;

/**
 * 변경사항 감지 실패 시 발생하는 예외
 */
public class ChangeDetectionException extends RuntimeException {
    public ChangeDetectionException(String message) {
        super(message);
    }

    public ChangeDetectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

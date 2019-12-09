package exceptions;

public class InjectException extends RuntimeException {
    public InjectException(String message) {
        super(message);
    }

    public InjectException(Throwable cause) {
        super(cause);
    }

    public InjectException(String message, Throwable cause) {
        super(message, cause);
    }
}

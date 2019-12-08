package exceptions;

public class InjectException extends RuntimeException {
    public InjectException(String message) {
        super(message);
    }

    public InjectException(Throwable cause) {
        super(cause);
    }
}

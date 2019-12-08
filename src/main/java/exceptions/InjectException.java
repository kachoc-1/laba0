package exceptions;

public class InjectException extends Exception {
    public InjectException(String message) {
        super(message);
    }

    public InjectException(Throwable cause) {
        super(cause);
    }
}

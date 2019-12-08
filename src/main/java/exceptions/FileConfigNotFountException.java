package exceptions;

public class FileConfigNotFountException extends RuntimeException {
    public FileConfigNotFountException() {
    }

    public FileConfigNotFountException(String message) {
        super(message);
    }

    public FileConfigNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileConfigNotFountException(Throwable cause) {
        super(cause);
    }
}

package exceptions;

public class FileConfigNotFoundException extends RuntimeException {
    public FileConfigNotFoundException() {
    }

    public FileConfigNotFoundException(String message) {
        super(message);
    }

    public FileConfigNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileConfigNotFoundException(Throwable cause) {
        super(cause);
    }
}

package exception;

public class CustomizedInvalidStatus extends RuntimeException{
    public CustomizedInvalidStatus() {
    }

    public CustomizedInvalidStatus(String message) {
        super(message);
    }

    public CustomizedInvalidStatus(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizedInvalidStatus(Throwable cause) {
        super(cause);
    }

    public CustomizedInvalidStatus(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

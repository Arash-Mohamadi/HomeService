package exception;

public class CustomizedFormatOrSizeFile extends RuntimeException    {
    public CustomizedFormatOrSizeFile() {
    }

    public CustomizedFormatOrSizeFile(String message) {
        super(message);
    }

    public CustomizedFormatOrSizeFile(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizedFormatOrSizeFile(Throwable cause) {
        super(cause);
    }

    public CustomizedFormatOrSizeFile(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

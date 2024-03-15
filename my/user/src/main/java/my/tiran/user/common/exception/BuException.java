package my.tiran.user.common.exception;

import lombok.Getter;
import my.tiran.user.common.constant.ErrorType;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
public class BuException extends Exception {
    private final ErrorType code;
    public BuException(ErrorType code) {
        super();
        this.code = code;
    }
    public BuException(String message, Throwable cause, ErrorType code) {
        super(message, cause);
        this.code = code;
    }
    public BuException(String message, ErrorType code) {
        super(message);
        this.code = code;
    }
    public BuException(List<String> messages, ErrorType code) {
        super(StringUtils.join(messages, ", "));
        this.code = code;
    }
    public BuException(Throwable cause, ErrorType code) {
        super(cause);
        this.code = code;
    }
}

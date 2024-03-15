package my.tiran.user.common.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseStatus {
    SUCCESS(HttpStatus.OK, "success", "Success"),
    UN_AUTH(HttpStatus.UNAUTHORIZED, "unauthorized", ""),
    VALID(HttpStatus.BAD_REQUEST, "valid", ""),
    HANDLE_ERROR(HttpStatus.EXPECTATION_FAILED, "failed", ""),
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error");

    private final HttpStatus httpStatus;
    private final String code;
    private final String value;

    ResponseStatus(HttpStatus httpStatus, String code, String value) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.value = value;
    }
}

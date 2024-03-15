package my.tiran.ctable.common.constant;

import lombok.Getter;

@Getter
public enum ErrorType {
    E0001("E0001", "valid", "failed"),
    ;

    private final String code;
    private final String status;
    private final String message;

    ErrorType(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}

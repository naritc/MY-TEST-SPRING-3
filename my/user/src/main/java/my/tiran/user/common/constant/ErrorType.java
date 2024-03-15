package my.tiran.user.common.constant;

import lombok.Getter;

@Getter
public enum ErrorType {
    A0001("A0001", "valid", "authentication failed"),

    B0001("B0001", "valid", "existing username or email"),
    B0002("B0002", "valid", "not found username"),
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

package my.tiran.user.common.constant;


import lombok.Getter;

@Getter
public enum TokenType {
    BEARER("BEARER", "")
    ;

    private final String code;
    private final String value;

    TokenType(String code, String value) {
        this.code = code;
        this.value = value;
    }
}

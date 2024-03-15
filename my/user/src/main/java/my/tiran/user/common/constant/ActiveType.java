package my.tiran.user.common.constant;


import lombok.Getter;

@Getter
public enum ActiveType {
    ACTIVE("A", ""),
    INACTIVE("I", "")
    ;

    private final String code;
    private final String value;

    ActiveType(String code, String value) {
        this.code = code;
        this.value = value;
    }
}

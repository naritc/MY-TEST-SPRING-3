package my.tiran.user.common.constant;


import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum RoleType {
    USER("USER", "USER"),
    ADMIN("ADMIN", "ADMIN")
    ;

    private final String code;
    private final String value;

    RoleType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.value));
    }
}

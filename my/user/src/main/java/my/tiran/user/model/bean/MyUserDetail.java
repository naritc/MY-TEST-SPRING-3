package my.tiran.user.model.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import my.tiran.user.model.dto.MyUser;
import my.tiran.user.model.entity.MyUserEntity;
import my.tiran.user.model.entity.MyUserRoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetail extends MyUser implements UserDetails {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String username;
    private final String password;
    
    @Getter
    private String token;
    private final Collection<? extends GrantedAuthority> authorities;


    public MyUserDetail(MyUserEntity en) {
        List<GrantedAuthority> auths = new ArrayList<>();

        for(MyUserRoleEntity role : en.getMyRoles()){
            auths.add(new SimpleGrantedAuthority(role.getMyRole().getRoleType().getCode()));
        }
        this.username = en.getUsername();
        this.password = en.getPassword();
        this.firstName = en.getFirstName();
        this.lastName = en.getLastName();
        this.email = en.getEmail();
        if (!en.getTokens().isEmpty()) {
            this.token = en.getTokens().getFirst().getToken();
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

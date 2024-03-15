package my.tiran.user.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.*;
import my.tiran.user.common.constant.ActiveType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "my_user")
public class MyUserEntity {
    @Id
    @Column(name = "my_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myUserId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;

    @Enumerated(EnumType.STRING)
    @Column(name = "active")
    private ActiveType active;

    @OneToMany(mappedBy = "myUser")
    private Set<MyUserRoleEntity> myRoles;

    @OneToMany(mappedBy = "myUser")
    private List<TokenEntity> tokens;
}

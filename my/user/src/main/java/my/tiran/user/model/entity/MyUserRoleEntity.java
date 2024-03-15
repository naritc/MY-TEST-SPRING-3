package my.tiran.user.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "my_user_role",
    uniqueConstraints={ @UniqueConstraint(columnNames = {"myUserId", "roleId"} ) }
)
public class MyUserRoleEntity {
    @Id
    @Column(name = "my_user_role_id")
    @GeneratedValue
    private Long myUserRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public MyUserEntity myUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public RoleEntity myRole;

    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;
}

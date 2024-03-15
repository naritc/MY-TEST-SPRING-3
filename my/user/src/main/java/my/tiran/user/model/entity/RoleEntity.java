package my.tiran.user.model.entity;

import jakarta.persistence.*;
import lombok.*;
import my.tiran.user.common.constant.RoleType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "role_id")
    @GeneratedValue
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;
}

package my.tiran.user.repository;

import my.tiran.user.common.constant.RoleType;
import my.tiran.user.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRoleType(RoleType roleType);
}

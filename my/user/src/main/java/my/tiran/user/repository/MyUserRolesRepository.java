package my.tiran.user.repository;

import my.tiran.user.model.entity.MyUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRolesRepository extends JpaRepository<MyUserRoleEntity, Long> {
}

package my.tiran.user.repository;

import java.util.List;
import java.util.Optional;
import my.tiran.user.common.constant.ActiveType;
import my.tiran.user.model.entity.MyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUserEntity, Long> {
    Optional<MyUserEntity> findByUsernameOrEmail(String username, String email);
    Optional<MyUserEntity> findByUsername(String username);
    List<MyUserEntity> findAllByActive(ActiveType activeType);

    @Query("SELECT en " +
            "FROM MyUserEntity en " +
            "JOIN en.tokens to " +
            "WHERE en.username = :username " +
            "AND en.active = 'A' " +
            "AND (to.expired = false OR to.revoked = false)")
    Optional<MyUserEntity> findByUsernameAndActive(String username);
    
}

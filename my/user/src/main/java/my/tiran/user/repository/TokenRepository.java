package my.tiran.user.repository;

import my.tiran.user.model.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    @Query("SELECT en FROM TokenEntity en WHERE en.myUser.username = :myUserId")
    List<TokenEntity> findAllValidTokenByUser(Long myUserId);

    Optional<TokenEntity> findByToken(String token);

}

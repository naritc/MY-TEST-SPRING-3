package my.tiran.ctable.repository;

import my.tiran.ctable.model.entity.ReserveDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReserveDetailRepository extends JpaRepository<ReserveDetailEntity, Long> {
    @Query(
            "SELECT en FROM ReserveDetailEntity en " +
                    "WHERE en.reserveDateTime >= :startOfDay AND en.reserveDateTime < :endOfDay " +
                    "ORDER BY en.reserveDateTime, en.exitDateTime"
    )
    List<ReserveDetailEntity> findAllByReserveDateTime(LocalDateTime startOfDay, LocalDateTime endOfDay);
}

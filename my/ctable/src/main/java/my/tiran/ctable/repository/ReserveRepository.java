package my.tiran.ctable.repository;

import my.tiran.ctable.model.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {

}

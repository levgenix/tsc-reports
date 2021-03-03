package su.tsc.reports.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import su.tsc.reports.backend.entity.Unit;

import javax.transaction.Transactional;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Transactional
    @Modifying
    @Query(value = "TRUNCATE TABLE unit", nativeQuery = true)
    void cleanTable();
}

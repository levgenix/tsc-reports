package su.tsc.reports.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import su.tsc.reports.backend.entity.Unit;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Transactional
    @Modifying
    @Query(value = "TRUNCATE TABLE unit", nativeQuery = true)
    void cleanTable();

    @Query("SELECT u from Unit u WHERE LOWER(u.serial) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Unit> search(@Param("searchTerm") String searchTerm);
}

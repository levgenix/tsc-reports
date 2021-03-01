package su.tsc.reports.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.tsc.reports.backend.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}

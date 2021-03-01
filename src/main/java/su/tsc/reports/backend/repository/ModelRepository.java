package su.tsc.reports.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.tsc.reports.backend.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}

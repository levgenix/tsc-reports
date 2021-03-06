package su.tsc.reports.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.tsc.reports.backend.entity.Emploee;

import java.util.List;

public interface EmploeeRepository extends JpaRepository<Emploee, Long> {
    List<Emploee> findByName(String name);
}

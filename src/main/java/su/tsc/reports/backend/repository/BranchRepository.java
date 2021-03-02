package su.tsc.reports.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.tsc.reports.backend.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}

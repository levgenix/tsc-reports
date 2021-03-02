package su.tsc.reports.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.tsc.reports.backend.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}

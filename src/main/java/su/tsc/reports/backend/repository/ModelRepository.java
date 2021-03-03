package su.tsc.reports.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.tsc.reports.backend.entity.Model;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
//    @Query("SELECT m FROM Model m WHERE m.name = :name")
//    List<Model> findByName(@Param("name") String name);

    List<Model> findByName(String name);
}

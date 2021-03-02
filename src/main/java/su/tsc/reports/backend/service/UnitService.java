package su.tsc.reports.backend.service;

import org.springframework.stereotype.Service;
import su.tsc.reports.backend.entity.Unit;
import su.tsc.reports.backend.repository.ModelRepository;
import su.tsc.reports.backend.repository.UnitRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UnitService {
    private static final Logger LOGGER = Logger.getLogger(UnitService.class.getName());
    private UnitRepository unitRepository;
    private ModelRepository modelRepository;
//    private CompanyRepository companyRepository;

    public UnitService(UnitRepository unitRepository, ModelRepository modelRepository) {
        this.unitRepository = unitRepository;
        this.modelRepository = modelRepository;
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    public long count() {
        return unitRepository.count();
    }

    public void delete(Unit unit) {
        unitRepository.delete(unit);
    }

    public void save(Unit unit) {
        if (unit == null) {
            LOGGER.log(Level.SEVERE, "Unit is null. Are you sure you have connected your form to the application?");
            return;
        }
        unitRepository.save(unit);
    }

}

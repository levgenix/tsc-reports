package su.tsc.reports.backend.service;

import org.springframework.stereotype.Service;
import su.tsc.reports.backend.entity.Model;
import su.tsc.reports.backend.entity.Unit;
import su.tsc.reports.backend.repository.ModelRepository;
import su.tsc.reports.backend.repository.UnitRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UnitService {
    private static final Logger LOGGER = Logger.getLogger(UnitService.class.getName());
    private UnitRepository unitRepository;
    private ModelRepository modelRepository;

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

	@PostConstruct
	public void populateTestData() {
		if (modelRepository.count() == 0) {
			modelRepository.saveAll(
				Stream.of("Model 1", "Model 2", "Model 3")
				.map(Model::new)
				.collect(Collectors.toList()));
		}

		if (unitRepository.count() == 0) {
			Random r = new Random(0);
			List<Model> models = modelRepository.findAll();
			/*unitRepository.saveAll(
					Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
							"Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
							"Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson",
							"Kelly Gustavsson",
							"Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
							"Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
							"Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
							"Jaydan Jackson", "Bernard Nilsen")
							.map(name -> {
										String[] split = name.split(" ");
										Unit unit = new Unit();
										unit.setSerial(split[0]);
										unit.setModel(models.get(r.nextInt(models.size())));
										return unit;
									}
							).collect(Collectors.toList())
			);*/

			List<String> serials = new ArrayList<>();
			for (int i = 0; i<100; i++) {
				serials.add(UUID.randomUUID().toString());
			}
			unitRepository.saveAll(
					serials.stream()
							.map(serial -> {
										Unit unit = new Unit();
										unit.setSerial(serial);
										unit.setModel(models.get(r.nextInt(models.size())));
										return unit;
									}
							)
							.collect(Collectors.toList())
			);
		}
	}
}

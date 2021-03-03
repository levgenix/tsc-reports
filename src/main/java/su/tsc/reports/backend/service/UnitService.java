package su.tsc.reports.backend.service;

import org.apache.commons.io.FileExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.tsc.reports.backend.entity.*;
import su.tsc.reports.backend.repository.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
@Transactional
@PropertySource("classpath:values.properties")
public class UnitService {
    private static final Logger LOGGER = Logger.getLogger(UnitService.class.getName());
    private UnitRepository unitRepository;
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private BranchRepository branchRepository;
    private EmploeeRepository emploeeRepository;
    private CompanyRepository companyRepository;

    @Value("${file.input}")
    private String fileInput;

    @Value("${file.count_rows_batching}")
    private int countRowsBatching;

    public UnitService(UnitRepository unitRepository, ModelRepository modelRepository,
                       BrandRepository brandRepository, BranchRepository branchRepository,
                       EmploeeRepository emploeeRepository, CompanyRepository companyRepository) {
        this.unitRepository = unitRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.branchRepository = branchRepository;
        this.emploeeRepository = emploeeRepository;
        this.companyRepository = companyRepository;
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
    private void loadDataFromCSVFile() {
        try {
            Path path = getPathToFile(fileInput);

            List<Unit> batchUnits = new ArrayList<>();
            try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_16)) {
                unitRepository.cleanTable();

                lines.skip(1)
                        .forEach(line -> {
                            Unit unit = createUnitFromLine(line);
                            batchUnits.add(unit);

                            if (batchUnits.size() >= countRowsBatching) {
                                saveBatchUnits(batchUnits);
                                batchUnits.clear();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                saveBatchUnits(batchUnits);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Path getPathToFile(String fileInput) throws URISyntaxException, NullPointerException, FileExistsException {
        Path path = null;
        try {
            path = Paths.get(getClass().getClassLoader().getResource(fileInput).toURI());
            Files.exists(path);
        } catch (URISyntaxException e) {
            throw e;
        } catch (NullPointerException e) {
            throw new FileExistsException(path.toFile());
        }
        return path;
    }

    /**
     * @param line tab-separated String
     * First line in file in header
     * Structure of line:
     * Серийный номер
     * Модель
     * Бренд
     * Филиал ответств
     * Куратор
     * Отв.за сервис
     * Владелец техники
     * Дата отгрузки
     * Счетчик
     * Дата счетчика
     * Покупатель услуг
     * Дата последнего ТО
     * Кол ТО план
     * Кол ТО факт
     * Обслуживание
     * Гарантия
     * Контракт
     * Звонки
     * Выезды
     * ЗнР
     * (~blank field~)
     * Письмо-уведомление о снятии с гарантии
     * ВЛ
     * Причина отсутствия контракта/обслуживания/обновления счётчика
     *
     * @return object Unit
     */
    private Unit createUnitFromLine(String line) {
        String[] tokens = line.split("\\t");
        Unit unit = new Unit();

        unit.setSerial(tokens[0]);
        unit.setModel(modelRepository.findByName(tokens[1]).stream().findFirst()
                .orElseGet(() -> modelRepository.save(new Model(tokens[1]))));
        unit.setBrand(brandRepository.findByName(tokens[2]).stream().findFirst()
                .orElseGet(() -> brandRepository.save(new Brand(tokens[2]))));
        unit.setBranch(branchRepository.findByName(tokens[3]).stream().findFirst()
                .orElseGet(() -> branchRepository.save(new Branch(tokens[3]))));
        unit.setCurator(emploeeRepository.findByName(tokens[4]).stream().findFirst()
                .orElseGet(() -> emploeeRepository.save(new Emploee(tokens[4]))));
        unit.setResponsible(emploeeRepository.findByName(tokens[5]).stream().findFirst()
                .orElseGet(() -> emploeeRepository.save(new Emploee(tokens[5]))));
        unit.setOwner(companyRepository.findByName(tokens[6]).stream().findFirst()
                .orElseGet(() -> companyRepository.save(new Company(tokens[6]))));

        try {
            unit.setShipmentDate(new SimpleDateFormat("yyyy-MM-dd").parse(tokens[7]));
        } catch (ParseException e) {
            unit.setShipmentDate(null);
        }

        Date dateFixing;
        try {
            dateFixing = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[9]);
        } catch (ParseException e) {
            dateFixing = null;
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat format = new DecimalFormat("0.#");
        format.setDecimalFormatSymbols(symbols);
        float counterLastValue = 0;
        try {
            counterLastValue = tokens[8].isEmpty() ? 0 : format.parse(tokens[8]).floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        unit.setCounter(new Counter(dateFixing, counterLastValue));

        if (!tokens[10].isEmpty()) {
            unit.setBuyer(companyRepository.findByName(tokens[10]).stream().findFirst()
                    .orElseGet(() -> companyRepository.save(new Company(tokens[10]))));
        }

        Date lastServiceDate;
        try {
            lastServiceDate = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[11]);
        } catch (ParseException e) {
            lastServiceDate = null;
        }
        unit.setServiceData(new ServiceData(lastServiceDate, tokens[12].isEmpty() ? 0 : Integer.parseInt(tokens[12]),
                tokens[13].isEmpty() ? 0 : Integer.parseInt(tokens[13]), "Да".equals(tokens[13])));

        unit.setGuarantee(new Guarantee("Да".equals(tokens[15]), tokens[21]));
        unit.setHasContract("Да".equals(tokens[16]));
        unit.setConnection(new Connection(tokens[17].isEmpty() ? 0 : Integer.parseInt(tokens[17]),
                tokens[18].isEmpty() ? 0 : Integer.parseInt(tokens[18])));
        unit.setRepair(new Repair(tokens[19].isEmpty() ? 0 : Integer.parseInt(tokens[19]), tokens[20]));
        unit.setReason(new Reason(null, null, tokens[23]));

        return unit;
    }

    private void saveBatchUnits(List<Unit> units) {
        unitRepository.saveAll(units);
    }
}

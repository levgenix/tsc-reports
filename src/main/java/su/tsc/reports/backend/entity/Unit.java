package su.tsc.reports.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
@Table(name = "unit")
//@Table(name = "unit", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_1" , "id_2"})}, indexes = {})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Unit extends AbstractEntity implements Cloneable {
    @NotNull
    @NotEmpty
    private String serial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    // филиал
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curator_id", nullable = true)
    private Emploee curator;

    // ответственный
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsible_id", nullable = true)
    private Emploee responsible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Company owner;

    @Column(name = "shipment_date")
    private OffsetDateTime shipmentDate;

    @Embedded
    private Counter counter;

    // Плательщик
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "buyer_id")
    private Company buyer;

    @Embedded
    private ServiceData serviceData;

    @Embedded
    private Guarantee guarantee;

    @Column(name = "has_contract")
    private boolean hasContract;

    @Embedded
    private Connection connection;

    @Embedded
    private Repair repair;

    @Embedded
    private Reason reason;

    public Unit() {

    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Emploee getCurator() {
        return curator;
    }

    public void setCurator(Emploee curator) {
        this.curator = curator;
    }

    public Emploee getResponsible() {
        return responsible;
    }

    public void setResponsible(Emploee responsible) {
        this.responsible = responsible;
    }

    public Company getOwner() {
        return owner;
    }

    public void setOwner(Company owner) {
        this.owner = owner;
    }

    public OffsetDateTime getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(OffsetDateTime date) {
        this.shipmentDate = date;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public Company getBuyer() {
        return buyer;
    }

    public void setBuyer(Company buyer) {
        this.buyer = buyer;
    }

    public ServiceData getServiceData() {
        return serviceData;
    }

    public void setServiceData(ServiceData serviceData) {
        this.serviceData = serviceData;
    }

    public Guarantee getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Guarantee guarantee) {
        this.guarantee = guarantee;
    }

    public boolean hasContract() {
        return hasContract;
    }

    public void setContract(boolean hasContract) {
        this.hasContract = hasContract;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }
}
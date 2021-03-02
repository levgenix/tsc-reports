package su.tsc.reports.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "unit")
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
    @JoinColumn(name = "curator_id")
    private Emploee curator;

    // ответственный
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsible_id")
    private Emploee responsible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Company owner;

    @Column(name = "shipment_date")
    private Date shipmentDate;

    @Embedded
    private Counter counter;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public Unit(String serial, Model model, Brand brand, Branch branch, Emploee curator,
                Emploee responsible, Company owner, Date shipmentDate, Counter counter,
                Company buyer, ServiceData serviceData, Guarantee guarantee, boolean hasContract,
                Connection connection, Repair repair, Reason reason) {
        this.serial = serial;
        this.model = model;
        this.brand = brand;
        this.branch = branch;
        this.curator = curator;
        this.responsible = responsible;
        this.owner = owner;
        this.shipmentDate = shipmentDate;
        this.counter = counter;
        this.buyer = buyer;
        this.serviceData = serviceData;
        this.guarantee = guarantee;
        this.hasContract = hasContract;
        this.connection = connection;
        this.repair = repair;
        this.reason = reason;
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

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
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

    public boolean isHasContract() {
        return hasContract;
    }

    public void setHasContract(boolean hasContract) {
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
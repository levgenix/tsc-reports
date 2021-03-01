package su.tsc.reports.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "unit")
public class Unit extends AbstractEntity implements Cloneable {
    @NotNull
    @NotEmpty
    private String serial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    public Model getModel() {
        return model;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setModel(Model model) {
        this.model = model;
    }
/*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Brand brand;

    // филиал
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
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

    @Column("shipment_date")
    private Date shipmentDate;

    @Embedded
    private Counter counter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "byer_id")
    private Company byer;

    @Embedded
    private ServiceData serviceData;

    @Embedded
    private Guarantee guarantee;

    @Column("has_contract")
    private boolean hasContract;

    @Embedded
    private Connection connection;

    @Embedded
    private Repair repair;

    @Embedded
    private Reason reason;
*/

    public Unit() {

    }

/*    public Unit(String serial, Model model, Brand brand, Branch branch, Emploee curator, Emploee responsible, Company owner, Date shipmentDate, Counter counter, Company byer, ServiceData serviceData, Guarantee guarantee, boolean hasContract, Connection connection, Repair repair, Reason reason) {

        this.serial = serial;
        this.model = model;
        this.brand = brand;
        this.branch = branch;
        this.curator = curator;
        this.responsible = responsible;
        this.owner = owner;
        this.shipmentDate = shipmentDate;
        this.counter = counter;
        this.byer = byer;
        this.serviceData = serviceData;
        this.guarantee = guarantee;
        this.hasContract = hasContract;
        this.connection = connection;
        this.repair = repair;
        this.reason = reason;
    }*/
}
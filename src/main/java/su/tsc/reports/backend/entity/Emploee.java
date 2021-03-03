package su.tsc.reports.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emploee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Emploee extends AbstractEntity implements Cloneable {
    @NotNull
    @NotEmpty
    @Column(unique = true, length = 1400)
    private String name;

    public Emploee() {

    }

    public Emploee(@NotNull @NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

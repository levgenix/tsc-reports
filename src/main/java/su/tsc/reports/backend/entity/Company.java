package su.tsc.reports.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Company extends AbstractEntity implements Cloneable {
    @Column(unique = true)
    private String name;

    public Company() {

    }

    public Company(@NotNull @NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
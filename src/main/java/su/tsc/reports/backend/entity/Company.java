package su.tsc.reports.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company")
public class Company extends AbstractEntity implements Cloneable {
    @NotNull
    @NotEmpty
    private String name;

    public Company() {

    }

    public Company(@NotNull @NotEmpty String name) {
        this.name = name;
    }

    // todo
    public String getName() {
        return name;
    }

    // todo
    public void setName(String name) {
        this.name = name;
    }
}
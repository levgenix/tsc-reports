package su.tsc.reports.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@javax.persistence.Table(name = "model")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Model extends AbstractEntity implements Cloneable {
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String name;

    public Model() {

    }

    public Model(@NotNull @NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Model { " + "id=" + id + " name=" + name + " }";
    }
}

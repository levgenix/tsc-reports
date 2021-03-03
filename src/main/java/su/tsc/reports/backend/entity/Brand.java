package su.tsc.reports.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brand")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Brand extends AbstractEntity implements Cloneable {
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String name;

    public Brand() {

    }

    public Brand(@NotNull @NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

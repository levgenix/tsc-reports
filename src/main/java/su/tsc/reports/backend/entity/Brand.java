package su.tsc.reports.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brand")
public class Brand extends AbstractEntity implements Cloneable {
    @NotNull
    @NotEmpty
    private String name;

    public Brand() {

    }

    public Brand(@NotNull @NotEmpty String name) {
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

package su.tsc.reports.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// ЗнР
@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Repair {
    // количество ЗнР
    @Column(name = "repairs_count")
    private int repairsCount;

    // ссылка на отчет ЗнР по технике
    @Column(name = "repairs_link", length = 1400)
    private String repairsLink;

    public Repair() {

    }

    public Repair(int count, String link) {
        repairsCount = count;
        repairsLink = link;
    }
    public int getRepairsCount() {
        return repairsCount;
    }

    public void setRepairsCount(int repairsCount) {
        this.repairsCount = repairsCount;
    }

    public String getRepairsLink() {
        return repairsLink;
    }

    public void setRepairsLink(String repairsLink) {
        this.repairsLink = repairsLink;
    }
}

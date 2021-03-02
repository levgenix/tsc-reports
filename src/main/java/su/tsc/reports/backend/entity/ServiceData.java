package su.tsc.reports.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

// Сведения по обслуживанию
@Embeddable
public class ServiceData {
    // Дата последнего ТО
    @Column(name = "last_date_service")
    private Date lastDateService;

    // Кол ТО план
    @Column(name = "plan_service_count")
    private int planServiceCount;

    // Кол ТО факт
    @Column(name = "fact_service_count")
    private int factServiceCount;

    // на обслуживании?
    @Column(name = "is_served")
    private boolean isServed;

    public ServiceData() {
    }

    public ServiceData(Date dateService, int planCount, int factCount, boolean isServed) {
        this.lastDateService = dateService;
        this.planServiceCount = planCount;
        this.factServiceCount = factCount;
        this.isServed = isServed;
    }

    public Date getLastDateService() {
        return lastDateService;
    }

    public void setLastDateService(Date lastDateService) {
        this.lastDateService = lastDateService;
    }

    public int getPlanServiceCount() {
        return planServiceCount;
    }

    public void setPlanServiceCount(int planServiceCount) {
        this.planServiceCount = planServiceCount;
    }

    public int getFactServiceCount() {
        return factServiceCount;
    }

    public void setFactServiceCount(int factServiceCount) {
        this.factServiceCount = factServiceCount;
    }

    public boolean isServed() {
        return isServed;
    }

    public void setServed(boolean served) {
        isServed = served;
    }
}

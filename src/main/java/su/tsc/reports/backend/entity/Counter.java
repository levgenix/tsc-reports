package su.tsc.reports.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.OffsetDateTime;

// Счетчик
@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Counter {

    @Column(name = "counter_date_fixing")
    private OffsetDateTime dateFixing;

    @Column(name = "counter_last_value", nullable = true)
    private float lastValue;

    public Counter() {

    }

    public Counter(OffsetDateTime date, float value) {
        dateFixing = date;
        lastValue = value;
    }

    public OffsetDateTime getDateFixing() {
        return dateFixing;
    }

    public void setDateFixing(OffsetDateTime dateFixing) {
        this.dateFixing = dateFixing;
    }

    public float getLastValue() {
        return lastValue;
    }

    public void setLastValue(float lastValue) {
        this.lastValue = lastValue;
    }
}

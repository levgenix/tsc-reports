package su.tsc.reports.backend.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

// Счетчик
@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Counter {

    @Column(name = "counter_date_fixing")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateFixing;

    @Column(name = "counter_last_value", nullable = true)
    private float lastValue;

    public Counter() {

    }

    public Counter(Date date, float value) {
        dateFixing = date;
        lastValue = value;
    }

    public Date getDateFixing() {
        return dateFixing;
    }

    public void setDateFixing(Date dateFixing) {
        this.dateFixing = dateFixing;
    }

    public float getLastValue() {
        return lastValue;
    }

    public void setLastValue(float lastValue) {
        this.lastValue = lastValue;
    }
}

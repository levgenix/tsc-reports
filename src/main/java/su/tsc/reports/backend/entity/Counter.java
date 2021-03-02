package su.tsc.reports.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Counter {
    @Column(name = "date_fixing")
    private Date dateFixing;

    @Column(name = "last_value")
    private int lastValue;

    public Counter() {

    }

    public Counter(Date date, int value) {
        dateFixing = date;
        lastValue = value;
    }
}

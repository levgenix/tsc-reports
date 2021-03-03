package su.tsc.reports.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// Взаимодействие
@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Connection {
    // Звонки
    @Column(name = "calls_count")
    private int callsCount;

    // Выезды
    @Column(name = "departures_count")
    private int departuresCount;

    public Connection() {

    }

    public Connection(int calls, int departures) {
        callsCount = calls;
        departuresCount = departures;
    }

    public int getCallsCount() {
        return callsCount;
    }

    public void setCallsCount(int callsCount) {
        this.callsCount = callsCount;
    }

    public int getDeparturesCount() {
        return departuresCount;
    }

    public void setDeparturesCount(int departuresCount) {
        this.departuresCount = departuresCount;
    }
}

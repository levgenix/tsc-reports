package su.tsc.reports.backend.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

// Причина отсутствия контракта/обслуживания/обновления счётчика
@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Reason {
    // Дата причины
    @Column(name = "date_reason", nullable = true)
    private OffsetDateTime date;

    // Автор причины
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reason_author_id", nullable = true)
    private Emploee author;

    // Сообщение
    @Column(name = "reason_message", nullable = true, length = 1400)
    private String message;

    public Reason() {

    }

    public Reason(OffsetDateTime dateReason, Emploee author, String reasonMessage) {
        date = dateReason;
        this.author = author;
        message = reasonMessage;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public Emploee getAuthor() {
        return author;
    }

    public void setAuthor(Emploee author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

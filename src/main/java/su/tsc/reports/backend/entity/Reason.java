package su.tsc.reports.backend.entity;

import javax.persistence.*;
import java.util.Date;

// Причина отсутствия контракта/обслуживания/обновления счётчика
@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Reason {
    // Дата причины
    @Column(name = "date_reason", nullable = true)
    private Date date;

    // Автор причины
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reason_author_id", nullable = true)
    private Emploee author;

    // Сообщение
    @Column(name = "reason_message", nullable = true, length = 1400)
    private String message;

    public Reason() {

    }

    public Reason(Date dateReason, Emploee author, String reasonMessage) {
        date = dateReason;
        this.author = author;
        message = reasonMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

package su.tsc.reports.backend.entity;

import javax.persistence.*;
import java.util.Date;

// Причина отсутствия контракта/обслуживания/обновления счётчика
@Embeddable
public class Reason {
    // Дата причины
    @Column(name = "date_reason")
    private Date date;

    // Автор причины
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reason_author_id")
    private Emploee author;

    // Сообщение
    @Column(name = "reason_message")
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

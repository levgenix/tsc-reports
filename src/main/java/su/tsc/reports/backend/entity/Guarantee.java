package su.tsc.reports.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Guarantee {
    // на гарантии?
    @Column(name = "on_guarantee", nullable = false)
    private boolean onGuarantee;

    // ссылка на Письмо-уведомление о снятии с гарантии
    @Column(name = "guarantee_letter", length = 1400)
    private String guaranteeLetter;

    public Guarantee() {

    }

    public Guarantee(boolean onGuarantee, String guaranteeLetter) {
        this.onGuarantee = onGuarantee;
        this.guaranteeLetter = guaranteeLetter;
    }

    public boolean isGuarantee() {
        return onGuarantee;
    }

    public void setGuarantee(boolean onGuarantee) {
        this.onGuarantee = onGuarantee;
    }

    public String getGuaranteeLetter() {
        return guaranteeLetter;
    }

    public void setGuaranteeLetter(String guaranteeLetter) {
        this.guaranteeLetter = guaranteeLetter;
    }
}

package su.tsc.reports.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Guarantee {
    // onGuarantee
    @Column(name = "on_guarantee")
    private boolean onGuarantee;

    // documentLink на Письмо-уведомление о снятии с гарантии
    @Column(name = "link_to_letter")
    private String linkToLetter;

    public Guarantee() {

    }

    public Guarantee(boolean onGuarantee, String guaranteeLink) {
        this.onGuarantee = onGuarantee;
        linkToLetter = guaranteeLink;
    }

    public boolean isOnGuarantee() {
        return onGuarantee;
    }

    public void setOnGuarantee(boolean onGuarantee) {
        this.onGuarantee = onGuarantee;
    }

    public String getLinkToLetter() {
        return linkToLetter;
    }

    public void setLinkToLetter(String linkToLetter) {
        this.linkToLetter = linkToLetter;
    }
}

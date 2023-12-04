package lab.web.lab04.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Logger;

@Getter
@Setter
@Entity
@Table(name = "partners_banks")
@NamedQueries({
        @NamedQuery(name = "PartnersBank.findAll", query = "select p from PartnersBank p"),
        @NamedQuery(name = "PartnersBank.findForPartner", query = "select p from PartnersBank p where p.partner = :partnerId")
})
public class PartnersBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    @NotNull
    @Lob
    @Column(name = "iban", nullable = false)
    private String iban;

    @Column(name = "actual")
    private Boolean actual;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    public void setBank(Bank bank) {
        Logger.getLogger("BANKPART").warning("Setting bank: " + bank);
        this.bank = bank;
    }


}
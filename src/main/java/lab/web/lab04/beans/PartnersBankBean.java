package lab.web.lab04.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lab.web.lab04.dao.PartnersBankDao;
import lab.web.lab04.data.Bank;
import lab.web.lab04.data.Partner;
import lab.web.lab04.data.PartnersBank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class PartnersBankBean implements Serializable {

    @Getter
    @Setter
    private PartnersBank partnersBank = new PartnersBank();

    @Getter
    @Setter
    private Partner selectedPartner;

    @Getter
    @Setter
    private Bank selectedBank;

    @EJB
    private PartnersBankDao db;

    public List<PartnersBank> getList() {
        return db.listAll();
    }

    public void create() {
        Logger.getLogger("PB").warning("B_id: "+partnersBank.getIban());
        Logger.getLogger("PAR").warning("SelPar: "+selectedPartner.getName());
        db.create(partnersBank);
        partnersBank = new PartnersBank();
    }

    public void delete(Long id) {
        db.delete(id);
    }

}

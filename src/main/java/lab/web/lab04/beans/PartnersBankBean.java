package lab.web.lab04.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lab.web.lab04.dao.BankDao;
import lab.web.lab04.dao.PartnerDao;
import lab.web.lab04.dao.PartnersBankDao;
import lab.web.lab04.data.Bank;
import lab.web.lab04.data.Partner;
import lab.web.lab04.data.PartnersBank;
import lab.web.lab04.dto.PartnersBankDto;
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
    private PartnersBankDto pbDTO = new PartnersBankDto();

    @Getter
    @Setter
    private Partner selectedPartner;

    @Getter
    @Setter
    private Bank selectedBank;

    @EJB
    private PartnersBankDao db;
    @EJB
    private BankDao bankDAO;
    @EJB
    private PartnerDao partnerDAO;

    public List<PartnersBank> getList() {
        return db.listAll();
    }

    public void __create() {
        Logger.getLogger("PB").warning("B_id: "+partnersBank.getIban());
        Logger.getLogger("PAR").warning("SelPar: "+selectedPartner.getName());
        db.create(partnersBank);
        partnersBank = new PartnersBank();
    }

    public void create() {
        Bank bank;
        if (pbDTO.getNew_bank_name() != null && pbDTO.getBank_id() == 0) {
            bank = new Bank();
            bank.setName(pbDTO.getNew_bank_name());
            bankDAO.create(bank);
        } else {
            bank = bankDAO.find(pbDTO.getBank_id());
        }
        Partner partner = partnerDAO.find(pbDTO.getPartner_id());
        partnersBank.setPartner(partner);
        partnersBank.setBank(bank);
        db.create(partnersBank);
        partnersBank = new PartnersBank();
        pbDTO = new PartnersBankDto();
//        return "bank_account_list";
    }

    public void delete(Long id) {
        db.delete(id);
    }

}

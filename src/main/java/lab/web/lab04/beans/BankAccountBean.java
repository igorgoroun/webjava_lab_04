package lab.web.lab04.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lab.web.lab04.data.BankAccount;
import lab.web.lab04.dao.BankAccountDao;
import lab.web.lab04.data.Partner;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BankAccountBean implements Serializable {

    @Getter
    @Setter
    private BankAccount bank = new BankAccount();

    @EJB
    private BankAccountDao db;

    public List<BankAccount> getListForPartner(int partner_id) {
        return db.listForPartner(partner_id);
    }

    public List<BankAccount> getList() {
        return db.listAll();
    }

    public void create() {
        db.create(bank);
        bank = new BankAccount();
    }

    public void delete(int id) {
        db.delete(id);
    }

}

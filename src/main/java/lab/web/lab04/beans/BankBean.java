package lab.web.lab04.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lab.web.lab04.dao.BankDao;
import lab.web.lab04.data.Bank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BankBean implements Serializable {

    @Getter
    @Setter
    private Bank bank = new Bank();

    @EJB
    private BankDao db;

    public List<Bank> getList() {
        return db.listAll();
    }

    public void addRecord() {
        db.create(bank);
        bank = new Bank();
    }

    public void delete(int id) {
        System.out.println("Run delete bank");
        db.delete(id);
    }

}

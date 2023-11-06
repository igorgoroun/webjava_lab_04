package lab.web.lab04.beans;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Console;
import java.io.Serializable;
import java.util.List;
import lab.web.lab04.data.Partner;
import lab.web.lab04.dao.PartnerDao;

@Named
@SessionScoped
public class PartnerBean implements Serializable {

    @Getter
    @Setter
    private Partner partner = new Partner();

    @EJB
    private PartnerDao db;

    public List<Partner> getList() {
        return db.listAll();
    }

    public void addRecord() {
        db.create(partner);
        partner = new Partner();
    }

    public void delete(int id) {
        System.out.println("Run delete");
        db.delete(id);
    }

}

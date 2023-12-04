package lab.web.lab04.beans;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lab.web.lab04.data.Partner;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import lab.web.lab04.dao.PartnerDao;

@Named
@SessionScoped
public class PartnerBean implements Serializable {
    @EJB
    private PartnerDao db;

    @Getter
    @Setter
    private Partner partner = new Partner();

    public List<Partner> getList() {
        return db.listAll();
    }

    public void create() {
        db.create(partner);
        partner = new Partner();
    }

    public void delete(Long id) {
        db.delete(id);
    }

    public String edit(Long id) {
        partner = db.find(id);
        return "partner_form";
    }

    public String update() {
        db.update(partner);
        partner = new Partner();
        return "partner_list";
    }
}

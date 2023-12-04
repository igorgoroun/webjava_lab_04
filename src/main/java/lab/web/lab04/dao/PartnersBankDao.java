package lab.web.lab04.dao;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.web.lab04.data.Bank;
import lab.web.lab04.data.PartnersBank;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class PartnersBankDao {
    @PersistenceContext
    EntityManager em;

    public List<PartnersBank> listAll() {
        return em.createNamedQuery("PartnersBank.findAll", PartnersBank.class).getResultList();
    }

    public void create(PartnersBank bank) {
        Logger.getLogger("PB").warning("B_id: "+bank.getBank());
//        em.persist(bank);
    }

    public void delete(Long id) {
        PartnersBank bank = em.find(PartnersBank.class, id);
        em.remove(bank);
    }

    public PartnersBank find(Long id) {
        return em.find(PartnersBank.class, id);
    }

    public void update(PartnersBank bank) {
        em.merge(bank);
    }
}

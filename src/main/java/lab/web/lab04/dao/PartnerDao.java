package lab.web.lab04.dao;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.web.lab04.data.Bank;
import lab.web.lab04.data.Partner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class PartnerDao {
    @PersistenceContext
    EntityManager em;

//    @Resource(name = "jdbc/lab04")
//    DataSource psql;

    public List<Partner> listAll() {
        return em.createNamedQuery("Partner.findAll", Partner.class).getResultList();
    }

    public Partner find(Long id) {
        return em.find(Partner.class, id);
    }

    public void create(Partner partner) {
        em.persist(partner);
    }

    public void delete(Long id) {
        Partner p = em.find(Partner.class, id);
        em.remove(p);
    }

    public void update(Partner p) {
        em.merge(p);
    }

}

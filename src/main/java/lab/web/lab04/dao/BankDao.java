package lab.web.lab04.dao;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.web.lab04.data.Bank;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class BankDao {
    @PersistenceContext
    EntityManager em;

    public List<Bank> listAll() {
        return em.createNamedQuery("Bank.findAll", Bank.class).getResultList();
    }

    public void create(Bank bank) {
        em.persist(bank);
    }

    public void delete(Long id) {
        Bank bank = em.find(Bank.class, id);
        em.remove(bank);
    }

    public Bank find(Long id) {
        return em.find(Bank.class, id);
    }

    public void update(Bank bank) {
        em.merge(bank);
    }
}

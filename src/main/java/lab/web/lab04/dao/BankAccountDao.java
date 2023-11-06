package lab.web.lab04.dao;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import lab.web.lab04.data.BankAccount;
import lab.web.lab04.data.Partner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Stateless
public class BankAccountDao {

    @Resource(name = "jdbc/lab04")
    DataSource psql;

    public List<BankAccount> listForPartner(int partner_id) {
        String query =
                "select " +
                    "b.id, b.partner_id, b.iban, b.bank_name, b.actual " +
                    "from banks b " +
                    "where b.partner_id=?" +
                    "order by actual desc";

        try (
                Connection connection = psql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, partner_id);
            return getBankAccountsFromDao(ps);
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + query );
            return Collections.emptyList();
        }
    }

    public List<BankAccount> listAll() {
        String query =
                "select " +
                        "b.id, b.partner_id, b.iban, b.bank_name, b.actual, p.name as partner_name " +
                        "from banks b " +
                        "left join partners p on p.id=b.partner_id " +
                        "order by actual desc";
        try (
                Connection connection = psql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
        ) {
            return getBankAccountsFromDao(ps);
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + query );
            return Collections.emptyList();
        }
    }

    private List<BankAccount> getBankAccountsFromDao(PreparedStatement ps) throws SQLException {
        ps.execute();
        ResultSet rs = ps.getResultSet();
        List<BankAccount> banks = new ArrayList<>();
        while (rs.next()) {
            banks.add(new BankAccount(
                    rs.getInt("id"),
                    rs.getInt("partner_id"),
                    rs.getString("partner_name"),
                    rs.getString("iban"),
                    rs.getString("bank_name"),
                    rs.getBoolean("actual")
            ));
        }
        return banks;
    }

    public void create(BankAccount bank) {
        String query =
                "insert into banks (iban, partner_id, bank_name, actual) " +
                "values (?, ?, ?, ?)";
        try (
                Connection connection = psql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setString(1, bank.getIban());
            ps.setInt(2, bank.getPartner_id());
            ps.setString(3, bank.getIban());
            ps.setBoolean(4, bank.getActual());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Cannot execute query: " + query );
        }
    }

    public void delete(int id) {
        String query = "delete from banks where id=?";
        try (
                Connection connection = psql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Cannot execute query: " + query );
        }
    }
}

package lab.web.lab04.dao;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import lab.web.lab04.data.Bank;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class BankDao {

    @Resource(name = "jdbc/lab04")
    DataSource psql;

    public List<Bank> listAll() {
        String query =
                "select " +
                    "b.id, b.name, b.tel_number " +
                    "from banks b " +
                    "order by id desc";

        try (
                Connection connection = psql.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query)
        ) {
            List<Bank> partners = new ArrayList<>();
            while (rs.next()) {
                partners.add(new Bank(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("tel_number")
                ));
            }
            return partners;
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + query );
            return Collections.emptyList();
        }
    }

    public void create(Bank bank) {
        String query =
                "insert into banks (name, tel_number) " +
                "values (?, ?)";
        System.err.println("Run create Bank");
        try (
                Connection connection = psql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setString(1, bank.getName());
            ps.setString(2, bank.getTel_number());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Cannot execute query: " + query );
        }
    }

    public void delete(int id) {
        String query = "delete from banks where id=?";
        System.out.println("Run DB delete bank");
        Logger.getLogger("BANK").warning("asdasd asda slkdj aljsa");
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

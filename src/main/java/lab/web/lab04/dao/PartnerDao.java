package lab.web.lab04.dao;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import lab.web.lab04.data.Partner;


@Stateless
public class PartnerDao {

//    @Resource(name = "jdbc/l04")
    @Resource(name = "jdbc/lab04")
    DataSource psql;

    public List<Partner> listAll() {
        String query =
                "select " +
                    "p.id, p.name, p.address, p.tel_number, p.itn, p.reg, p.notes " +
                    "from partners p " +
                    "order by id desc";

        System.err.println("Run list");
        try (
                Connection connection = psql.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query)
        ) {
            List<Partner> partners = new ArrayList<>();
            while (rs.next()) {
                partners.add(new Partner(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("tel_number"),
                        rs.getString("itn"),
                        rs.getString("reg"),
                        rs.getString("notes")
                ));
            }
            return partners;
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + query );
            return Collections.emptyList();
        }
    }

    public void create(Partner partner) {
        String query =
                "insert into partners (name, address, tel_number, itn, reg, notes) " +
                "values (?, ?, ?, ?, ?, ?)";
        System.err.println("Run create");
        try (
                Connection connection = psql.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setString(1, partner.getName());
            ps.setString(2, partner.getAddress());
            ps.setString(3, partner.getTel_number());
            ps.setString(4, partner.getItn());
            ps.setString(5, partner.getReg());
            ps.setString(6, partner.getNotes());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Cannot execute query: " + query );
        }
    }

    public void delete(int id) {
        String query = "delete from partners where id=?";
        System.out.println("Run DB delete");
        Logger.getLogger("sss").warning("asdasd asda slkdj aljsa");
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

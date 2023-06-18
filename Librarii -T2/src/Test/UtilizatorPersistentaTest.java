import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.*;

public class UtilizatorPersistentaTest {
    @Test
    public void adaugareUtilizatorTest() throws SQLException {

        Utilizator utilizatorTest = new Utilizator(1451, "mariai", "septembrie", "angajat", 5);

        UtilizatorPersistenta utilizatorPersistenta = new UtilizatorPersistenta();
        boolean rezultat = utilizatorPersistenta.adaugareUtilizator(utilizatorTest);

        Connection connection = DataBaseConnection.getInstance().getConnection();
        String comandaSQL = "SELECT COUNT(*) AS total FROM utilizator_app WHERE id_utilizator = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, utilizatorTest.getIdUtilizator());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int numarUtilizatori = resultSet.getInt("total");
        assertEquals(1, numarUtilizatori);

        assertTrue(rezultat);
    }
    @Test
    public void stergereUtilizatorTest() throws SQLException {

        Utilizator utilizatorTest = new Utilizator(11, "mariusp", "test", "angajat", 1);

        UtilizatorPersistenta utilizatorPersistenta = new UtilizatorPersistenta();
        utilizatorPersistenta.adaugareUtilizator(utilizatorTest);
        boolean rezultat = utilizatorPersistenta.stergereUtilizator(utilizatorTest.getIdUtilizator());

        Connection connection = DataBaseConnection.getInstance().getConnection();
        String comandaSQL = "SELECT COUNT(*) AS total FROM utilizator_app WHERE id_utilizator = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, utilizatorTest.getIdUtilizator());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int numarUtilizatori = resultSet.getInt("total");
        assertEquals(0, numarUtilizatori);

        assertTrue(rezultat);

    }
    @Test
    public void actualizareUtilizatorTest() throws SQLException {

        Utilizator utilizator = new Utilizator(1312, "mariand", "octombrie", "angajat", 6);
        UtilizatorPersistenta utilizatorPersistenta = new UtilizatorPersistenta();
        utilizatorPersistenta.adaugareUtilizator(utilizator);

        utilizator.setParola("parola_noua");
        utilizator.setTip("angajat");
        utilizator.setIdLibrarie(2);
        boolean result = utilizatorPersistenta.actualizareUtilizator(utilizator);

        Connection connection = DataBaseConnection.getInstance().getConnection();
        String comandaSQL = "SELECT * FROM utilizator_app WHERE id_utilizator = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, utilizator.getIdUtilizator());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals(utilizator.getUser(), resultSet.getString("username"));
        assertEquals(utilizator.getParola(), resultSet.getString("parola"));
        assertEquals(utilizator.getTip(), resultSet.getString("tip"));
        assertEquals(utilizator.getIdLibrarie(), resultSet.getInt("id_librarie"));

        assertTrue(result);
    }

}




import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.*;

public class CarteLibrariePersistentaTest {

    @Test
    public void adaugareCarteTest() throws SQLException {

        Carte carteTest = new Carte(668, "Ficțiune", "Testamentele", "Margaret Atwood");

        CartePersistenta cartePersistenta = new CartePersistenta();
        boolean rezultat = cartePersistenta.adaugareCarte(carteTest);

        Connection connection = DataBaseConnection.getInstance().getConnection();
        String comandaSQL = "SELECT COUNT(*) AS total FROM carte_app WHERE id_carte = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, carteTest.getIdCarte());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int numarCarti = resultSet.getInt("total");
        assertEquals(1, numarCarti);

        assertTrue(rezultat);
    }

    @Test
    public void stergereCarteTest() throws SQLException {
        Carte carteTest = new Carte(1235, "Istorie", "The Nightingale", "Kristin Hannah");

        CartePersistenta cartePersistenta = new CartePersistenta();
        cartePersistenta.adaugareCarte(carteTest);

        boolean rezultat = cartePersistenta.stergereCarte(carteTest.getIdCarte());

        assertTrue(rezultat);
    }

    @Test
    public void actualizareCarteTest() throws SQLException {

        Carte carteTest = new Carte(636, "Fictiune", "Piatra filozofala", "J.K. Rowling");

        CartePersistenta cartePersistenta = new CartePersistenta();
        cartePersistenta.adaugareCarte(carteTest);

        Carte carteModificata = new Carte(carteTest.getIdCarte(), "Fictiune", "camera secretelor", "J.K. Rowling");
        boolean rezultat = cartePersistenta.actualizareCarte(carteModificata);

        assertTrue(rezultat);
    }


}

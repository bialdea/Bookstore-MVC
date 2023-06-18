import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.*;

public class CartePersistentaTest {

    @Test
    public void adaugareCarteLibrarieTest() throws SQLException {

        Carte carteTest = new Carte(775, "Non-ficțiune", "Sapiens: humanity", "Yuval Noah");
        CarteLibrarie carteLibrarieTest = new CarteLibrarie(50.0f, 10, 775, "Europa", 1, carteTest.getIdCarte(), carteTest.getDomeniu(), carteTest.getTitlu(), carteTest.getAutor());

        CarteLibrariePersistenta carteLibrariePersistenta = new CarteLibrariePersistenta();
        boolean rezultat = carteLibrariePersistenta.adaugareCarteLibrarie(carteLibrarieTest);

        Connection connection = DataBaseConnection.getInstance().getConnection();
        String comandaSQL = "SELECT COUNT(*) AS total FROM carte_app WHERE id_carte = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, carteTest.getIdCarte());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int numarCarti = resultSet.getInt("total");
        assertEquals(1, numarCarti);

        comandaSQL = "SELECT COUNT(*) AS total FROM cartelibrarie_app WHERE id_cartelibrarie = ?";
        statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, carteLibrarieTest.getIdCarteLibrarie());
        resultSet = statement.executeQuery();
        resultSet.next();
        int numarCartiLibrarie = resultSet.getInt("total");
        assertEquals(1, numarCartiLibrarie);

        assertTrue(rezultat);
    }

    CarteLibrariePersistenta clp = new CarteLibrariePersistenta();
    @Test
    public void stergereCarteLibrarieTest() throws SQLException {

        Carte carteTest = new Carte(111, "Biografie", "Becoming", "Michelle Obama");
        CarteLibrarie carteLibrarieTest = new CarteLibrarie(60.0f, 5, 111, "Litera", 1, carteTest.getIdCarte(), carteTest.getDomeniu(), carteTest.getTitlu(), carteTest.getAutor());
        CarteLibrariePersistenta carteLibrariePersistenta = new CarteLibrariePersistenta();
        carteLibrariePersistenta.adaugareCarteLibrarie(carteLibrarieTest);


        boolean rezultat = carteLibrariePersistenta.stergereCarteLibrarie(carteLibrarieTest.getIdCarteLibrarie());

        // Verificare că carte a fost ștearsă din baza de date
        Connection connection = DataBaseConnection.getInstance().getConnection();
        String comandaSQL = "SELECT COUNT(*) AS total FROM cartelibrarie_app WHERE id_cartelibrarie = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, carteLibrarieTest.getIdCarteLibrarie());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int numarCartiLibrarie = resultSet.getInt("total");
        assertEquals(0, numarCartiLibrarie);

        assertTrue(rezultat);
    }

    @Test
    public void actualizareCarteLibrarieTest() throws SQLException {

        Carte carteTest = new Carte(1029, "Ficțiune", "Gone Girl", "Gillian Flynn");
        CarteLibrarie carteLibrarieTest = new CarteLibrarie(40.0f, 5, 1029, "RaoEditure", 2, carteTest.getIdCarte(), carteTest.getDomeniu(), carteTest.getTitlu(), carteTest.getAutor());
        CarteLibrariePersistenta carteLibrariePersistenta = new CarteLibrariePersistenta();
        carteLibrariePersistenta.adaugareCarteLibrarie(carteLibrarieTest);

        CarteLibrarie carteLibrarieActualizata = new CarteLibrarie(50.0f, 10, 1029, "RaoEditure", 2, carteTest.getIdCarte(), carteTest.getDomeniu(), carteTest.getTitlu(), carteTest.getAutor());
        boolean rezultat = carteLibrariePersistenta.actualizareCarteLibrarie(carteLibrarieActualizata);

        Connection connection = DataBaseConnection.getInstance().getConnection();
        String comandaSQL = "SELECT editura, pret, disponibil FROM cartelibrarie_app WHERE id_cartelibrarie = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, carteLibrarieTest.getIdCarteLibrarie());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        String editura = resultSet.getString("editura");
        float pret = resultSet.getFloat("pret");
        int disponibil = resultSet.getInt("disponibil");

        assertEquals(carteLibrarieActualizata.getEditura(), editura);
        assertEquals(carteLibrarieActualizata.getPret(), pret, 0.001);
        assertEquals(carteLibrarieActualizata.getDisponibil(), disponibil);

        assertTrue(rezultat);
    }

}

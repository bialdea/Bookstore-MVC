package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartePersistenta {
    Connection connection = DataBaseConnection.getInstance().getConnection();
    public boolean adaugareCarte(Carte utl) throws SQLException {
        String comandaSQL = "INSERT INTO carte_app VALUES(? ,?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, utl.getIdCarte());
        statement.setString(2, utl.getDomeniu());
        statement.setString(3, utl.getTitlu());
        statement.setString(4, utl.getAutor());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Crearea cartii a esuat!");
        }
        return true;
    }

    public boolean stergereCarte(int idCarte) throws SQLException {
        String comandaSQL = "DELETE FROM carte_app WHERE id_carte = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, idCarte);

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Stergerea cartii din baza de date a esuat!");
        }
        return true;
    }
    public boolean actualizareCarte(Carte atl) throws SQLException {
        String comandaSQL = "UPDATE carte_app SET domeniu=?, titlu=?, autor=? WHERE id_carte=?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setString(1, atl.getDomeniu());
        statement.setString(2, atl.getTitlu());
        statement.setString(3, atl.getAutor());
        statement.setInt(4, atl.getIdCarte());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Actualizarea cartii din baza de date a esuat!");
        }
        return true;
    }

}

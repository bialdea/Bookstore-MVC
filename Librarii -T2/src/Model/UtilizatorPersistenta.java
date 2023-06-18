package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizatorPersistenta {
    Connection connection = DataBaseConnection.getInstance().getConnection();
    public boolean adaugareUtilizator(Utilizator utl) throws SQLException {
        String comandaSQL = "INSERT INTO utilizator_app VALUES(? ,?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, utl.getIdUtilizator());
        statement.setString(2, utl.getUser());
        statement.setString(3, utl.getParola());
        statement.setString(4, utl.getTip());
        statement.setInt(5, utl.getIdLibrarie());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Adaugarea utilizatorului a esuat!");
        }
        return true;
    }

    public boolean stergereUtilizator(int idUtilizator) throws SQLException {
        String comandaSQL = "DELETE FROM utilizator_app WHERE id_utilizator = ?;";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, idUtilizator);
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Stergerea utilizatorului a esuat, utilizatorul cu idUtilizator: " + idUtilizator + " nu exista!");
        }
        return true;
    }

    public boolean actualizareUtilizator(Utilizator utl) throws SQLException {
        String comandaSQL = "UPDATE utilizator_app SET username=?, parola=?, tip=?, id_librarie=? WHERE id_utilizator=?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setString(1, utl.getUser());
        statement.setString(2, utl.getParola());
        statement.setString(3, utl.getTip());
        statement.setInt(4, utl.getIdLibrarie());
        statement.setInt(5, utl.getIdUtilizator());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Actualizarea utilizatorului a esuat!");
        }
        return true;
    }

        public List<Utilizator> totiUtilizatorii() {
        List<Utilizator> totiUtilizatorii = new ArrayList<>();

        String comandaSQL = "SELECT * FROM utilizator_app;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(comandaSQL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idUtilizator = resultSet.getInt("id_utilizator");
                String username = resultSet.getString("username");
                String parola = resultSet.getString("parola");
                String tip = resultSet.getString("tip");
                int idLibrarie = resultSet.getInt("id_librarie");
                Utilizator utilizator = new Utilizator(idUtilizator, username, parola, tip, idLibrarie);
                totiUtilizatorii.add(utilizator);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }

        return totiUtilizatorii;
    }

    public Utilizator cautareUtilizator(String username, String parola) {
        String comandaSQL = "SELECT * FROM utilizator_app WHERE username=? AND parola=?;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilizator utilizator = null;

        try {
            statement = connection.prepareStatement(comandaSQL);
            statement.setString(1, username);
            statement.setString(2, parola);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idUtilizator = resultSet.getInt("id_utilizator");
                String tip = resultSet.getString("tip");
                int idLibrarie = resultSet.getInt("id_librarie");
                utilizator = new Utilizator(idUtilizator, username, parola, tip, idLibrarie);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return utilizator;
    }

}






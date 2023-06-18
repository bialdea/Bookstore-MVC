package Model;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarteLibrariePersistenta {
    Connection connection = DataBaseConnection.getInstance().getConnection();

    public List<CarteLibrarie> selectAllCarti(int idLibrarieT) {
        List<CarteLibrarie> carti = new ArrayList<>();
        try {
            Connection connection = DataBaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT cl.id_librarie, cl.id_cartelibrarie, cl.pret, cl.disponibil, cl.editura, c.domeniu, c.titlu, c.autor FROM cartelibrarie_app cl INNER JOIN carte_app c ON cl.id_cartelibrarie = c.id_carte WHERE id_librarie =" + idLibrarieT +" ORDER BY autor, titlu");
            while (result.next()) {
                CarteLibrarie carte = new CarteLibrarie();
                carte.setIdLibrarie(result.getInt("id_librarie"));
                carte.setIdCarteLibrarie(result.getInt("id_cartelibrarie"));
                carte.setDomeniu(result.getString("domeniu"));
                carte.setTitlu(result.getString("titlu"));
                carte.setAutor(result.getString("autor"));
                carte.setPret(result.getFloat("pret"));
                carte.setDisponibil(result.getInt("disponibil"));
                carte.setEditura(result.getString("editura"));
                carti.add(carte);
            }
        } catch (SQLException e) {
            System.out.println("Eroare in selectarea cartilor: " + e.getMessage());
        }
        return carti;
    }

    public List<CarteLibrarie> filterAllCarti(String coloana, Object criteriu, int idLibrarieT) {
        List<CarteLibrarie> carti = new ArrayList<>();
        try {
            Connection connection = DataBaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT cl.id_librarie, cl.id_cartelibrarie, cl.pret, cl.disponibil, cl.editura, c.domeniu, c.titlu, c.autor FROM cartelibrarie_app cl INNER JOIN carte_app c ON cl.id_cartelibrarie = c.id_carte WHERE id_librarie ="+ idLibrarieT +" AND CAST("+coloana+" AS TEXT) LIKE '"+criteriu+"%' ORDER BY autor, titlu");
            while (result.next()) {
                CarteLibrarie carte = new CarteLibrarie();
                carte.setIdLibrarie(result.getInt("id_librarie"));
                carte.setIdCarteLibrarie(result.getInt("id_cartelibrarie"));
                carte.setDomeniu(result.getString("domeniu"));
                carte.setTitlu(result.getString("titlu"));
                carte.setAutor(result.getString("autor"));
                carte.setPret(result.getFloat("pret"));
                carte.setDisponibil(result.getInt("disponibil"));
                carte.setEditura(result.getString("editura"));
                carti.add(carte);
            }
        } catch (SQLException e) {
            System.out.println("Eroare in selectarea cartilor: " + e.getMessage());
        }
        return carti;
    }

    public boolean adaugareCarteLibrarie(CarteLibrarie carte) {
        try {
            Connection connection = DataBaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO carte_app (id_carte, domeniu, titlu, autor) VALUES (?, ?, ?, ?) ");
            statement.setInt(1, carte.getIdCarte());
            statement.setString(2, carte.getDomeniu());
            statement.setString(3, carte.getTitlu());
            statement.setString(4, carte.getAutor());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                PreparedStatement statement2 = connection.prepareStatement("INSERT INTO cartelibrarie_app (id_librarie, id_cartelibrarie, pret, disponibil, editura) VALUES (?, ?, ?, ?, ?)");
                statement2.setInt(1, carte.getIdLibrarie());
                statement2.setInt(2, carte.getIdCarteLibrarie());
                statement2.setFloat(3, carte.getPret());
                statement2.setInt(4, carte.getDisponibil());
                statement2.setString(5, carte.getEditura());
                int rowsInserted2 = statement2.executeUpdate();
                if (rowsInserted2 > 0) {
                    System.out.println("Carte adaugata cu succes!");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Eroare la adaugarea cartii in baza de date: " + e.getMessage());
        }
        return false;
    }

    public boolean stergereCarteLibrarie(int idCarteLibrarie) throws SQLException {
        String comandaSQL = "DELETE FROM cartelibrarie_app WHERE id_cartelibrarie = ?";
        PreparedStatement statement = connection.prepareStatement(comandaSQL);
        statement.setInt(1, idCarteLibrarie);

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Stergerea cartii din baza de date a esuat!");
        }
        return true;
    }
    public boolean actualizareCarteLibrarie(CarteLibrarie carte) {
        try {
            Connection connection = DataBaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE carte_app SET domeniu=?, titlu=?, autor=? WHERE id_carte=?");
            statement.setString(1, carte.getDomeniu());
            statement.setString(2, carte.getTitlu());
            statement.setString(3, carte.getAutor());
            statement.setInt(4, carte.getIdCarte());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                PreparedStatement statement2 = connection.prepareStatement("UPDATE cartelibrarie_app SET pret=?, disponibil=?, editura=? WHERE id_librarie=? AND id_cartelibrarie=?");
                statement2.setFloat(1, carte.getPret());
                statement2.setInt(2, carte.getDisponibil());
                statement2.setString(3, carte.getEditura());
                statement2.setInt(4, carte.getIdLibrarie());
                statement2.setInt(5, carte.getIdCarteLibrarie());
                int rowsUpdated2 = statement2.executeUpdate();
                if (rowsUpdated2 > 0) {
                    System.out.println("Carte actualizată cu succes!");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Eroare la actualizarea cărții în baza de date: " + e.getMessage());
        }
        return false;
    }

}







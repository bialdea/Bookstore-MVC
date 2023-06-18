package Model;

import java.sql.*;

public class LibrariePersistenta {
    Connection connection = DataBaseConnection.getInstance().getConnection();

    public Librarie cautareLibrarie(int idLibrarie) {
        try {
            String query = "SELECT id_librarie, locatie FROM librarie_app WHERE id_librarie = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, idLibrarie);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                Librarie l = new Librarie(rs.getInt("id_librarie"), rs.getString("locatie"));
                return l;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}



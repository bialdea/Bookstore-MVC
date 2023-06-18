package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DataBaseConnection {
        private static DataBaseConnection instance;
        private Connection connection;
        String jdbcURL = "jdbc:postgresql://localhost:5432/librarie";
        String username = "postgres";
        String password = "7737";
        public DataBaseConnection() {
            try {
                this.connection = DriverManager.getConnection(jdbcURL, username, password);
            } catch (SQLException ex) {
                System.out.println("Something is wrong with the DB connection String : " + ex.getMessage());
            }
        }
        public Connection getConnection() {
            return connection;
        }
        public static DataBaseConnection getInstance() {
            if (instance == null) {
                instance = new DataBaseConnection();
                return instance;
            } else
                return  instance;
        }
    }


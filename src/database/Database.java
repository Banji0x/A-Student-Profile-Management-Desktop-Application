package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static void connectToDb() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "your_username";
        String password = "your_password";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save() {

    }

    public void saveAll() {

    }

}

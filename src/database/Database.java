package database;


import model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Database {
    private boolean connected;

    public void connect() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "your_username";
        String password = "your_password";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            connected = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean save() {
        return false;
    }

    public boolean saveAll() {
        return false;
    }

    public boolean isConnected() {
        return connected;
    }

    public List<Student> getStudent(String firstName, String lastName) {
        return null; //returns a list bc it's possible more than 2 students bears the same first and last name.
    }

    public List<Student> getListOfStudents() {
        return null;
    }
}

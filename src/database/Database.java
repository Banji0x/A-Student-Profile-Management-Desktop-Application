package database;


import model.Student;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class Database {
    private boolean connected;
    private JFrame frame;

    public boolean connect(JFrame frame) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "your_username";
        String password = "your_password";

//        try {
//            Connection connection = DriverManager.getConnection(url, username, password);
//            connection.setAutoCommit(false);
//            connected = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //
        this.frame = frame;
        connected = true;
        if (!connected) {
            JOptionPane.showMessageDialog(frame, "There's a problem with your network connection.");
        }
        return connected;
        //
    }


    public boolean save(Student student) {
        boolean connected = connect(frame);
        if (!connected)
            return false;
        return true;
//        return false;
    }

    public boolean saveAll(List<Student> students) {
        boolean connected = connect(frame);
        if (!connected)
            return false;
        return true;
//        return false;
    }

    public List<Student> getStudentByName(String firstName, String lastName) {
        boolean connected = connect(frame);
        if (!connected)
            return Collections.emptyList();
        return List.of(
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee")
        );//returns a list bc it's possible more than 2 students bears the same first and last name.
    }

    public List<Student> getStudentByMatricNumber(String matricNumber) {
        if (matricNumber.equals("test001"))
            return List.of(
                    new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                    new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                    new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                    new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee")
            );
        else
            return Collections.emptyList();
    }

    public List<Student> getListOfStudents() {
        return List.of(
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee")
        );
    }

    public boolean studentExists(String matricNumber) {
        if (matricNumber.equals("test001"))
            return true;
        return false;
    }

    public boolean studentExists(String firstName, String lastName) {
        if (!connect(frame))
            return false;
        if (firstName.equals("test") && lastName.equals("password")) {
            return true;
        }
        return false;
    }

    public boolean deleteStudent(String matricNumber) {
        if (!connect(frame))
            return false;
        return true;
    }

    public boolean deleteStudent(String firstName, String lastName) {
        if (!connect(frame))
            return false;
        return true;
    }

    public boolean updateAll(List<Student> students) {
        if (!connect(frame))
            return false;
        return true;
    }

    public boolean deleteALl(List<Student> students) {
        if (!connect(frame))
            return false;
        return true;
    }
}

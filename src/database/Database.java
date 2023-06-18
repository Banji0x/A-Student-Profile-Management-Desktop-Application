package database;


import model.Student;

import java.util.List;

public class Database {
    private int numberOfTimes;
    private boolean connected;

    public void connect() {
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
    }


    public boolean save(Student student) {
        return true;
//        return false;
    }

    public boolean saveAll(List<Student> students) {
        return true;
//        return false;
    }

    public boolean isConnected() {
//        return connected;
        return false;
    }

    public List<Student> getStudentByName(String firstName, String lastName) {
        return List.of(
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee")
        );//returns a list bc it's possible more than 2 students bears the same first and last name.
    }

    public List<Student> getStudentByMatricNumber(String matricNumber) {
        return List.of(
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee")
        );
//        return null;
    }

    public List<Student> getListOfStudents() {
        return List.of(
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee"),
                new Student("aaaaa", "aaaaa", 34, "ekniei", "bejeie", "jee")
        );
//        return null;
    }

    public boolean studentExists(String matricNumber) {
        return true;
    }

    public boolean studentExists(String firstName, String lastName) {
        return false;
    }

    public boolean deleteStudent(String matricNumber) {
        return true;
    }

    public boolean deleteStudent(String firstName, String lastName) {
        return true;
    }
}

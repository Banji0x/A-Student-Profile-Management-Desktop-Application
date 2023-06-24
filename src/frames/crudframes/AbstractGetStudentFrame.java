package frames.crudframes;

import database.Database;
import frames.ApplicationFrame;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public abstract class AbstractGetStudentFrame extends JFrame {
    protected Database database;
    protected JPanel studentPanel;

    private AbstractGetStudentFrame(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null); //centers it
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        database = new Database();
        database.connect(this);
        studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());

        var buttonPanel = new JPanel();
        var homeButton = new JButton("Proceed to Home");
        var exitButton = new JButton("Exit application");
        //listeners
        homeButton.addActionListener(actionPerformed -> {
            dispose();
            new ApplicationFrame();
        });
        exitButton.addActionListener(actionPerformed -> dispose());
        //

        buttonPanel.add(homeButton);
        buttonPanel.add(exitButton);
        studentPanel.add(buttonPanel, SOUTH);
        this.add(studentPanel);
    }


    protected JTable createTable(List<Student> students) {
        String[] columnHeaders = {"First Name", "Last Name", "Age", "Matric Number", "Department", "Faculty"};
        Object[][] rowData = new Object[students.size()][columnHeaders.length];
        var table = new JTable(rowData, columnHeaders);
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < columnHeaders.length; j++) {
                for (int k = 0; k < columnHeaders.length; k++) {
                    rowData[i][k++] = students.get(i).firstName();
                    rowData[i][k++] = students.get(i).lastName();
                    rowData[i][k++] = students.get(i).age();
                    rowData[i][k++] = students.get(i).matricNumber();
                    rowData[i][k++] = students.get(i).department();
                    rowData[i][k++] = students.get(i).faculty();
                }
            }
        }
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        table.setEnabled(false);
        return table;
    }

    //fetches student's using a first and last name parameter
    //this inner class has another inner-class that it delegates to
    public static class FetchStudentByFirstAndLastNameFrame extends AbstractStudentByFirstAndLastNameFrame {

        public FetchStudentByFirstAndLastNameFrame() {
            super("Fetch Student Details.");
            submitButton.addActionListener(actionPerformed -> {
                if (doNotProceed) //the other listener found errors...
                    return;
                if (!studentExists)
                    return;
                var studentList = database.getStudentByName(studentDto.firstNameTextField().getText().trim(), studentDto.lastNameTextField().getText().trim());
                dispose(); //dispose this frame...
                new GetStudentByFirstAndLastNameFrame(studentList);
            });
            setVisible(true);
        }

        public static class GetStudentByFirstAndLastNameFrame extends AbstractGetStudentFrame {
            public GetStudentByFirstAndLastNameFrame(List<Student> studentList) { //fetches only a single student
                super("Fetch Student Details By First And Last Name.");
                JTable table = createTable(studentList);
                JScrollPane jScrollPane = new JScrollPane(table);
                jScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
                studentPanel.add(jScrollPane, CENTER);
                setVisible(true);
            }
        }


    }

    //fetches student's using a matric number parameter
    //this inner class has another inner-class that it delegates to
    public static class FetchStudentByMatricNumberFrame extends AbstractStudentByMatricNumberFrame {
        public FetchStudentByMatricNumberFrame() throws HeadlessException {
            super("Fetch Student Details");
            submitButton.addActionListener(actionPerformed -> {
                if (doNotProceed) { //check if the listener found issues...
                    return;
                }
                if (!studentExists){
                    return;
                }
                new GetStudentByMatricNumberFrame(matricNumberTextField);
            });
            setVisible(true);
        }

        public static class GetStudentByMatricNumberFrame extends AbstractGetStudentFrame {
            public GetStudentByMatricNumberFrame(JTextField matricNumberTextField) {
                super("Fetch Student Details By Matric Number");
                List<Student> students = database.getStudentByMatricNumber(matricNumberTextField.getText().trim());
                JTable table = createTable(students);
                JScrollPane jScrollPane = new JScrollPane(table);
                jScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
                studentPanel.add(jScrollPane, CENTER);
                setVisible(true);
            }
        }

    }

    //fetch all students in the database
    //this doesn't have to be an "inner-inner" class
    public static class GetAllStudentDetailsFrame extends AbstractGetStudentFrame {
        public GetAllStudentDetailsFrame() { //fetches all students
            super("Fetch All Student Details");
            List<Student> students = database.getListOfStudents();
            if (students.isEmpty()) {
                Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
                JOptionPane.showMessageDialog(this, "Student not found !!!");
                return;
            }
            JTable table = createTable(students);
            JScrollPane jScrollPane = new JScrollPane(table);
            jScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
            studentPanel.add(jScrollPane, CENTER);
            setVisible(true);
        }

    }
}

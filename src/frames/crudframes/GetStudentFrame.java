package frames.crudframes;

import database.Database;
import frames.ApplicationFrame;
import model.Student;
import model.StudentDto;
import validator.Validator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class GetStudentFrame extends JFrame {
    private StudentDto studentDto;
    private String matricNumber;

    public static void FetchAllStudents() { //fetches all students
        new GetStudentFrame();
    }

    public static void FetchStudentByName(StudentDto studentDto) { //fetches only a single student
        GetStudentFrame studentFrame = new GetStudentFrame();
        studentFrame.studentDto = studentDto;
    }

    public static void FetchStudentByMatricNumber(String matricNumber) {
        GetStudentFrame studentFrame = new GetStudentFrame();
        studentFrame.matricNumber = matricNumber;
    }

    private GetStudentFrame() throws HeadlessException {
        super("Fetch Student Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null); //centers it
        Database database = new Database();
        database.connect();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());

        List<Student> students;
        if (studentDto != null)
            students = database.getStudentByName(studentDto.firstNameTextField().getText().trim(), studentDto.lastNameTextField().getText().trim());
        else if (matricNumber != null) students = database.getStudentByMatricNumber(matricNumber);
        else students = database.getListOfStudents();

        JTable table = createTable(students);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

        studentPanel.add(jScrollPane, CENTER);
        var buttonPanel = new JPanel();
        var homeButton = new JButton("Proceed to Home");
        var exitButton = new JButton("Exit application");
        //listeners
        homeButton.addActionListener(actionPerformed -> {
            dispose();
            new ApplicationFrame();
        });

        exitButton.addActionListener(actionPerformed -> {
            dispose();
        });
        //
        buttonPanel.add(homeButton);
        buttonPanel.add(exitButton);
        studentPanel.add(buttonPanel, SOUTH);
        this.add(studentPanel);
        setVisible(true);
    }

    private JTable createTable(List<Student> students) {
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
        return table;
    }

    public static class FetchStudentByNameFrame extends AbstractStudentByNameFrame {

        public FetchStudentByNameFrame() {
            super("Get Student By Name");
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setSize(300, 230);
            setLocationRelativeTo(null);
//            JLabel firstNameLabel = new JLabel("First name: ");
//            JLabel lastNameLabel = new JLabel("Last name: ");
//            JTextField firstNameTextField = new JTextField(15);
//            JTextField lastNameTextField = new JTextField(15);
//
//            ((PlainDocument) firstNameTextField.getDocument()).setDocumentFilter(Validator.textValidator());
//            ((PlainDocument) lastNameTextField.getDocument()).setDocumentFilter(Validator.textValidator());
//
//            var studentPanel = new JPanel(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.anchor = GridBagConstraints.WEST;
//            gbc.insets = new Insets(5, 5, 5, 5);
//
//            gbc.gridy = 0;
//            gbc.gridx = 0;
//            studentPanel.add(firstNameLabel, gbc);
//            gbc.gridx = 1;
//            studentPanel.add(firstNameTextField, gbc);
//            gbc.gridy = 1;
//            gbc.gridx = 0;
//            studentPanel.add(lastNameLabel, gbc);
//            gbc.gridx = 1;
//            studentPanel.add(lastNameTextField, gbc);
//
//            var submitButton = new JButton("Submit");
//            gbc.gridy = 2;
//            gbc.gridwidth = 2;
//            gbc.insets = new Insets(10, 5, 5, 5);
//            submitButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size for the login button
//
//            studentPanel.add(submitButton, gbc);
//            studentPanel.revalidate();
//            studentPanel.repaint();
//
//            lastNameTextField.addKeyListener(new TextFieldKeyListener(submitButton));  ////
            //these lines of code is redundant because the abstract super class already has it...
            add(studentPanel, CENTER);

            submitButton.addActionListener(actionPerformed -> {
                Object[] objects = new Validator.BlankFieldsValidator().validate(new JTextField[]{firstNameTextField, lastNameTextField});
                var isBlank = (boolean) objects[0];
                var blankField = (JTextField) objects[1];
                if (isBlank) {
                    JOptionPane.showMessageDialog(this, "Field cannot be blank !!!");
                    blankField.requestFocus(); // Set focus back to the component
                    return;
                }
                var studentDto = new StudentDto(firstNameTextField, lastNameTextField);
                dispose();
                GetStudentFrame.FetchStudentByName(studentDto);
            });
//            pack();
            setVisible(true);
        }


    }

    public static class FetchStudentByMatricFrame extends AbstractStudentByMatricNumberFrame {
        public FetchStudentByMatricFrame() throws HeadlessException {
            super("Fetch Student Details");
            submitButton.addActionListener(actionPerformed -> {
                if (!validationIssues) {
                    dispose();
                    GetStudentFrame.FetchStudentByMatricNumber(submitButton.getText().trim());
                }
            });
            setVisible(true);
        }
    }
}

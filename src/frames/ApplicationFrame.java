package frames;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.BorderLayout.*;

public class ApplicationFrame extends JFrame {
    private final ArrayList<JTextField[]> studentTextFieldRows;
    private final JLabel firstNameLabel, lastNameLabel, ageLabel/*, genderLabel*/, matricNumberLabel, departmentLabel, facultyLabel;
    private final JTextField firstNameTextField, lastNameTextField, ageTextField, matricNumberTextField, departmentTextField, facultyTextField;
    private JButton fetchButton, addButton, updateButton, deleteButton; //Get,Post,Put/Patch,Delete
    private JPanel studentPanel;
    private JTextArea outputTextArea;
    private int rowCount = 1;

    public ApplicationFrame() {
        super("Student Profile Management System");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //Labels and Text fields
        //first and last name
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        firstNameTextField = new JTextField(10);
        lastNameTextField = new JTextField(10);
        //age
        ageLabel = new JLabel("Age");
        ageTextField = new JTextField(3);
        //matric number
        matricNumberLabel = new JLabel("Matric Number");
        matricNumberTextField = new JTextField(10);
        //department
        departmentLabel = new JLabel("Department");
        departmentTextField = new JTextField(20);
        //faculty
        this.facultyLabel = new JLabel("Faculty");
        facultyTextField = new JTextField(20);

        fetchButton = new JButton("Fetch All");
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false); //the data fetched that is being displayed shouldn't be editable
        JScrollPane scrollPane = new JScrollPane(outputTextArea); //makes the output pane scrollable


        studentPanel = new JPanel(new GridBagLayout());
        studentTextFieldRows = new ArrayList<>(); //an arrayList to store each row data
        addNewStudentRow();

        JPanel buttonPanel = new JPanel(new FlowLayout());
//        buttonPanel.add(fetchButton);
        buttonPanel.add(addButton);
//        buttonPanel.add(updateButton);
//        buttonPanel.add(deleteButton);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(studentPanel, NORTH);
        mainPanel.add(buttonPanel, CENTER);
        mainPanel.add(scrollPane, SOUTH);
        this.add(mainPanel);
        //listeners
        fetchButton.addActionListener(actionPerformed -> fetchStudentProfiles());

        addButton.addActionListener(actionPerformed -> {
            addNewStudentRow();
        });
        updateButton.addActionListener(actionPerformed -> updateStudentProfiles());
        deleteButton.addActionListener(actionPerformed -> deleteStudentProfiles());
    }


    private void addNewStudentRow() {
        var gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 5, 5, 5);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = rowCount;
        studentPanel.add(firstNameLabel);
        studentPanel.add(firstNameTextField, gridBagConstraints);


        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = rowCount;
        studentPanel.add(lastNameLabel);
        studentPanel.add(lastNameTextField, gridBagConstraints);

//        studentPanel.add(genderLabel);
//        studentPanel.add(maleRadioButton);
//        studentPanel.add(femaleRadioButton);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = rowCount;
        studentPanel.add(ageLabel);
        studentPanel.add(ageTextField, gridBagConstraints);


        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = rowCount;
        studentPanel.add(matricNumberLabel);
        studentPanel.add(matricNumberTextField, gridBagConstraints);


        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = rowCount;
        studentPanel.add(departmentLabel);
        studentPanel.add(departmentTextField, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = rowCount;
        studentPanel.add(facultyLabel);
        studentPanel.add(facultyTextField, gridBagConstraints);

        JTextField[] fields = {
                firstNameTextField, lastNameTextField, ageTextField,
                ageTextField, departmentTextField, facultyTextField
        };

        studentTextFieldRows.add(fields);
        rowCount++;
        studentPanel.revalidate();
        studentPanel.repaint();
    }

    private void postStudentProfiles() {

    }

    private void deleteStudentProfiles() {

    }

    private void fetchStudentProfiles() {

    }

    private void updateStudentProfiles() {
    }
}
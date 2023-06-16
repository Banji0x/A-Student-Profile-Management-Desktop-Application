package frames.crudframes;

import frames.ConfirmationFrame;
import validator.Validator;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class AddStudentFrame extends JFrame {
    private final JLabel firstNameLabel, lastNameLabel, ageLabel, matricNumberLabel, departmentLabel, facultyLabel;
    private ArrayList<JTextField[]> listOfTextFields;
    private int rowCount, labelXAxis;

    private final JPanel studentPanel;

    public AddStudentFrame() throws HeadlessException {
        super("Add Student Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 500);
        setLocationRelativeTo(null); //centers it
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        studentPanel = new JPanel(new GridBagLayout());
        listOfTextFields = new ArrayList<>();
        rowCount = 1;


        //labels
        //first and last name
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        //age
        ageLabel = new JLabel("Age");
        //matric number
        matricNumberLabel = new JLabel("Matric Number");
        //department
        departmentLabel = new JLabel("Department");
        //faculty
        this.facultyLabel = new JLabel("Faculty");
        addStudentLabels();
        addStudentRow(); //adds a single row to the panel

        JScrollPane scrollPane = new JScrollPane(studentPanel);//make the panel scrollable
        scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(actionPerformed -> {
            var fields = listOfTextFields.get(listOfTextFields.size() - 1);
            var firstNameTextField = fields[0];
            var lastNameTextField = fields[1];
            var ageTextField = fields[2];
            var matricNumberTextField = fields[3];
            var departmentTextField = fields[4];
            var facultyTextField = fields[5];
            //validate fields
            Object[] objects = new Validator.BlankFieldsValidator().validate(new JTextField[]{firstNameTextField, lastNameTextField, ageTextField, matricNumberTextField, departmentTextField, facultyTextField});
            var isBlank = (boolean) objects[0];
            var blankField = (JTextField) objects[1];
            if (isBlank) {
                JOptionPane.showMessageDialog(this, "Field cannot be blank !!!");
                blankField.requestFocus(); // Set focus back to the component
                return;
            }
            //check if the matric number of the current field is valid
            boolean result = new Validator.MatricNumberValidator().validate(matricNumberTextField.getText().trim());
            if (!result) {
                JOptionPane.showMessageDialog(matricNumberTextField, "Matric number is in an invalid format!!!");
                matricNumberTextField.requestFocus(); // Set focus back to the component
                return;
            }
            addStudentRow();
            pack(); //to automatically resize...
        });


        var confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(actionPerformed -> {
            //you still have to implement a button that
            setVisible(false);
            new ConfirmationFrame(listOfTextFields,this);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(confirmButton);

        this.add(scrollPane, CENTER);
        this.add(buttonPanel, SOUTH);
        this.setVisible(true);
        pack();
    }


    private void addStudentLabels() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = labelXAxis;
        gbc.gridy = 0;
        studentPanel.add(firstNameLabel, gbc);
        gbc.gridx = ++labelXAxis;
        studentPanel.add(lastNameLabel, gbc);
        gbc.gridx = ++labelXAxis;
        studentPanel.add(ageLabel, gbc);
        gbc.gridx = ++labelXAxis;
        studentPanel.add(matricNumberLabel, gbc);
        gbc.gridx = ++labelXAxis;
        studentPanel.add(departmentLabel, gbc);
        gbc.gridx = ++labelXAxis;
        studentPanel.add(facultyLabel, gbc);
    }

    private void addStudentRow() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        //text fields
        var firstNameTextField = new JTextField(10);
        var lastNameTextField = new JTextField(10);
        var ageTextField = new JTextField(3);
        var matricNumberTextField = new JTextField(10);
        var departmentTextField = new JTextField(20);
        var facultyTextField = new JTextField(20);

        //validations
        //type validation
        ((PlainDocument) firstNameTextField.getDocument())
                .setDocumentFilter(Validator.textValidator());
        ((PlainDocument) lastNameTextField.getDocument())
                .setDocumentFilter(Validator.textValidator());
        ((PlainDocument) ageTextField.getDocument())
                .setDocumentFilter(Validator.numberValidator());
//        ((PlainDocument) matricNumberTextField.getDocument())
//                .setDocumentFilter(Validator.matricNumberValidator());
        ((PlainDocument) departmentTextField.getDocument())
                .setDocumentFilter(Validator.textValidator());
        ((PlainDocument) facultyTextField.getDocument())
                .setDocumentFilter(Validator.textValidator());

        //alignment
        gbc.gridy = rowCount;
        studentPanel.add(firstNameTextField, gbc);


        gbc.gridx = 1;
        gbc.gridy = rowCount;
        studentPanel.add(lastNameTextField, gbc);


        gbc.gridx = 2;
        gbc.gridy = rowCount;
        studentPanel.add(ageTextField, gbc);


        gbc.gridx = 3;
        gbc.gridy = rowCount;
        studentPanel.add(matricNumberTextField, gbc);


        gbc.gridx = 4;
        gbc.gridy = rowCount;
        studentPanel.add(departmentTextField, gbc);


        gbc.gridx = 5;
        gbc.gridy = rowCount;
        studentPanel.add(facultyTextField, gbc);
        listOfTextFields.add(new JTextField[]{firstNameTextField, lastNameTextField, ageTextField, matricNumberTextField, departmentTextField, facultyTextField});
        rowCount++;

        studentPanel.revalidate();
        studentPanel.repaint();
        this.revalidate();
        this.repaint();
    }
}

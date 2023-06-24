package frames.crudframes;

import database.Database;
import enums.OperationEnums;
import frames.ApplicationFrame;
import frames.ConfirmationFrame;
import validator.Validator;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public abstract class AbstractStudentOperationsFrame extends JFrame {
    private final JLabel firstNameLabel, lastNameLabel, ageLabel, matricNumberLabel, departmentLabel, facultyLabel;
    protected final JButton homeButton, addButton, exitButton, submitButton;
    protected boolean doNotProceed;
    protected Database database;
    protected List<JTextField[]> listOfTextFields = new ArrayList<>();
    private int rowCount, labelXAxis;

    private final JPanel studentPanel;
//    protected ArrayList<StudentDto> firstAndLastNameTextFieldsList;

    public AbstractStudentOperationsFrame(String title, OperationEnums operation) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 500);
        setLocationRelativeTo(null); //centers it
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        studentPanel = new JPanel(new GridBagLayout());
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
//
        addButton = new JButton("Add");
        submitButton = new JButton("Submit"); //initialized the submit button to the protected instance variable
        homeButton = new JButton("Proceed to Home");
        exitButton = new JButton("Exit application");
//
        //listeners
        addButton.addActionListener(actionPerformed -> {
            if (doNotProceed)
                return;
            var fields = listOfTextFields.get(listOfTextFields.size() - 1); //returns an array of JTextFields
            var firstNameTextField = fields[0];
            var lastNameTextField = fields[1];
            var ageTextField = fields[2];
            var matricNumberTextField = fields[3];
            var departmentTextField = fields[4];
            var facultyTextField = fields[5];

            //validate fields
            boolean blankFieldExists = Validator.BlankFieldsExistsValidator(this, new JTextField[]{firstNameTextField, lastNameTextField, ageTextField, matricNumberTextField, departmentTextField, facultyTextField});
            if (blankFieldExists) {
                return;
            }
            //check if the matric number of the previous row was valid
            boolean valid = Validator.matricNumberValidator(this, matricNumberTextField);
            if (!valid) {
                return;
            }
            addStudentRow();
            pack(); //to automatically resize...
        });
        homeButton.addActionListener(actionPerformed -> {
            dispose();
            new ApplicationFrame();
        });
        exitButton.addActionListener(actionPerformed -> dispose());
        submitButton.addActionListener(actionPerformed -> {
            if (doNotProceed)
                return;
            //check for if a new row was added but wasn't filled
            boolean blankFieldsExists = Validator.BlankFieldsExistsValidator(this, listOfTextFields);
            if (blankFieldsExists)
                return;
            var matricNumberField = listOfTextFields.get(listOfTextFields.size() - 1)[3];
            var valid = Validator.matricNumberValidator(this, matricNumberField);
            //revalidate
            if (!valid) { //check if the matric number of the previous row was valid
                return;
            }
            setVisible(false);
            new ConfirmationFrame(listOfTextFields, this, operation);
        });
        //
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(homeButton);
        buttonPanel.add(addButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(exitButton);
        //
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
        //type validation for the new text fields to be added to the student panel
        ((PlainDocument) firstNameTextField.getDocument())
                .setDocumentFilter(Validator.textValidator());
        ((PlainDocument) lastNameTextField.getDocument())
                .setDocumentFilter(Validator.textValidator());
        ((PlainDocument) ageTextField.getDocument())
                .setDocumentFilter(Validator.numberValidator());
        ((PlainDocument) departmentTextField.getDocument())
                .setDocumentFilter(Validator.textValidator());
        ((PlainDocument) facultyTextField.getDocument())
                .setDocumentFilter(Validator.textValidator());
        //

        //this field is for subclasses to track the text fields...
        listOfTextFields.add(new JTextField[]{firstNameTextField, lastNameTextField, ageTextField, matricNumberTextField, departmentTextField, facultyTextField});

        //
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
        rowCount++;
        studentPanel.revalidate();
        studentPanel.repaint();
        this.revalidate();
        this.repaint();
    }
}

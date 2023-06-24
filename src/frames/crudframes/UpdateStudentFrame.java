package frames.crudframes;

import database.Database;

import javax.swing.*;
import java.awt.*;

import static enums.OperationEnums.UPDATE;

public abstract class UpdateStudentFrame extends AbstractStudentOperationsFrame {
    protected Database database;

    public UpdateStudentFrame(String title) throws HeadlessException {
        super(title, UPDATE);
        database = new Database();
        database.connect(this);
    }

    public static class UpdateStudentFrameByMatricNumber extends UpdateStudentFrame {
        public UpdateStudentFrameByMatricNumber() {
            super("Update Student Details By Matric Number");
            addButton.addActionListener(actionPerformed -> validateMatricNumber());
            submitButton.addActionListener(actionPerformed -> validateMatricNumber());
        }

        private boolean studentExists(JTextField matricNumberTextField) {
            //call db to check if it exists
            return database.studentExists(matricNumberTextField.getText().trim());
        }

        private void validateMatricNumber() {
            JTextField[] textFields = listOfTextFields.get(listOfTextFields.size() - 1);
            JTextField matricNumberTextField = textFields[3];
            boolean studentExists = studentExists(matricNumberTextField);
            doNotProceed = !studentExists; //this makes the listener in the parent class to proceed or not
            if (!doNotProceed)
                return;
            String message = "Student doesn't exist !!!";
            if (!matricNumberTextField.getText().isBlank())
                message = matricNumberTextField.getText().trim() + " is not a student.";
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
            JOptionPane.showMessageDialog(this, message);
            matricNumberTextField.requestFocus(); // Set focus back to the matricNumber textField
        }
    }

    public static class UpdateStudentFrameByFullName extends UpdateStudentFrame {

        public UpdateStudentFrameByFullName() throws HeadlessException {
            super("Update Student Details By FullName");
            addButton.addActionListener(actionPerformed -> validateFirstAndLastNameFields());

            submitButton.addActionListener(actionPerformed -> validateFirstAndLastNameFields());
        }

        private boolean studentExists(JTextField firstNameTextField, JTextField lastNameTextField) {
            return database.studentExists(firstNameTextField.getText().trim(), lastNameTextField.getText().trim());
        }

        private void validateFirstAndLastNameFields() {
            JTextField[] textFields = listOfTextFields.get(listOfTextFields.size() - 1);
            JTextField firstNameTextField = textFields[0];
            JTextField lastNameTextField = textFields[1];
            boolean studentExists = studentExists(firstNameTextField, lastNameTextField);
            //doNotProceed decides whether the other listener will be invoked or not
            doNotProceed = !studentExists;
            if (!doNotProceed)
                return;
            String message = "Student doesn't exist !!!";
            if (!firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank())
                message = firstNameTextField.getText().trim() + " " + lastNameTextField.getText().trim() + " is not a student.";
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
            JOptionPane.showMessageDialog(this, message);
            firstNameTextField.requestFocus(); // Set focus back to the firstName textField
        }
    }
}

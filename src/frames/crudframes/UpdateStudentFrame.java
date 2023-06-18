package frames.crudframes;

import database.Database;
import model.StudentDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class UpdateStudentFrame extends AddStudentFrame {
    protected Database database;
    private static String title = "Update Student Details";

    public UpdateStudentFrame(String title) throws HeadlessException {
        super.setTitle(title);
        database = new Database();
        database.connect();
    }


    public static class UpdateStudentFrameByMatricNumber extends UpdateStudentFrame {
        public UpdateStudentFrameByMatricNumber() {
            super(title);
            var listOfMatricNumber = listOfMatricNumber();

            AtomicReference<JTextField> textField = new AtomicReference<>();

            submitButton.addActionListener(actionPerformed -> {
                boolean found = listOfMatricNumber.stream().anyMatch(matricNumberTextField -> {
                    var exists = matricNumberExists(matricNumberTextField.getText().trim());
                    textField.set(matricNumberTextField);
                    return exists;
                });
                if (!found) {
                    validationIssueExists = true;  //this prevents the other action listener from proceeding to the next page.
                    JOptionPane.showMessageDialog(this, "Matric number not found!!!");
                    textField.get().requestFocus(); // Set focus back to the component
                    return;
                }
                validationIssueExists = false;
            });
        }

        private List<JTextField> listOfMatricNumber() {
            return this.matricNumberTextFieldsList;
        }

        private boolean matricNumberExists(String matricNumber) {
            //call db to check if it exists
            return database.studentExists(matricNumber.trim());
        }
    }

    public static class UpdateStudentFrameByFullName extends UpdateStudentFrame {

        public UpdateStudentFrameByFullName() throws HeadlessException {
            super(title);
            List<JTextField> namesTextField = new ArrayList<>();
            submitButton.addActionListener(actionPerformed -> {
                List<StudentDto> namesList = listOfStudentFirstAndLastName();
                boolean studentFound = namesList.stream().anyMatch(nameDto -> {
                    var firstName = nameDto.firstNameTextField().getText().trim();
                    var lastName = nameDto.lastNameTextField().getText().trim();
                    boolean studentExists = studentExists(firstName, lastName);
                    if (!studentExists) {
                        namesTextField.add(nameDto.firstNameTextField());
                        namesTextField.add(nameDto.lastNameTextField());
                    }
                    return studentExists;
                });
                if (!studentFound) {
                    validationIssueExists = true;  //this prevents the other action listener from proceeding to the next page.
                    if (namesList.get(0).firstNameTextField().getText().trim().equals("") || namesList.get(0).lastNameTextField().getText().equals("")) {
                        //do nothing
                        //ignore
                    } else {
                        JOptionPane.showMessageDialog(this, namesTextField.get(0).getText().trim() + " " + namesTextField.get(1).getText().trim() + " is not a student.");
                        namesTextField.get(0).requestFocus(); // Set focus back to the first name component
                    }
                } else
                    validationIssueExists = false;
            });
        }

        private boolean studentExists(String firstName, String lastName) {
            return database.studentExists(firstName, lastName);
        }

        private List<StudentDto> listOfStudentFirstAndLastName() {
            return this.firstAndLastNameTextFieldsList;
        }
    }
}

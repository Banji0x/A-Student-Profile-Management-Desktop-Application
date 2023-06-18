package frames.crudframes;

import database.Database;
import frames.ApplicationFrame;
import validator.Validator;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public abstract class AbstractDeleteStudentFrame extends JFrame {
    private static String title = "Delete Student Details";

    public static class DeleteStudentByName extends AbstractStudentByNameFrame {
        private Database database;

        public DeleteStudentByName() throws HeadlessException {
            super(title);
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
//            lastNameTextField.addKeyListener(new TextFieldKeyListener(submitButton));
            //these lines of code is redundant because the abstract super class already has it...
            submitButton.addActionListener(actionPerformed -> {
                Object[] objects = new Validator.BlankFieldsValidator().validate(new JTextField[]{firstNameTextField, lastNameTextField});
                var isBlank = (boolean) objects[0];
                var blankField = (JTextField) objects[1];
                if (isBlank) {
                    JOptionPane.showMessageDialog(this, "This field cannot be blank !!!");
                    blankField.requestFocus(); // Set focus back to the component
                    return;
                }
                if (!database.isConnected()) {
                    database.connect();
                    JOptionPane.showMessageDialog(this, "There's a problem with your network.");
                    return;
                }
                String firstName = firstNameTextField.getText().trim();
                String lastName = lastNameTextField.getText().trim();
                boolean studentExists = database.studentExists(firstName, lastName);
                if (!studentExists) {
                    JOptionPane.showMessageDialog(this, firstName + " " + lastName + " does not exist.");
                    return;
                }
                int confirmDialog = JOptionPane.showConfirmDialog(this, "Are you sure you want to perform this operation ?");
                if (confirmDialog == 0) { //yes
                    boolean success = database.deleteStudent(firstName, lastName);
                    if (!success) {
                        JOptionPane.showMessageDialog(this, "There's a problem with your network, please try again.");
                        return;
                    }
                    String[] options = {"Proceed to Home", "Exit application"};
                    int optionChosen = JOptionPane.showOptionDialog(this, "Delete Operation successful.", "Delete Operation Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
                    if (optionChosen == 0) {
                        dispose();
                        new ApplicationFrame();
                        return;
                    }
                    dispose();
                }
            });
            database = new Database();
            database.connect();
            add(studentPanel, CENTER);
            studentPanel.revalidate();
            studentPanel.repaint();
            setVisible(true);
        }
    }

    public static class DeleteStudentByMatricNumber extends AbstractStudentByMatricNumberFrame {

        public DeleteStudentByMatricNumber() throws HeadlessException {
            super(title);
            setVisible(true);
            submitButton.addActionListener(actionPerformed -> {
                if (!validationIssues) {

                }
            });
        }

    }
}

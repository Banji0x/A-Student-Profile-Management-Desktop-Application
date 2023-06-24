package frames.crudframes;

import database.Database;
import frames.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public abstract class AbstractDeleteStudentFrame extends JFrame {
    private static String title = "Delete Student Details";

    public static class DeleteStudentByName extends AbstractStudentByFirstAndLastNameFrame {
        private Database database = new Database();

        public DeleteStudentByName() throws HeadlessException {
            super(title);
            submitButton.addActionListener(actionPerformed -> {
                boolean proceed = canProceed(doNotProceed, this, studentExists);
                if (!proceed)
                    return;
                boolean success = database.deleteStudent(firstNameTextField.getText().trim(), lastNameTextField.getText().trim());
                performAction(this, success);
            });
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
                boolean proceed = canProceed(doNotProceed, this, studentExists);
                if (!proceed)
                    return;
                boolean success = database.deleteStudent(matricNumberTextField.getText().trim());
                performAction(this, success);
            });
        }

    }

    protected static void performAction(JFrame frame, boolean success) {
        if (!success) {
            JOptionPane.showMessageDialog(frame, "There's a problem with your network, please try again.");
            return;
        }
        String[] options = {"Proceed to Home", "Exit application"};
        int optionChosen = JOptionPane.showOptionDialog(frame, "Delete Operation successful.", "Delete Operation Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if (optionChosen == 0) {
            frame.dispose();
            new ApplicationFrame();
            return;
        }
        frame.dispose();
    }

    protected static boolean canProceed(boolean doNotProceed, JFrame frame, boolean studentExists) {
        boolean result = false;
        if (!doNotProceed) {
            if (studentExists) {
                int confirmDialog = JOptionPane.showConfirmDialog(frame, "Are you sure you want to perform this operation ?");
                if (confirmDialog == 0) {
                    result = true;
                }
            }
        }
        return result;
    }
}

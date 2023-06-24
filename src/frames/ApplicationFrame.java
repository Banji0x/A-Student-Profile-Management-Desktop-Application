package frames;

import frames.crudframes.*;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() throws HeadlessException {
        super("Student Profile Management System");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 100);
        setLocationRelativeTo(null);

        var jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());

        var addButton = new JButton("Add Student");
        addButton.addActionListener(actionEvent -> {
            dispose();
            new PostStudentFrame();
        });

        var getAllButton = new JButton("Fetch Student");
        getAllButton.addActionListener(actionEvent -> {
            String[] options = {"Fetch Student By First and Last Name", "Fetch student by matric number", "Fetch all Students"};
            int optionPicked = JOptionPane.showOptionDialog(this, "Please select an option", "Option Dialog", DEFAULT_OPTION, PLAIN_MESSAGE, null, options, null);
            if (optionPicked == 0) {
                dispose();
                new AbstractGetStudentFrame.FetchStudentByFirstAndLastNameFrame();
            } else if (optionPicked == 1) {
                dispose();
                new AbstractGetStudentFrame.FetchStudentByMatricNumberFrame();
            } else if (optionPicked == 2) {
                dispose();
                new AbstractGetStudentFrame.GetAllStudentDetailsFrame();
            }
        });

        var updateButton = new JButton("Update Student");
        updateButton.addActionListener(actionEvent -> {
            String[] options = {"Update by first name and last name", "Update by matric number"};
            int optionPicked = JOptionPane.showOptionDialog(this, "Please select an option", "Update Option Dialog", DEFAULT_OPTION, PLAIN_MESSAGE, null, options, null);
            if (optionPicked == 0) {
                dispose();
                new UpdateStudentFrame.UpdateStudentFrameByFullName();
            } else if (optionPicked == 1) {
                dispose();
                new UpdateStudentFrame.UpdateStudentFrameByMatricNumber();
            }
        });

        var deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(actionEvent -> {
            //delete functionality
            String[] options = {"Delete by first name and last name", "Delete by matric number"};
            int optionPicked = JOptionPane.showOptionDialog(this, "Please select an option", "Delete Option Dialog", DEFAULT_OPTION, PLAIN_MESSAGE, null, options, null);
            if (optionPicked == 0) {
                dispose();
                new AbstractDeleteStudentFrame.DeleteStudentByName();
            } else if (optionPicked == 1) {
                dispose();
                new AbstractDeleteStudentFrame.DeleteStudentByMatricNumber();
            }
        });

        var buttonPanel = new JPanel();
        buttonPanel.add(getAllButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        jPanel.add(buttonPanel, gbc);

        this.add(jPanel);
//        pack();
        this.setVisible(true);
    }
}

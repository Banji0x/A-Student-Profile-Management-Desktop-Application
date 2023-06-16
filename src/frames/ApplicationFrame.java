package frames;

import frames.crudframes.AddStudentFrame;

import javax.swing.*;
import java.awt.*;

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
            new AddStudentFrame();
        });

        var getAllButton = new JButton("Get All Students");
        getAllButton.addActionListener(actionEvent -> {
            //get all student functionality
        });

        var updateButton = new JButton("Update Student");
        updateButton.addActionListener(actionEvent -> {
            //update functionality
        });

        var deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(actionEvent -> {
            //delete functionality
        });

        var buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(getAllButton);

        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        jPanel.add(buttonPanel, gbc);

        this.add(jPanel);
//        pack();
        this.setVisible(true);
    }
}

package frames.crudframes;

import listener.TextFieldKeyListener;
import validator.Validator;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public abstract class AbstractStudentByMatricNumberFrame extends JFrame {
    protected boolean validationIssues;
    protected JButton submitButton;

    public AbstractStudentByMatricNumberFrame(String title) throws HeadlessException {
        super(title);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 130);
        setLocationRelativeTo(null);
        var studentPanel = new JPanel(new GridBagLayout());
        var gbc = new GridBagConstraints();
//            gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        var matricNumberLabel = new JLabel("Matric Number: ");
        var matricNumberTextField = new JTextField(10);
        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 0;
        studentPanel.add(matricNumberLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        studentPanel.add(matricNumberTextField, gbc);
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        gbc.gridx = 0;
        studentPanel.add(submitButton, gbc);
        this.add(studentPanel, CENTER);

        matricNumberTextField.addKeyListener(new TextFieldKeyListener(submitButton)); //for ease

        submitButton.addActionListener(actionPerformed -> {
            //validation
            Object[] objects = new Validator.BlankFieldsValidator().validate(new JTextField[]{matricNumberTextField});
            var isBlank = (boolean) objects[0];
            var blankField = (JTextField) objects[1];
            if (isBlank) {
                JOptionPane.showMessageDialog(this, "Field cannot be blank !!!");
                blankField.requestFocus(); // Set focus back to the component
                validationIssues = true;
                return;
            } else
                validationIssues = false;

            boolean valid = new Validator.MatricNumberValidator().validate(matricNumberTextField.getText().trim());
            if (!valid) {
                JOptionPane.showMessageDialog(this, "Matric number is not valid !!!");
                matricNumberTextField.requestFocus(); // Set focus back to the component
                return;
            } else
                validationIssues = false;
            //end of validation
        });
    }
}

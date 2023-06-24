package frames.crudframes;

import buttons.CustomJButton;
import database.Database;
import listener.TextFieldKeyListener;
import validator.Validator;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public abstract class AbstractStudentByMatricNumberFrame extends JFrame {
    protected boolean studentExists;
    protected Database database;
    protected JTextField matricNumberTextField;
    protected boolean doNotProceed;
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
        matricNumberTextField = new JTextField(10);
        submitButton = new CustomJButton("Submit");

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
        database = new Database();
        database.connect(this);
        submitButton.addActionListener(actionPerformed -> {
            //validation
            boolean isBlank = Validator.BlankFieldsExistsValidator(this, new JTextField[]{matricNumberTextField});
            if (isBlank) {
                doNotProceed = true;
                return;
            } else
                doNotProceed = false;

            boolean valid = Validator.matricNumberValidator(this, matricNumberTextField);
            if (!valid) {
                doNotProceed = true;
                return;
            } else
                doNotProceed = false;
            if (!database.studentExists(matricNumberTextField.getText().trim())) {
                studentExists = false;
                Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
                JOptionPane.showMessageDialog(this, "Student not found !!!");
                matricNumberTextField.requestFocus(); // Set focus back to the matric number text field
            } else
                studentExists = true;
            //end of validation
        });
    }
}

package frames.crudframes;

import buttons.CustomJButton;
import database.Database;
import listener.TextFieldKeyListener;
import model.StudentDto;
import validator.Validator;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public abstract class AbstractStudentByFirstAndLastNameFrame extends JFrame {
    protected final JTextField firstNameTextField, lastNameTextField;
    protected final JLabel firstNameLabel, lastNameLabel;
    protected final JButton submitButton;
    protected final JPanel studentPanel;
    protected boolean studentExists;
    protected boolean doNotProceed;
    protected StudentDto studentDto;
    protected Database database;

    public AbstractStudentByFirstAndLastNameFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 230);
        setLocationRelativeTo(null);
        database = new Database();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        firstNameLabel = new JLabel("First name: ");
        lastNameLabel = new JLabel("Last name: ");
        firstNameTextField = new JTextField(15);
        lastNameTextField = new JTextField(15);

        ((PlainDocument) firstNameTextField.getDocument()).setDocumentFilter(Validator.textValidator());
        ((PlainDocument) lastNameTextField.getDocument()).setDocumentFilter(Validator.textValidator());

        studentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        studentPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        studentPanel.add(firstNameTextField, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        studentPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        studentPanel.add(lastNameTextField, gbc);

        submitButton = new CustomJButton("Submit");
        submitButton.addActionListener(actionPerformed -> {
            boolean isBlank = Validator.BlankFieldsExistsValidator(this, new JTextField[]{firstNameTextField, lastNameTextField});
            if (isBlank) {
                doNotProceed = true;
                return;
            }
            doNotProceed = false;
            studentDto = new StudentDto(firstNameTextField, lastNameTextField);

            studentExists = database.studentExists(studentDto.firstNameTextField().getText().trim(), studentDto.lastNameTextField().getText().trim());
            if (!studentExists) { //student not found
                Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
                setVisible(true);
                JOptionPane.showMessageDialog(this, "Student not found !!!");
                studentDto.firstNameTextField().requestFocus(); // Set focus back to the first name text field
            }
        });
//
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        submitButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size for the login button

        studentPanel.add(submitButton, gbc);
        studentPanel.revalidate();
        studentPanel.repaint();
        add(studentPanel, CENTER);
        lastNameTextField.addKeyListener(new TextFieldKeyListener(submitButton));
        database.connect(this);
        pack();
    }
}

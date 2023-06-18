package frames.crudframes;

import listener.TextFieldKeyListener;
import validator.Validator;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;

public abstract class AbstractStudentByNameFrame extends JFrame {
    protected final JTextField firstNameTextField, lastNameTextField;
    protected final JLabel firstNameLabel, lastNameLabel;
    protected final JButton submitButton;
    protected final JPanel studentPanel;

    public AbstractStudentByNameFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 230);
        setLocationRelativeTo(null);
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

        submitButton = new JButton("Submit");
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        submitButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size for the login button

        studentPanel.add(submitButton, gbc);
        studentPanel.revalidate();
        studentPanel.repaint();

        lastNameTextField.addKeyListener(new TextFieldKeyListener(submitButton));
    }
}

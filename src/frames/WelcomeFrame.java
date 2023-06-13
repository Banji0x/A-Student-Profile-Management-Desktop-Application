package frames;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JFrame {
    private final JPanel welcomePanel;
    private final JLabel usernameLabel, passwordLabel;
    private final JTextField usernameTextField;
    private final JPasswordField passwordField;

    public WelcomeFrame() throws HeadlessException {
        super("Welcome");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null); //centers this frame
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; //first column
        gbc.gridy = 0; //first row
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets for spacing

        usernameLabel = new JLabel("Username:");
        gbc.anchor = GridBagConstraints.WEST;
        welcomePanel.add(usernameLabel, gbc);

        gbc.gridx = 1; // second column
        usernameTextField = new JTextField(15);
        welcomePanel.add(usernameTextField, gbc);

        gbc.gridx = 0;//first column
        gbc.gridy = 1; //second row
        passwordLabel = new JLabel("Password: ");
        welcomePanel.add(passwordLabel, gbc);

        gbc.gridx = 1; //second column
        passwordField = new JPasswordField(15);
        welcomePanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size for the login button

        //listener
        loginButton.addActionListener(actionPerformed -> {
            if (validateLogin(usernameTextField.getText().trim(), String.valueOf(passwordField.getPassword()))) {
                this.dispose();
                new ApplicationFrame();
            } else
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        });

        gbc.gridx = 0; //first column
        gbc.gridy = 2; //third row
        gbc.gridwidth = 2; //sets the
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 5, 5);
        welcomePanel.add(loginButton, gbc);

        this.add(welcomePanel);
        this.setVisible(true);
//        pack();
    }

    private boolean validateLogin(String principal, String password) {
        return principal.equals("admin") && password.equals("admin#345");
    }
}
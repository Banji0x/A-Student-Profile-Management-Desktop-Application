package frames;

import javax.swing.*;
import java.awt.*;

import static database.Database.connectToDb;

public class ConfirmationFrame extends JFrame {
    private JTextArea outputTextArea;

    public ConfirmationFrame() throws HeadlessException {
        super("Confirmation Page ");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false); //the data fetched that is being displayed shouldn't be editable
        JScrollPane scrollPane = new JScrollPane(outputTextArea); //makes the output pane scrollable
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(scrollPane);
        this.add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        connectToDb();
    }
}

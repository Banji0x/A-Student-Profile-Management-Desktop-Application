package frames;

import database.Database;
import frames.crudframes.AddStudentFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ConfirmationFrame extends JFrame {
    private Database database;

    public ConfirmationFrame(ArrayList<JTextField[]> list, AddStudentFrame studentFrame) throws HeadlessException {
        super("Confirmation Page");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
//        database.connect();
        JPanel confirmationPanel = new JPanel(new BorderLayout());

        JTable table = createTable(list);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            boolean connected = database.isConnected();
            if (!connected) {
                database.connect(); //try to connect again
                boolean connected1 = database.isConnected();
                if (!connected1) {
                    JOptionPane.showMessageDialog(this, "There's an error with your network connection.");
                    return;
                }
            }
            boolean success = database.saveAll(/* data will be passed in here*/);
            if (!success) {
                boolean saved = database.saveAll(/* data will be passed in here*/);//try to save it again
                if (!saved) {
                    JOptionPane.showMessageDialog(this, "An error occurred while persisting to database.Kindly try again");
                    return;
                }
            }
            studentFrame.dispose();
            JOptionPane.showMessageDialog(this, "Student's data inputted were persisted successfully.");
            dispose();
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            dispose();
            studentFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(proceedButton);
        buttonPanel.add(editButton);

        confirmationPanel.add(scrollPane, BorderLayout.CENTER);
        confirmationPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(confirmationPanel);
        setVisible(true);
    }

    private JTable createTable(ArrayList<JTextField[]> list) {
        String[] columnHeaders = {"First Name", "Last Name", "Age", "Matric Number", "Department", "Faculty"};
        Object[][] rowData = new Object[list.size()][6];

        for (int i = 0; i < list.size(); i++) {
            JTextField[] fields = list.get(i);
            for (int j = 0; j < fields.length; j++) {
                rowData[i][j] = fields[j].getText().trim();
            }
        }

        JTable table = new JTable(rowData, columnHeaders);
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));

        return table;
    }
}

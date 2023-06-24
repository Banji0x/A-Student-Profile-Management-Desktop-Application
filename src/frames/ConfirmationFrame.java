package frames;

import database.Database;
import enums.OperationEnums;
import frames.crudframes.AbstractStudentOperationsFrame;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static enums.OperationEnums.*;
import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;


public class ConfirmationFrame extends JFrame {
    protected JTable outputTable;
    private Database database;

    public ConfirmationFrame(List<JTextField[]> list, AbstractStudentOperationsFrame studentFrame, OperationEnums operationEnum) throws HeadlessException {
        super("Confirmation Page");
        database = new Database();
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        JPanel confirmationPanel = new JPanel(new BorderLayout());

        outputTable = createTable(list);
        outputTable.setEnabled(false); //by default it's false...

        JScrollPane scrollPane = new JScrollPane(outputTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            database.connect(this);
            List<Student> studentList = list
                    .stream()
                    .map(textFieldArray -> new Student(textFieldArray[0].getText().trim(), textFieldArray[1].getText().trim(), textFieldArray[2].getText().trim(), textFieldArray[3].getText().trim(), textFieldArray[4].getText().trim(), textFieldArray[5].getText().trim()))
                    .toList();

            boolean success = false;
            if (operationEnum.equals(POST)) {
                success = database.saveAll(studentList);
            } else if (operationEnum.equals(UPDATE)) {
                success = database.updateAll(studentList);
            } else if (operationEnum.equals(DELETE)) {
                success = database.deleteALl(studentList);
            }
            if (!success) {
                JOptionPane.showMessageDialog(this, "An error occurred while persisting to database.Kindly try again at a later time");
                return;
            }
            studentFrame.dispose();
            String[] options = {"Proceed to Home", "Exit Application"};
            int optionPicked = JOptionPane.showOptionDialog(this, "Student's data was persisted successfully. Kindly choose an option", "Action Dialog", DEFAULT_OPTION, PLAIN_MESSAGE, null, options, null);
            dispose();
            if (optionPicked == 0) new ApplicationFrame();
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e ->

        {
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

    private JTable createTable(List<JTextField[]> list) {
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

//    public JTable getOutputTable() {
//        return outputTable;
//    }

//    public void setOutputTable(JTable outputTable) {
//        this.outputTable = outputTable;
//    }
}


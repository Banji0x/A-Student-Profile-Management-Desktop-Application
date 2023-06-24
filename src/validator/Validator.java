package validator;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Validator extends DocumentFilter {
    private String acceptedNumberRegex = "\\d+";
    private String acceptedTextRegex = "[a-zA-Z]+";
    private static String matricNumberRegex = "^\\d{2}/[0-9a-zA-Z]{2}[A-Z]{2}\\d{3}$\n";
    private boolean isNumber;

    public static Validator textValidator() {
        return new Validator();
    }

    public static Validator numberValidator() {
        return new Validator(true);
    }

    public static boolean matricNumberValidator(JFrame frame, JTextField text) { //returns true if the supplied matric number is valid
        if (text.getText().trim().matches(matricNumberRegex)) return true;
        if (text.getText().trim().equals("test001"))  // this should be deleted to utilize the dynamic implementation
            return true;
        Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
        JOptionPane.showMessageDialog(frame, "Matric number format is invalid!!!");
        text.requestFocus(); // Set focus back to the matric number textField
        return false;
    }

    public static boolean BlankFieldsExistsValidator(JFrame frame, JTextField[] textFields) { //returns true if a blank field exists
        AtomicReference<JTextField> blankField = new AtomicReference<>();
        boolean aFieldIsBlank = Arrays.stream(textFields).anyMatch(jTextField -> {
            if (jTextField.getText().isBlank()) {
                blankField.set(jTextField);
                return true;
            }
            return false;
        });
        if (!aFieldIsBlank) return false;
        Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
        JOptionPane.showMessageDialog(frame, "Field cannot be blank !!!");
        blankField.get().requestFocus(); // Set focus back to the matric number textField
        return true;
    }

    public static boolean BlankFieldsExistsValidator(JFrame frame, List<JTextField[]> listOfFields) {
        JTextField[] textField = listOfFields.get(listOfFields.size() - 1); //the last item in the list will be retrieved and validation will be performed on it
        for (int i = 0; i < textField.length; i++) {
            if (BlankFieldsExistsValidator(frame, textField)) {
                return true;
            }
        }
        return false;
    }

    private Validator(boolean isNumber) {
        this.isNumber = isNumber;
    }

    private Validator() {
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.isBlank()) {
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
            return;
        }
        if (isNumber) {
            if (text.matches(acceptedNumberRegex)) {
                super.replace(fb, offset, length, text, attrs);
                return;
            }
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
            return;
        }
        if (text.matches(acceptedTextRegex)) super.replace(fb, offset, length, text, attrs);
        else Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.isBlank()) {
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
            return;
        }
        if (isNumber) {
            //validate number
            if (string.matches(acceptedNumberRegex)) {
                super.insertString(fb, offset, string, attr);
                return;
            }
            //invalid output...
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
            return;
        }
        if (string.matches(acceptedTextRegex)) super.insertString(fb, offset, string, attr);
        else Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
    }

}
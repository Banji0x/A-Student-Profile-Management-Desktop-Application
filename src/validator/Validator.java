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
//        Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
//        JOptionPane.showMessageDialog(frame, "Matric number is in an invalid format!!!");
//        text.requestFocus(); // Set focus back to the matric number textField
//        return false;
        return true;
    }

    public static boolean BlankFieldsValidator(JFrame frame, JTextField[] textFields) { //returns true if a blank field exists
        AtomicReference<JTextField> blankField = new AtomicReference<>();
        boolean anyMatch = Arrays.stream(textFields).anyMatch(jTextField -> {
            if (jTextField.getText().isEmpty() || jTextField.getText().isBlank()) {
                blankField.set(jTextField);
                return true;
            }
            return false;
        });
        if (!anyMatch) return false;
        Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
        JOptionPane.showMessageDialog(frame, "Field cannot be blank !!!");
        blankField.get().requestFocus(); // Set focus back to the matric number textField
        return true;
    }

    public static boolean BlankFieldsValidator(JFrame frame, List<JTextField[]> listOfFields) {
        for (JTextField[] textFieldsArray : listOfFields) {
            return BlankFieldsValidator(frame, textFieldsArray);
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
//        if (isMatricNumber) {
//            if (string.matches(matricNumberRegex)) {
//                super.insertString(fb, offset, string, attr);
//                return;
//            }
//            //invalid output...
//            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
//            return;
//        }
        if (string.matches(acceptedTextRegex)) super.insertString(fb, offset, string, attr);
        else Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
    }

}

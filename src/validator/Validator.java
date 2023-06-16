package validator;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class Validator extends DocumentFilter {
    private String numberRegex = "\\d+";
    private String textRegex = "[a-zA-Z]+";
    private boolean isNumber;
//    private boolean isMatricNumber;

//    public static Validator matricNumberValidator() {
//        Validator validator = new Validator();
//        validator.isMatricNumber = true;
//        return validator;
//    }

    public static Validator textValidator() {
        return new Validator();
    }

    public static Validator numberValidator() {
        return new Validator(true);
    }

    private Validator(boolean isNumber) {
        this.isNumber = isNumber;
    }

    private Validator() {
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isNumber) {
            if (text.matches(numberRegex)) {
                super.replace(fb, offset, length, text, attrs);
                return;
            }
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
            return;
        }
//        if (isMatricNumber) {
//            if (text.matches(matricNumberRegex)) {
//                super.replace(fb, offset, length, text, attrs);
//                return;
//            }
//            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
//            return;
//        }
        if (text.matches(textRegex))
            super.replace(fb, offset, length, text, attrs);
        else
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isNumber) {
            //validate number
            if (string.matches(numberRegex)) {
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
        if (string.matches(textRegex))
            super.insertString(fb, offset, string, attr);
        else
            Toolkit.getDefaultToolkit().beep();  // Beep to indicate invalid input
    }

    public record MatricNumberValidator() {

        public boolean validate(String text) {
            String matricNumberRegex = "^\\d{2}/[0-9a-zA-Z]{2}[A-Z]{2}\\d{3}$\n";
//            return text.matches(matricNumberRegex);
            return true;
        }
    }

    public record BlankFieldsValidator() {
        public Object[] validate(JTextField[] textFields) {
            for (var field : textFields) {
                if (field.getText().isBlank() || field.getText().isEmpty())
                    return new Object[]{true, field};

            }
            return new Object[]{false, null};
        }
    }
}

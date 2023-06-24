package listener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextFieldKeyListener implements KeyListener {

    private final AbstractButton loginButton;

    public TextFieldKeyListener(AbstractButton loginButton) {
        this.loginButton = loginButton;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (((JTextField) e.getComponent()).getText().isEmpty()) {
            return;
        }
        if (keyChar == KeyEvent.VK_ENTER)
            loginButton.doClick();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //do nothing
    }
}

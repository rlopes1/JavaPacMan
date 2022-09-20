import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Input extends JPanel {

    public Input() {
        KeyListener listener =  new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed = " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased = "+ KeyEvent.getKeyText(e.getKeyCode()));
            }
        };
        addKeyListener(listener);
        setFocusable(true);
    }

}

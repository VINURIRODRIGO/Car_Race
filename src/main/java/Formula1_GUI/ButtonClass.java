package Formula1_GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Creating buttons
 *
 */
public class ButtonClass extends JButton {
    public ButtonClass(int x, int y,int width, int height, JFrame frame, String fileName){
        Icon icon = new ImageIcon(fileName);
        setBackground(new Color(185, 144, 120));
        setForeground(new Color(185, 144, 120));
        setBorderPainted(false);
        setContentAreaFilled(false);
        Cursor hand_cursor = new Cursor(Cursor.HAND_CURSOR); // https://kodejava.org/how-do-i-change-the-cursor-shape/
        setCursor(hand_cursor);
        setIcon(icon);
        setBounds(x, y, width, height);
        setVisible(true);
        frame.add(this);
    }
}

package Formula1_GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ScrollPaneClass extends JScrollPane{
    public ScrollPaneClass(JTable table){
        super(table);
        setForeground(new Color(233, 236, 239));
        setBackground(new Color(233, 236, 239));
        setBounds(20, 120, 800, 250);
        MatteBorder border = new MatteBorder(10, 10, 10, 10, Color.BLACK);
        this.setBorder(border);
    }
}

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    public DrawPanel() {
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 5;
        for (int rows = 0; rows < 30; rows++){
            int x = 5;
            for (int cols = 0; cols < 40; cols++){
                g.drawRect(x,y,20,20);
                x += 25;
            }
            y += 25;
        }
    }

}
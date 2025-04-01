import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private boolean[][] grid;

    public DrawPanel() {
        grid = new boolean[30][40];
        for (int row = 0; row < 30; row++){
            for (int col = 0; col < 40; col++){
                double randomizer = Math.random();
                if (randomizer <= 0.3){
                    grid[row][col] = true;
                } else {
                    grid[row][col] = false;
                }
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 5;

        Graphics2D g2 = (Graphics2D) g;

        // Drawing squares 30 x 40
        for (int rows = 0; rows < 30; rows++){
            int x = 5;
            for (int cols = 0; cols < 40; cols++){
                g.drawRect(x,y,20,20);

                if (grid[rows][cols]){
                    g2.setColor(Color.red);
                } else {
                    g2.setColor(Color.darkGray);
                }
                g2.fillRect(x, y, 20,20);
                g2.setColor(Color.BLACK);

                x += 25;
            }
            y += 25;
        }
    }

}
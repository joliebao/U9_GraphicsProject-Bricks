import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener {
    private int[][] grid;

    public DrawPanel() {
        grid = new int[30][40];
        this.addMouseListener(this);
        randomizing();
    }

    public void timedEvent(){
        long time = System.currentTimeMillis();
        System.out.println(time);
        if (time % 150 == 0) {
            randomizing();
        }
    }

    public void randomizing(){
        for (int row = 0; row < 30; row++){
            for (int col = 0; col < 40; col++){
                int randomizer = (int) (Math.random() * 8 + 1);
                grid[row][col] = randomizer;
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

                timedEvent();

                if (grid[rows][cols] == 1){ // if true
                    g2.setColor(Color.red);
                } else if (grid[rows][cols] == 2) {
                    g2.setColor(Color.darkGray);
                } else if (grid[rows][cols] == 3) {
                    g2.setColor(Color.cyan);
                } else if (grid[rows][cols] == 4) {
                    g2.setColor(Color.blue);
                } else if (grid[rows][cols] == 5) {
                    g2.setColor(Color.magenta);
                } else if (grid[rows][cols] == 6) {
                    g2.setColor(Color.orange);
                } else if (grid[rows][cols] == 7) {
                    g2.setColor(Color.yellow);
                }
                g2.fillRect(x, y, 20,20);
                g2.setColor(Color.BLACK);

                x += 25;
            }
            y += 25;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        randomizing();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
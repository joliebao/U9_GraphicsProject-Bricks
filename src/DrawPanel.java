import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.io.File;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener {
    private int[][] grid;
    BrickLayout b = new BrickLayout("src/bricks", 40, true);

    private boolean placed;

    public DrawPanel() {
        grid = new int[30][40];
        this.addMouseListener(this);
        randomizing();
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
        long time = System.currentTimeMillis();
        super.paintComponent(g);
        int y = 5;

        Graphics2D g2 = (Graphics2D) g;

        // Drawing squares 30 x 40
        for (int rows = 0; rows < 30; rows++){
            int x = 5;
            for (int cols = 0; cols < 40; cols++){
                g.drawRect(x,y,20,20);

                g2.setColor(Color.lightGray);
                g2.fillRect(x, y, 20, 20);

                /* Part 1 -- per click
                if (b.checkBrickSpot(rows,cols)) {
                    g2.setColor(Color.blue);
                    g2.fillRect(x, y, 20, 20);
                }
                */

                // Part 2 -- timed event
                if (System.currentTimeMillis() - time == 300) {
                    time = System.currentTimeMillis();
                    dropBricks();
                }

                g2.setColor(Color.black);
                x += 25;
            }
            y += 25;
        }
    }

    public void dropBricks(){
        // Part 2 -- timed event
        b.doOneBrick();
        b.printBrickLayout();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // UNCOMMENT HERE FOR
        /* Part 1!!!
        b.doOneBrick();
        */

        // Part 2
        placed = true;
        System.out.println("clicked");
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
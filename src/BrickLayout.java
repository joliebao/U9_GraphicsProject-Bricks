import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BrickLayout {
    private int l = 1;
    private ArrayList<Brick> bricks;
    private int[][] brickLayout;
    private int cols;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        brickLayout = new int[bricks.size()][cols];
        if (dropAllBricks) {
            while (bricks.size() != 0) {
                doOneBrick();
            }
        }
    }

    public void doOneBrick() {
        int numRowsFilled = 1;
        if (!bricks.isEmpty()) {   // if there are more bricks
            Brick b = bricks.remove(0);

            int currRow = brickLayout.length - 1;
            boolean placed = false;

            while (!placed && currRow >= 0) {
                if (!checkBrickSpot(currRow, b.getStart()) && !checkBrickSpot(currRow, b.getEnd())){
                    for (int k = 0; k < ) // finish this condition to check every row using numRowsFilled amount of times
                    if (currRow - 1 <= brickLayout.length && !checkBrickSpot(currRow - 1,b.getStart()) && !checkBrickSpot(currRow - 1, b.getEnd())) {
                        for (int i = b.getStart(); i <= b.getEnd(); i++) {
                            brickLayout[currRow][i] = 1; // changing value to 1 from beg. to end
                        }
                        placed = true;
                    }
                } else {
                    currRow--;
                    numRowsFilled ++;
                }
            }
            System.out.println();
            System.out.println("(" + l + ")");
            printBrickLayout();
            System.out.println();
            l++;
        }
    }

    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }

    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkBrickSpot(int r, int c) {
        if (brickLayout[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
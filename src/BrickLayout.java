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
    private int currRow;

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

        currRow = brickLayout.length - 1;

        // Drops all bricks at once
//        if (dropAllBricks) {
//            while (bricks.size() != 0) {
//                doOneBrick();
//            }
//        }
    }

    public void doOneBrick() {
        // update the current row so that it checks if current row is applicable instead of the bottom row
        if (!bricks.isEmpty()) {   // if there are more bricks
            Brick b = bricks.removeFirst();
            int start = b.getStart();
            int end = b.getEnd();
            boolean placed = false;

            while (!placed) {
                if (!checkRow(currRow, start, end)){ // IF FALSE!
                    if (currRow > 1 && currRow < brickLayout.length - 1) {
                        currRow++;
                    }
                    if (checkRow(currRow, start, end)) {
                        currRow--;
                    }
                } else {
                    while (checkRow(currRow, start, end)) {
                        if (checkRow(currRow, start, end) && (currRow > 1)) {
                            currRow--;
                        }
                    }
                }
                for (int i = start; i <= end; i++) {
                    brickLayout[currRow][i] = 1;
                }
                placed = true;
            }
        }
    }

    private boolean checkRow(int row, int start, int end){
        for (int s = start; s <= end; s++){
            if (brickLayout[row][s] == 1){
                return true;
            }
        }
        return false;
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
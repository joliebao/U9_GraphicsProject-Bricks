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
        // update the current row so that it checks if current row is applicable instead of the bottom row
        if (!bricks.isEmpty()) {   // if there are more bricks
            Brick b = bricks.removeFirst();
            int start = b.getStart();
            int end = b.getEnd();
            boolean placed = false;

            int currRow = brickLayout.length - 1;

            System.out.println(checkRow(currRow, start, end));
            while (!placed) {
                // determine which row it goes on
                if (!checkBrickSpot(currRow, start) && !checkBrickSpot(currRow, end)){ // <<<<< THIS LINE SEEMS REMOVABLE BUT IT DOESN'T PRINT ANYTHING IF IT'S NOT HERE !????
                    //// ^^^^^^ NOT SURE WHAT TO DO ABOUT THIS!
                    if (currRow > 1 && currRow <= brickLayout.length - 2) {
                        if (!checkRow(currRow, start, end)) {    // if false move down
                            currRow++;
                        } else {       // if true move up
                            currRow--;
                        }
                    }
                } else {    // added this else statement rlly late. Does not work as intended, so need to edit later
                    currRow --;
                }
                // place amount of bricks
                for (int i = start; i <= end; i++) {
                    brickLayout[currRow][i] = 1;
                }
                placed = true;
            }

            //test code
            System.out.println();
            System.out.println("(" + l + ")");
            printBrickLayout();
            System.out.println();
            l++;
        }
    }

    private boolean checkRow(int row, int start, int end){
        for (int s = start; s < end; s++){
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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BrickLayout {
    private int l = 1;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private ArrayList<Brick> bricks2 = new ArrayList<Brick>();
    private int[][] brickLayout;
    private int cols;
    private int currRow;
    private long time;
    private int count = 0;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
            bricks2.add(b);
        }
        brickLayout = new int[30][40];
        currRow = brickLayout.length - 1;

        printBrickLayout();
    }

    public void doOneBrick() { // part 1
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

    public void placeOneBrick(){ // redo
        int start;
        int end;
        if (!bricks.isEmpty()) {
            Brick b = bricks2.removeFirst();
            start = b.getStart();
            end = b.getEnd();

            for (int i = start; i <= end; i++) {
                brickLayout[b.getHeight()][i] = 1;
            }

            printBrickLayout();
        }
    }

    public void fallingBricks(){
        while (bricks.getFirst().getHeight() < 29){
            placeOneBrick();

            for (int j = 0; j < (bricks.size() - bricks2.size()); j++) {
                int start = bricks.get(j).getStart();
                int end = bricks.get(j).getEnd();
                int height = bricks.get(j).getHeight();

                for (int i = start; i <= end; i++) {
                    brickLayout[height][i] = 0;
                }

                bricks.get(j).incrHeight();
                for (int i = start; i <= end; i++) {
                    brickLayout[bricks.get(j).getHeight()][i] = 1;
                }
            }
        }
        printBrickLayout();
    }

    public boolean checkBrickSpot(int r, int c) {
        return brickLayout[r][c] == 1;
    }

    public boolean checkUnderBrick(int r, int c, int length){
        int count = 0;
        for (int i = c; i < c + length; i++){
            if (brickLayout[r+1][c] == 1){
                count++;
            }
        }

        if (count == length){
            return true;
        }
        return false;
    }

    private boolean checkRow(int row){
        for (int c = 0; c < brickLayout[0].length; c++){
            if (brickLayout[row][c] == 1){
                return true;
            }
        }
        return false;
    }

    private boolean checkRow(int row, int start, int end){
        for (int s = start; s <= end; s++){
            if (brickLayout[row][s] == 1){
                return true;
            }
        }
        return false;
    }

    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            System.out.println();
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
        }
        System.out.println();
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
}
public class Brick {
    private int start;
    private int end;
    private int height;

    public Brick(int start, int end) {
        this.start = start;
        this.end = end;
        this.height = 0;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLength(){
        return end - start;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
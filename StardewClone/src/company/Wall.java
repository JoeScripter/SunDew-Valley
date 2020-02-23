package company;

public class Wall extends Entity{

    private boolean passable;

    public Wall(int x, int y, int width, int height, boolean passable) {
        super(x, y, width, height, passable);
    }
}

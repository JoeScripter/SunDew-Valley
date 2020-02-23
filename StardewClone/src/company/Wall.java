package company;

public class Wall extends Entity{

    private boolean passable;

    public Wall(int x, int y, int width, int height, boolean passable) {
        super(x, y, width, height);
        this.passable = passable;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }
}

package application;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player implements Drawable {

    private int x;
    private int y;
    private boolean passable;
    private int speed;
    public static final int PLAYER_TILE_SCALE = 32;

    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;

    private String spritePath = ".\\player.png";
    private BufferedImage sprite;

    public Player(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.passable = false;
        this.speed = speed;
        try {
            sprite = ImageIO.read(new File(spritePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        if (x < 0) {
            this.x = 0;
        } else if (x > Map.xMax - PLAYER_TILE_SCALE) {
            this.x = Map.xMax - PLAYER_TILE_SCALE;
        } else {
            this.x = x;
        }
    }

    public void setY(int y) {
        if (y < 0) {
            this.y = 0;
        } else if (y > Map.yMax - PLAYER_TILE_SCALE) {
            this.y = Map.yMax - PLAYER_TILE_SCALE;
        } else {
            this.y = y;
        }
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

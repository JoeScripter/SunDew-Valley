package gameApplication;

import java.awt.*;

public class Camera implements Updatable{

    private boolean update;

    private Player player;

    private Point center;
    private int width;
    private int height;
    private int xMax;
    private int yMax;

    private int x;
    private int y;
    private int xLowerRight;
    private int yLowerRight;

    private int screenOffsetX;
    private int screenOffsetY;

    private int visibleXStart;
    private int visibleXEnd;
    private int visibleYStart;
    private int visibleYEnd;

    public Camera(Player player, int xMax, int yMax) {
        this.player = player;
        this.xMax = xMax;
        this.yMax = yMax;
        this.width = GameWindow.WIDTH;
        this.height = GameWindow.HEIGHT;
        this.update = true;

        update();
    }

    public void update() {
        if (update || player.isUpdate()) {
            setCenter();
            calculateCorners();
            calculateVisibleIndexes();
            calculateOffset();
            update = false;
        }
    }

    private void calculateCorners() {
        x = center.x - width / 2;
        y = center.y - height / 2;

        xLowerRight = center.x + width / 2;
        yLowerRight = center.y + height / 2;
    }

    private void calculateOffset() {
        screenOffsetX = x;
        screenOffsetY = y;
    }

    public void calculateVisibleIndexes() {
        visibleXStart = x / TileEntity.TILE_SCALE;
        visibleXEnd = (xLowerRight + TileEntity.TILE_SCALE - 1) / TileEntity.TILE_SCALE;
        visibleYStart = y / TileEntity.TILE_SCALE;
        visibleYEnd = (yLowerRight + TileEntity.TILE_SCALE - 1) / TileEntity.TILE_SCALE;
    }

    public void setCenter() {
        int x = player.getCenter().x;
        int y = player.getCenter().y;

        if (x - width / 2 < 0) {
            x = width / 2;
        } else if (x + width / 2 > xMax) {
            x = xMax - width / 2;
        }

        if (y - height / 2 < 0) {
            y = height / 2;
        } else if (y + height / 2 > yMax) {
            y = yMax - height / 2;
        }
        this.center = new Point(x, y);
        update = true;
    }

    public int getScreenOffsetX() {
        return screenOffsetX;
    }

    public int getScreenOffsetY() {
        return screenOffsetY;
    }

    public int getVisibleXStart() {
        return visibleXStart;
    }

    public int getVisibleXEnd() {
        return visibleXEnd;
    }

    public int getVisibleYStart() {
        return visibleYStart;
    }

    public int getVisibleYEnd() {
        return visibleYEnd;
    }

    public boolean isUpdate() {
        return update;
    }

    public Point getCenter() {
        return center;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxLowerRight() {
        return xLowerRight;
    }

    public int getyLowerRight() {
        return yLowerRight;
    }
}

package application;

public class Camera {

    private boolean update;

    private int x;
    private int y;
    private int width;
    private int height;
    private int widthHalf;
    private int heightHalf;
    private int xMax;
    private int yMax;

    private int xUpperRight;
    private int yUpperRight;
    private int xLowerLeft;
    private int yLowerLeft;

    private int screenOffsetX;
    private int screenOffsetY;

    private int drawableXStart = 0;
    private int drawableXEnd = 0;
    private int drawableYStart = 0;
    private int drawableYEnd = 0;

    public Camera(int x, int y, int width, int height, int xMax, int yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.width = width;
        this.height = height;
        this.widthHalf = width / 2;
        this.heightHalf = height / 2;
        setX(x);
        setY(y);
        calculateCorners();
        calculateDrawableBorders();
        calculateOffsets();
    }

    public void update() {
        if (update) {
            calculateCorners();
            calculateDrawableBorders();
            calculateOffsets();
            update = false;
        }
    }

    private void calculateCorners() {
        xUpperRight = Math.min(xMax, x + widthHalf);
        yUpperRight = Math.max(0, y - heightHalf);

        xLowerLeft = Math.max(0, x - widthHalf);
        yLowerLeft = Math.min(yMax, y + heightHalf);
    }

    private void calculateOffsets() {
        screenOffsetX = xLowerLeft;
        screenOffsetY = yUpperRight;
    }

    public int getScreenOffsetX() {
        return screenOffsetX;
    }

    public int getScreenOffsetY() {
        return screenOffsetY;
    }

    private int calculateDrawableXStart() {
        return (xLowerLeft) / Tile.TILE_SCALE;
    }

    private int calculateDrawableXEnd() {
        return (xUpperRight + Tile.TILE_SCALE - 1) / Tile.TILE_SCALE;
    }

    private int calculateDrawableYStart() {
        return (yUpperRight) / Tile.TILE_SCALE;
    }

    private int calculateDrawableYEnd() {
        return (yLowerLeft + Tile.TILE_SCALE - 1) / Tile.TILE_SCALE;
    }

    public void calculateDrawableBorders() {
        drawableXStart = calculateDrawableXStart();
        drawableXEnd = calculateDrawableXEnd();
        drawableYStart = calculateDrawableYStart();
        drawableYEnd = calculateDrawableYEnd();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getxUpperRight() {
        return xUpperRight;
    }

    public int getyUpperRight() {
        return yUpperRight;
    }

    public int getxLowerLeft() {
        return xLowerLeft;
    }

    public int getyLowerLeft() {
        return yLowerLeft;
    }

    public int getDrawableXStart() {
        return drawableXStart;
    }

    public int getDrawableXEnd() {
        return drawableXEnd;
    }

    public int getDrawableYStart() {
        return drawableYStart;
    }

    public int getDrawableYEnd() {
        return drawableYEnd;
    }

    public void setX(int x) {

        if (x - widthHalf < 0) {
            this.x = widthHalf;
        } else if ((x + widthHalf) > xMax) {
            this.x = xMax - widthHalf;
        } else {
            this.x = x;
        }
        update = true;
    }

    public void setY(int y) {

        if (y - heightHalf < 0) {
            this.y = heightHalf;
        } else if (y + heightHalf > yMax) {
            this.y = yMax - heightHalf;
        } else {
            this.y = y;
        }
        update = true;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isUpdate() {
        return update;
    }
}

package gameApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile implements Drawable {

    public static final int TILE_SCALE = 64;

    private static final String defaultTileSpritePath = ".\\tile_sprites\\default.png";
    private static BufferedImage defaultTileSprite;

    private int x;
    private int y;
    private boolean passable;

    public Tile(int x, int y, boolean passable) {
        this.x = x;
        this.y = y;
        this.passable = passable;
    }

    public static BufferedImage getTileSprite() {
        if (defaultTileSprite == null) {
            try {
                defaultTileSprite = ImageIO.read(new File(defaultTileSpritePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defaultTileSprite;
    }

    @Override
    public BufferedImage getSprite() {
        return Tile.getTileSprite();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean isPassable() {
        return passable;
    }
}

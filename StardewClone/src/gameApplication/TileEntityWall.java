package gameApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileEntityWall extends TileEntity {

    public static final int id = 1;

    public static final String spritePath = ".\\tile_sprites\\wall.png";
    private static BufferedImage sprite;

    public TileEntityWall(int x, int y) {
        super(x, y, false);
    }

    public static BufferedImage getTileSprite() {
        if (sprite == null) {
            try {
                sprite = ImageIO.read(new File(spritePath));
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return sprite;
    }

    @Override
    public BufferedImage getSprite() {
        return TileEntityWall.getTileSprite();
    }

    @Override
    public boolean isPassable() {
        return super.isPassable();
    }
}

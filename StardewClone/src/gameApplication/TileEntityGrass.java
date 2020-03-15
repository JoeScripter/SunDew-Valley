package gameApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileEntityGrass extends TileEntity {

    public static final int id = 0;

    public static final String spritePath = ".\\tile_sprites\\grass.png";
    private static BufferedImage sprite;

    public TileEntityGrass(int x, int y) {
        super(x, y, true);
    }

    public static BufferedImage getTileSprite() {
        if (sprite == null) {
            try {
                sprite = ImageIO.read(new File(spritePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sprite;
    }

    @Override
    public BufferedImage getSprite() {
        return TileEntityGrass.getTileSprite();
    }

    @Override
    public boolean isPassable() {
        return super.isPassable();
    }
}

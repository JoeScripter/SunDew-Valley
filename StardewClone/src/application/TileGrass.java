package application;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileGrass extends Tile{

    public static final int id = 0;

    public static final String spritePath = ".\\tile sprites\\grass.png";
    private static BufferedImage sprite;

    public TileGrass(int x, int y) {
        super(x, y, true);
    }

    public static BufferedImage getTileSprite(){
        if(sprite == null){
            try{
                sprite = ImageIO.read(new File(spritePath));
            }catch(IOException e){
                System.out.println(e);
            }
        }
        return sprite;
    }

    @Override
    public BufferedImage getSprite() {
        return TileGrass.getTileSprite();
    }

    @Override
    public boolean isPassable() {
        return super.isPassable();
    }
}

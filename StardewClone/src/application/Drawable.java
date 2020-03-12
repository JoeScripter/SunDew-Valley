package application;

import java.awt.image.BufferedImage;

public interface Drawable {

    BufferedImage getSprite();
    int getX();
    int getY();
    boolean isPassable();
}

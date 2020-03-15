package gameApplication;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private BufferedImage image;
    private Graphics2D graphics;

    public Renderer(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
    }

    public void drawImage(BufferedImage image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    public void drawCrossHair(Point center){
        graphics.setColor(Color.RED);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawLine(center.x - 10, center.y, center.x + 10, center.y);
        graphics.drawLine(center.x, center.y - 10, center.x, center.y + 10);
    }

    public void clear() {
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, image.getWidth(), image.getHeight());
    }
}

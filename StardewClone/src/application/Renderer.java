package application;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private static BufferedImage scene;
    private static Graphics2D graphics;

    public Renderer() {
        if (scene == null) {
            scene = new BufferedImage(GameWindow.WIDTH, GameWindow.HEIGHT, BufferedImage.TYPE_INT_ARGB);
            graphics = scene.createGraphics();
        }

    }

    public static BufferedImage getScene() {
        return scene;
    }

    public static void drawImage(BufferedImage image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    public static void drawPuntik(int x, int y){
        graphics.fillRect(x, y, 1, 1);
    }

    public static void clear() {
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, scene.getWidth(), scene.getHeight());
    }
}

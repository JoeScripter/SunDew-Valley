package gameApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Renderer renderer;
    private GameScreen screen;
    private BufferedImage image;

    public GameWindow() throws HeadlessException {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        renderer = new Renderer(image);
        screen = new GameScreen(image);

        init();
    }

    private void init() {
        setSize(WIDTH, HEIGHT);
        setTitle("SunDew Valley");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().add(screen, BorderLayout.CENTER);
        renderer.clear();

        pack();
    }

    public GameScreen getScreen() {
        return screen;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public BufferedImage getImage() {
        return image;
    }
}

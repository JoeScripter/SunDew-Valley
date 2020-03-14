package gameApplication;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private GameScreen screen;

    public GameWindow() throws HeadlessException {
        setSize(WIDTH, HEIGHT);
        setTitle("SunDew Valley");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        screen = new GameScreen();
        screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().add(screen, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        Renderer.clear();

        pack();
    }

    public GameScreen getScreen() {
        return screen;
    }
}

package application;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameWindow extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private final Set<Character> pressed = new HashSet<Character>();

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

package gameApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel {

    private BufferedImage image;

    public GameScreen(BufferedImage image) {
        this.image = image;

        setPreferredSize(new Dimension(GameWindow.WIDTH, GameWindow.HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        image.flush();
        g.drawImage(image, 0, 0, this);
    }

}

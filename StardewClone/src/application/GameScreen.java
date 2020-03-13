package application;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel {

    private BufferedImage scene;

    public GameScreen() {
        scene = Renderer.getScene();
    }

    @Override
    public void paintComponent(Graphics g) {
        scene.flush();
        g.drawImage(scene, 0, 0, this);
    }

}

package company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display extends JPanel {

    private BufferedImage bitmap;
    private Graphics2D g;

    public Display(BufferedImage b){
        bitmap = b;
    }

    @Override
    public void paintComponent(Graphics g){
        bitmap.flush();
        g.drawImage(bitmap, 0, 0, this);
    }
}

package company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer {

    private Graphics2D g;
    private BufferedImage bitmap;

    public Renderer(BufferedImage b){
        bitmap = b;
        g = bitmap.createGraphics();
    }

    public void putPixel(int x, int y, Color color){
        bitmap.setRGB(x, y, color.getRGB());
    }

    public void drawRectangle(int x, int y, int w, int h, Color color){
        for(int m = y; m <= y+h; m++){
            for(int n = x; n <= x+w; n++){
                putPixel(n, m, color);
            }
        }
    }

    public void drawRectangle(EntityInformation info, Color color){

        int x = info.getX();
        int y = info.getY();
        int width = info.getWidth();
        int height = info.getHeight();

        drawRectangle(x, y, width, height, color);
    }

    public void showImage(String path, int x, int y){
        try {
            BufferedImage image = ImageIO.read(new File(path));
            g.drawImage(image, x, y, null);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void clear(){
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }
}

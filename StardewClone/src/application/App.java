package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App {

    private GameWindow window;
    private Map map;

    private Player p = new Player(640, 640, 7);
    private Camera c;

    public App() {

        init();
        buildWorld();
        startMainLoop();
    }

    private void init(){

        new Renderer();
        window = new GameWindow();
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(e.getKeyChar() == 'a'){
                    p.setLeft(true);
                }
                if(e.getKeyChar() == 'd'){
                    p.setRight(true);
                }
                if(e.getKeyChar() == 's'){
                    p.setDown(true);
                }
                if(e.getKeyChar() == 'w'){
                    p.setUp(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyChar() == 'a'){
                    p.setLeft(false);
                }
                if(e.getKeyChar() == 'd'){
                    p.setRight(false);
                }
                if(e.getKeyChar() == 's'){
                    p.setDown(false);
                }
                if(e.getKeyChar() == 'w'){
                    p.setUp(false);
                }
            }
        });

        window.setVisible(true);
    }

    private void buildWorld(){
        map = new Map();
        c = new Camera(p.getX(), p.getY(), GameWindow.WIDTH, GameWindow.HEIGHT, map.getxMax(), map.getyMax());
    }

    private void startMainLoop(){

        Timer timer = new Timer(16, e -> {//16
            processInput();
            update();
            render();
        });
        timer.start();
    }

    private void processInput(){

        int speed = p.getSpeed();
        if(p.isDown()){
            p.setY(p.getY()+speed);
            if(checkCollision(p)) p.setY(p.getY()-speed);
//            map.getMapTile
        }
        if(p.isUp()){
            p.setY(p.getY()-speed);
            if(checkCollision(p)) p.setY(p.getY()+speed);
        }
        if(p.isRight()){
            p.setX(p.getX()+speed);
            if(checkCollision(p)) p.setX(p.getX()-speed);
        }
        if(p.isLeft()){
            p.setX(p.getX()-speed);
            if(checkCollision(p)) p.setX(p.getX()+speed);
        }
    }

    private void update(){
            c.setX(p.getX());
            c.setY(p.getY());
            c.update();
    }

    private void render(){

        Drawable[][] itemsToDraw = map.getMapTiles();

        int xOffset = c.getScreenOffsetX();
        int yOffset = c.getScreenOffsetY();

        for(int y = c.getDrawableYStart(); y < c.getDrawableYEnd(); y++){
            for(int x = c.getDrawableXStart(); x < c.getDrawableXEnd(); x++){
                Drawable itemToDraw = itemsToDraw[y][x];
                Renderer.drawImage(itemToDraw.getSprite(), itemToDraw.getX()-xOffset,
                        itemToDraw.getY()-yOffset);
            }
        }

        Renderer.drawImage(p.getSprite(),
                p.getX()-xOffset, p.getY()-yOffset);
        window.getScreen().repaint();
    }

    private boolean checkCollision(Player p){
        boolean ret = false;

        int x = p.getX();
        int y = p.getY();

        Point[] corners = new Point[4];
        corners[0] = new Point(x, y);
        corners[1] = new Point(x + Player.PLAYER_TILE_SCALE, y);
        corners[2] = new Point(x, y + Player.PLAYER_TILE_SCALE);
        corners[3] = new Point(x + Player.PLAYER_TILE_SCALE, y + Player.PLAYER_TILE_SCALE);

        for (int i = 0; i < 4; i++) {
            int indexX = corners[i].x/Tile.TILE_SCALE;
            int indexY = corners[i].y/Tile.TILE_SCALE;
            Drawable tile = map.getMapTile(indexX, indexY);
            if(!tile.isPassable()){
                ret = true;
                break;
            }
        }
        return ret;
    }
}

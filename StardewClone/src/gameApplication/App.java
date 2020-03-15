package gameApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App {
    private GameWindow window;
    private Renderer renderer;
    private Map map;
    private Player p;
    private Camera c;
    Drawable[][] itemsToDraw;

    private int remainingSpeedUp;
    private int remainingSpeedDown;
    private int remainingSpeedLeft;
    private int remainingSpeedRight;

    public App() {
        init();
        buildWorld();
        startMainLoop();
    }

    private void init() {
        window = new GameWindow();
        renderer = window.getRenderer();

        window.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyChar() == 'a') {
                    p.setLeft(true);
                }
                if (e.getKeyChar() == 'd') {
                    p.setRight(true);
                }
                if (e.getKeyChar() == 's') {
                    p.setDown(true);
                }
                if (e.getKeyChar() == 'w') {
                    p.setUp(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyChar() == 'a') {
                    p.setLeft(false);
                }
                if (e.getKeyChar() == 'd') {
                    p.setRight(false);
                }
                if (e.getKeyChar() == 's') {
                    p.setDown(false);
                }
                if (e.getKeyChar() == 'w') {
                    p.setUp(false);
                }
            }
        });
        window.setVisible(true);
    }

    private void buildWorld() {
        map = new Map();
        p = new Player(map.getxMax() / 2, map.getyMax() / 2, 7);
        c = new Camera(p, map.getxMax(), map.getyMax());
        itemsToDraw = map.getMapTiles();
        System.out.println(map.getIdsOfSpritesToLoad());
    }

    private void startMainLoop() {
        Timer timer = new Timer(16, e -> {//16
            processInput();
            update();
            render();
        });
        timer.start();
    }

    private void processInput() {
        int speed = p.getSpeed();

        if (p.isUp()) {
            p.setY(p.getY() - speed);
            if (checkCollisionUp(p)) {
                p.setY(p.getY() + speed - remainingSpeedUp);
            }
        }
        if (p.isDown()) {
            p.setY(p.getY() + speed);
            if (checkCollisionDown(p)) {
                p.setY(p.getY() - speed + remainingSpeedDown);
            }
        }
        if (p.isRight()) {
            p.setX(p.getX() + speed);
            if (checkCollisionRight(p)) {
                p.setX(p.getX() - speed + remainingSpeedRight);
            }
        }
        if (p.isLeft()) {
            p.setX(p.getX() - speed);
            if (checkCollisionLeft(p)) {
                p.setX(p.getX() + speed - remainingSpeedLeft);
            }
        }
    }

    private void update() {
        p.update();
        c.update();
    }

    private void render() {
        int xOffset = c.getScreenOffsetX();
        int yOffset = c.getScreenOffsetY();

        for (int y = c.getVisibleYStart(); y < c.getVisibleYEnd(); y++) {

            for (int x = c.getVisibleXStart(); x < c.getVisibleXEnd(); x++) {
                Drawable itemToDraw = itemsToDraw[y][x];
                renderer.drawImage(itemToDraw.getSprite(), itemToDraw.getX() - xOffset,
                        itemToDraw.getY() - yOffset);
            }
        }
        renderer.drawImage(p.getSprite(), p.getX() - xOffset, p.getY() - yOffset);

        window.getScreen().repaint();
    }

    private boolean checkCollisionUp(Player p) {
        if (p.isPassable()) return false;

        Point[] corners = new Point[2];
        corners[0] = new Point(p.getX() + 1, p.getY());
        corners[1] = new Point(p.getX() + Player.PLAYER_TILE_SCALE - 1, p.getY());

        for (Point corner : corners) {
            int x = corner.x / TileEntity.TILE_SCALE;
            int y = corner.y / TileEntity.TILE_SCALE;
            Drawable tile = map.getMapTile(x, y);
            if (!tile.isPassable()) {
                remainingSpeedUp = p.getY() + p.getSpeed() - (tile.getY() + TileEntity.TILE_SCALE);
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionDown(Player p) {
        if (p.isPassable()) return false;

        Point[] corners = new Point[2];
        corners[0] = new Point(p.getX() + 1, p.getY() + Player.PLAYER_TILE_SCALE);
        corners[1] = new Point(p.getX() + Player.PLAYER_TILE_SCALE - 1, p.getY() + Player.PLAYER_TILE_SCALE);

        for (Point corner : corners) {
            int x = corner.x / TileEntity.TILE_SCALE;
            int y = corner.y / TileEntity.TILE_SCALE;
            Drawable tile = map.getMapTile(x, y);
            if (!tile.isPassable()) {
                remainingSpeedDown = tile.getY() - (p.getY() + Player.PLAYER_TILE_SCALE - p.getSpeed());
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionLeft(Player p) {
        if (p.isPassable()) return false;

        Point[] corners = new Point[2];
        corners[0] = new Point(p.getX(), p.getY() + 1);
        corners[1] = new Point(p.getX(), p.getY() + Player.PLAYER_TILE_SCALE - 1);

        for (Point corner : corners) {
            int x = corner.x / TileEntity.TILE_SCALE;
            int y = corner.y / TileEntity.TILE_SCALE;
            Drawable tile = map.getMapTile(x, y);
            if (!tile.isPassable()) {
                remainingSpeedLeft = p.getX() + p.getSpeed() - (tile.getX() + TileEntity.TILE_SCALE);
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionRight(Player p) {
        if (p.isPassable()) return false;

        Point[] corners = new Point[2];
        corners[0] = new Point(p.getX() + Player.PLAYER_TILE_SCALE, p.getY() + 1);
        corners[1] = new Point(p.getX() + Player.PLAYER_TILE_SCALE, p.getY() + Player.PLAYER_TILE_SCALE - 1);

        for (Point corner : corners) {
            int x = corner.x / TileEntity.TILE_SCALE;
            int y = corner.y / TileEntity.TILE_SCALE;
            Drawable tile = map.getMapTile(x, y);
            if (!tile.isPassable()) {
                remainingSpeedRight = tile.getX() - (p.getX() + Player.PLAYER_TILE_SCALE - p.getSpeed());
                return true;
            }
        }
        return false;
    }
}

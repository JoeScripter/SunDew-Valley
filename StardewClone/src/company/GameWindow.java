package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class GameWindow extends JFrame {

    private int width = 800;
    private int height = 600;

    private Display display;
    private BufferedImage bitmap;
    private Renderer renderer;
    private Player player;
    private PlayerCommand playerCommand;

    private boolean isRunning;

    private final Set<Character> pressed = new HashSet<Character>();

    public GameWindow(String title, Player player, PlayerCommand playerCommand) throws HeadlessException {
        super(title);
        this.player = player;
        this.playerCommand = playerCommand;

        init();
    }

    private void init(){

        isRunning = true;

        setSize(this.width, this.height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameWindow.this.setVisible(false);
                GameWindow.this.dispose();
                isRunning = false;
            }
        });

        setupGraphics();

        playerCommand = new PlayerCommand(renderer, display);

        addListener();

        pack();
    }

    private void setupGraphics(){

        bitmap = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        renderer = new Renderer(bitmap);
        display = new Display(bitmap);

        display.setPreferredSize(new Dimension(this.width, this.height));
        getContentPane().add(display, BorderLayout.CENTER);
        display.setFocusable(true);
        renderer.clear();
    }

    private void addListener(){

        display.addKeyListener(new KeyAdapter() {

            @Override
            public synchronized void keyPressed(KeyEvent e){
                pressed.add(e.getKeyChar());
                if (pressed.size() > 1) {
                    int a = 0;
                    char[] chars = new char[pressed.size()];
                    for(char ch : pressed){
                        chars[a] = ch;
                        a++;
                    }
                    playerCommand.updateMovementFlag(player, chars, true);
                }
                else {
                    playerCommand.updateMovementFlag(player, e.getKeyChar(), true);
                }
            }

            @Override
            public synchronized void keyReleased(KeyEvent e) {
                pressed.remove(e.getKeyChar());
                playerCommand.updateMovementFlag(player, e.getKeyChar(), false);
            }
        });
    }

    public boolean isRunning() {
        return isRunning;
    }

    public BufferedImage getBitmap() {
        return bitmap;
    }

    public void setBitmap(BufferedImage bitmap) {
        this.bitmap = bitmap;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public PlayerCommand getPlayerCommand() {
        return playerCommand;
    }

    public void setPlayerCommand(PlayerCommand playerCommand) {
        this.playerCommand = playerCommand;
    }
}

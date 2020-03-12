package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
//        addListener();
    }

    private void initUI(){

        screen = new GameScreen();
        screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().add(screen, BorderLayout.CENTER);
        setLocationRelativeTo(null);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        Renderer.clear();

        pack();
    }

//    private void addListener(){
//
//        screen.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public synchronized void keyPressed(KeyEvent e){
//                pressed.add(e.getKeyChar());
//                if (pressed.size() > 1) {
//                    int a = 0;
//                    char[] chars = new char[pressed.size()];
//                    for(char ch : pressed){
//                        chars[a] = ch;
//                        a++;
//                    }
//                    playerCommand.updateMovementFlag(player, chars, true);
//                }
//                else {
//                    playerCommand.updateMovementFlag(player, e.getKeyChar(), true);
//                }
//            }
//
//            @Override
//            public synchronized void keyReleased(KeyEvent e) {
//                pressed.remove(e.getKeyChar());
//                playerCommand.updateMovementFlag(player, e.getKeyChar(), false);
//            }
//        });
//    }

    public GameScreen getScreen() {
        return screen;
    }
}

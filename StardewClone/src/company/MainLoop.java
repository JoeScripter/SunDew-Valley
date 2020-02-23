package company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLoop {

    private GameWindow gameWindow;
    private Player player1;
    private Timer timer;
    private int delay = 30;

    private PlayerCommand playerCommand;

    public MainLoop(Player player1, GameWindow gameWindow) {

        this.gameWindow = gameWindow;
        this.player1 = player1;
        playerCommand = gameWindow.getPlayerCommand();
    }

    public void start() {

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                endLoopIfProgramNotRunning();
                playerCommand.renderPlayerMovement(player1);
            }
        });
        timer.start();
    }

    private void endLoopIfProgramNotRunning(){
        if(!gameWindow.isRunning()){
            timer.stop();
        }
    }
}

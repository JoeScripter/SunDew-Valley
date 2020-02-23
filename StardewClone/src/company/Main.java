package company;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {

            private GameWindow gameWindow;
            private PlayerCommand playerCommand;
            private Player player1;
            private Wall wall;

            @Override
            public void run() {

                wall = new Wall(100, 200, 60, 20, false);
                player1 = new Player(50, 50, 30, 40, 3);

                gameWindow = new GameWindow("SunDew Valley", player1, playerCommand);

                playerCommand = gameWindow.getPlayerCommand();

                playerCommand.spawnPlayer(player1, gameWindow.getWidth()/2, gameWindow.getHeight()/2);
                playerCommand.spawnEntity(wall);
                playerCommand.addEntity(wall);

                new MainLoop(player1, gameWindow).start();

                gameWindow.setVisible(true);
            }
        });
    }
}

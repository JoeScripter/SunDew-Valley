package company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {

            private GameWindow gameWindow;
            private PlayerCommand playerCommand;
            private Player player1;
            private Wall wall;
            private Wall wall2;
            private Wall wall3;

            @Override
            public void run() {

                wall = new Wall(100, 200, 60, 20, false);
                wall2 = new Wall(200, 200, 180, 50, false);
                wall3 = new Wall(450, 80, 80, 200, false);

                //TODO Why doesn't the image fit the exact pixel dimensions?
                // for now just subtract 1
                player1 = new Player(50, 50, 30-1, 40-1, 5);

                gameWindow = new GameWindow("SunDew Valley", player1, playerCommand);

                playerCommand = gameWindow.getPlayerCommand();

                playerCommand.spawnPlayer(player1, gameWindow.getWidth()/2, gameWindow.getHeight()/2);
                playerCommand.spawnEntity(wall, Color.RED);
                playerCommand.spawnEntity(wall2, Color.RED);
                playerCommand.spawnEntity(wall3, Color.RED);

                playerCommand.addEntity(wall);
                playerCommand.addEntity(wall2);
                playerCommand.addEntity(wall3);

                new MainLoop(player1, gameWindow).start();

                gameWindow.setVisible(true);
            }
        });
    }
}

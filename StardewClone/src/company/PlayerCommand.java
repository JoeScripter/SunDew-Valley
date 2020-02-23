package company;

import java.awt.*;
import java.util.ArrayList;

public class PlayerCommand {

    private Renderer r;
    private Display d;
    private final double sinConstant = 0.71;
    private ArrayList<Entity> nearbyEntities;


    public PlayerCommand(Renderer r, Display d) {
        this.r = r;
        this.d = d;
        nearbyEntities = new ArrayList<>();
    }

    public void spawnEntity(Entity e){
        EntityInformation ei = e.getEntityInformation();
        r.drawRectangle(ei.getX(), ei.getY(), ei.getWidth(), ei.getHeight(), Color.RED);
    }

    public void spawnPlayer(Player p){
        r.showImage(p.getPlayerImagePath(), p.getEntityInformation().getX(), p.getEntityInformation().getY());
    }

    public void spawnPlayer(Player p, int x, int y){
        r.showImage(p.getPlayerImagePath(), x, y);
        p.getEntityInformation().setX(x);
        p.getEntityInformation().setY(y);
    }

    public void updateMovementFlag(Player p, char[] chars, boolean isKeyPressed){

        for(char ch : chars) {
            if (ch == 'a') {
                p.setLeftFlag(isKeyPressed);
            }
            if (ch == 'd') {
                p.setRightFlag(isKeyPressed);
            }
            if (ch == 'w') {
                p.setUpFlag(isKeyPressed);
            }
            if (ch == 's') {
                p.setDownFlag(isKeyPressed);
            }
        }
    }

    public void updateMovementFlag(Player p, char ch, boolean isKeyPressed){
        char[] c = {ch};
        updateMovementFlag(p, c, isKeyPressed);
    }

    public void renderPlayerMovement(Player p) {

        int speed = p.getSpeed();

        if(p.numberOfFlags() == 0){
            return;
        }
        if(p.numberOfFlags() > 1){
            speed = (int)(speed*sinConstant);
        }
        int left = p.isLeftFlag() ? -speed : 0;
        int right = p.isRightFlag() ? +speed : 0;
        int up = p.isUpFlag() ? -speed : 0;
        int down = p.isDownFlag() ? +speed : 0;

        EntityInformation playerInfo = p.getEntityInformation();
        int x = playerInfo.getX();
        int y = playerInfo.getY();
        int width = playerInfo.getWidth();
        int height = playerInfo.getHeight();

        if(collisionAhead(p, right+left, up+down)){
            return;
            //TODO try to make collision pixel perfect
        }

        r.drawRectangle(x, y, width, height, Color.WHITE);
        r.showImage(p.getPlayerImagePath(),x+right+left, y+up+down);
        d.repaint();


        p.getEntityInformation().setX(x+right+left);
        p.getEntityInformation().setY(y+up+down);
    }

    private boolean collisionAhead(Player p, int xDirection, int yDirection){
        boolean result = false;
        for(Entity e : nearbyEntities){
            EntityInformation ei = e.getEntityInformation();
            EntityInformation pei = p.getEntityInformation();
            int playerNextX = pei.getX()+xDirection;
            int playerNextY = pei.getY()+yDirection;
            if(playerNextX+pei.getWidth() >= ei.getX() && playerNextX <= ei.getX()+ei.getWidth() &&
                    playerNextY+pei.getHeight() >= ei.getY() && playerNextY <= ei.getY()+ei.getHeight()){
                result = true;
            }
//
        }
        return result;
    }

    public void addEntity(Entity entity){
        nearbyEntities.add(entity);
    }
}


package company;

import java.awt.*;
import java.util.ArrayList;

public class PlayerCommand {

    private Renderer r;
    private Display d;
    private final double sinConstant = 0.71;
    private ArrayList<Entity> nearbyEntities;

    private int lastSolvedNextX;
    private int lastSolvedNextY;


    public PlayerCommand(Renderer r, Display d) {
        this.r = r;
        this.d = d;
        nearbyEntities = new ArrayList<>();
    }

    public void spawnEntity(Entity e, Color color){
        EntityInformation ei = e.getEntityInformation();
        r.drawRectangle(ei.getX(), ei.getY(), ei.getWidth(), ei.getHeight(), color);
    }

    public void spawnPlayer(Player p){
        r.showImage(p.getPlayerImagePath(), p.getEntityInformation().getX(), p.getEntityInformation().getY());
    }

    public void spawnPlayer(Player p, int x, int y){
        r.showImage(p.getPlayerImagePath(), x, y);
//        r.drawRectangle(x, y, p.getEntityInformation().getWidth(), p.getEntityInformation().getHeight(), Color.BLACK);
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

        int xDirection = right+left;
        int yDirection = up+down;
        int xDirectionTemp = right+left;
        int yDirectionTemp = up+down;

        if(lastSolvedNextX == x+xDirection && lastSolvedNextY == y+yDirection){
            return;
        }

        if(collisionAhead(p, xDirection, yDirection)){
            boolean skip = false;
            lastSolvedNextX = x+xDirection;
            lastSolvedNextY = y+yDirection;
            do {
                if(!p.isLeftFlag() && !p.isRightFlag()){
                    skip = true;
                }
                if(p.isLeftFlag() && !skip){
                    if(xDirection >= 0){
                        xDirection = xDirectionTemp;
                        skip = true;
                    }
                    else {
                        xDirection += 1;
                    }
                }
                if(p.isRightFlag() && !skip){
                    if(xDirection <= 0){
                        xDirection = xDirectionTemp;
                        skip = true;
                    }
                    else {
                        xDirection -= 1;
                    }
                }
                if(p.isUpFlag() && skip){
                    if(yDirection >= 0){
                        yDirection = yDirectionTemp;
                        break;
                    }
                    else {
                        yDirection += 1;
                    }
                }
                if(p.isDownFlag() && skip){
                    if(yDirection <= 0){
                        yDirection = yDirectionTemp;
                        break;
                    }
                    else {
                        yDirection -= 1;
                    }
                }
            }
            while(collisionAhead(p, xDirection, yDirection));
        }

        r.drawRectangle(x, y, width, height, Color.WHITE);
        r.showImage(p.getPlayerImagePath(),x+xDirection, y+yDirection);
//        r.drawRectangle(x+xDirection, y+yDirection, p.getEntityInformation().getWidth(), p.getEntityInformation().getHeight(), Color.BLACK);
        d.repaint();

        p.getEntityInformation().setX(x+xDirection);
        p.getEntityInformation().setY(y+yDirection);
    }

    private boolean collisionAhead(Player p, int xDirection, int yDirection){
        boolean result = false;
        for(Entity e : nearbyEntities){
            if(e.getEntityInformation().isPassable()){
                continue;
            }
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


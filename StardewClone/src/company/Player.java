package company;

public class Player extends Entity{

    private int speed;
    private String playerImagePath = "D:\\Programming\\Java\\StardewClone\\player.png";

    private boolean leftFlag;
    private boolean rightFlag;
    private boolean upFlag;
    private boolean downFlag;

    public Player(int x, int y, int w, int h, int speed){

        super(x, y, w, h);

        this.speed = speed;

        leftFlag = false;
        rightFlag = false;
        upFlag = false;
        downFlag = false;
    }

    public int numberOfFlags(){
        int result = 0;
        if(leftFlag){
            result++;
        }
        if(rightFlag){
            result++;
        }
        if(upFlag){
            result++;
        }
        if(downFlag){
            result++;
        }
        return result;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getPlayerImagePath() {
        return playerImagePath;
    }

    public void setPlayerImagePath(String playerImagePath) {
        this.playerImagePath = playerImagePath;
    }

    public boolean isLeftFlag() {
        return leftFlag;
    }

    public void setLeftFlag(boolean leftFlag) {
        this.leftFlag = leftFlag;
    }

    public boolean isRightFlag() {
        return rightFlag;
    }

    public void setRightFlag(boolean rightFlag) {
        this.rightFlag = rightFlag;
    }

    public boolean isUpFlag() {
        return upFlag;
    }

    public void setUpFlag(boolean upFlag) {
        this.upFlag = upFlag;
    }

    public boolean isDownFlag() {
        return downFlag;
    }

    public void setDownFlag(boolean downFlag) {
        this.downFlag = downFlag;
    }
}

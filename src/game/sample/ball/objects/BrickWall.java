package game.sample.ball.objects;

import java.util.Random;

public class BrickWall extends Wall {
    private int health;
    public BrickWall(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
        health = 3;
    }

    @Override
    public boolean checkBelow(GameObject object) {
        if(object.getLocationY() == getLocationY() + getHeight())
        {
            if(object.getLocationX() + object.getWidth() >= getLocationX() && object.getLocationX() <= getLocationX() + getWidth())
            {
                health--;
                return true;
            }
        }
        return false;
    }
    public int getReward() {
        Random rand = new Random();
        int rnd = rand.nextInt(100);
        if(rnd <= 20)
            return 0;
        else if(rnd<=70)
            return 1;
        return -1;
    }
    public boolean isAlive(){
        return health>=1;
    }
}

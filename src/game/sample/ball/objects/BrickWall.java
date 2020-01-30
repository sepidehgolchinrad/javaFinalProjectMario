package game.sample.ball.objects;

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
    public boolean isAlive(){
        return health>=1;
    }
}

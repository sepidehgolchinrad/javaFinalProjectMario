package game.sample.ball.objects;

public abstract class Wall extends GameObject {
    public Wall(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
    }
    public boolean checkAbove(GameObject object){
        if(object.getLocationY() + object.getHeight() >= getLocationY() && object.getLocationY() + object.getHeight() <= getLocationY() + getHeight())
        {
            return object.getLocationX() + object.getWidth() >= getLocationX() && object.getLocationX() <= getLocationX() + getWidth();
        }
        return false;
    }
    public boolean checkMove(GameObject object) {
        if(object.getLocationY() >= getLocationY() + getHeight() || object.getLocationY() + object.getHeight() <= getLocationY())
            return true;
        return object.getLocationX() >= getWidth() + getLocationX() || object.getLocationX() + object.getWidth() <= getLocationX();
    }
    public abstract boolean checkBelow(GameObject object);

}

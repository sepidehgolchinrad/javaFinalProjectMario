package game.sample.ball.objects;

public class StoneWall extends Wall {
    public StoneWall(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
    }

    @Override
    public boolean checkBelow(GameObject object) {
        if(object.getLocationY() == getLocationY() + getHeight())
        {
            System.out.println("hello");
            return object.getLocationX() + object.getWidth() >= getLocationX() && object.getLocationX() <= getLocationX() + getWidth();
        }
        return false;
    }
}

package game.sample.ball.objects;

public class StoneWall extends Wall {
    public StoneWall(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
    }

    @Override
    public boolean checkBelow(GameObject object) {
        if(object.getLocationY() + object.getHeight() == getLocationY())
        {
            System.out.println("object : " + object.getLocationX() + " " + object.getLocationY() + " " + object.getWidth() + " " + object.getHeight());
            System.out.println("stone: " + getLocationX() + " " + getLocationY() + " " + getWidth() + " " + getHeight());
            return object.getLocationX() + object.getWidth() >= getLocationX() && object.getLocationX() <= getLocationX() + getWidth();
        }
        return false;
    }
}

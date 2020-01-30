package game.sample.ball.objects;

public class Coin extends GameObject {
    public Coin(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
    }
    public boolean checkMove(GameObject object) {
        if(object.getLocationY() >= getLocationY() + getHeight() || object.getLocationY() + object.getHeight() <= getLocationY()) {
            return true;
        }
        return object.getLocationX() >= getWidth() + getLocationX() || object.getLocationX() + object.getWidth() <= getLocationX();
    }
}

package game.sample.ball.objects.enemy;

import game.sample.ball.objects.GameObject;

public class Enemy extends GameObject {
    private boolean alive;
    public Enemy(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
        alive = true;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

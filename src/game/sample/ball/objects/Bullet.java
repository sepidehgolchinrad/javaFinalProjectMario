package game.sample.ball.objects;

import game.sample.ball.GameState;

public class Bullet extends GameObject implements Runnable {
    private int direction;
    private boolean alive;
    public Bullet(int locationX, int locationY, int direction) {
        super(locationX, locationY, 10, 10);
        this.direction = direction;
    }
    @Override
    public void run() {
        alive = true;
        for(int i=0;i<100;i++){
            if(GameState.pause) {
                i--;
                System.out.println("");
                continue;
            }
            try {
                this.setLocationX(this.getLocationX() + 5 * direction);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        alive = false;
    }
    public boolean getAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

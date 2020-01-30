package game.sample.ball.objects.enemy;

import game.sample.ball.GameState;

import java.awt.*;

public class Hedgehog extends Enemy implements Runnable{
    public int direction;
    public Hedgehog(int locationX, int locationY, int direction) {
        super(locationX, locationY, 50, 50);
        this.direction = direction;
    }

    @Override
    public void run() {
       while(true){
           if(GameState.pause) {
               System.out.println("");
               continue;
           }
           if(!getAlive()){
               return;
           }
           try {
               setLocationX(getLocationX() - 5*direction);
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
    public void paintComponent(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.drawRect(getLocationX(), getLocationY(), 50, 50);
        g2d.setColor(Color.RED);
        g2d.fillRect(getLocationX(), getLocationY(), 50, 50);

    }
}

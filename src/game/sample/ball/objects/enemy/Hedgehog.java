package game.sample.ball.objects.enemy;

import java.awt.*;

public class Hedgehog extends Enemy implements Runnable{
    public int direction;
    private int width, height;
    public Hedgehog(int locationX, int locationY, int width, int height, int direction) {
        super(locationX, locationY, width, height);
        this.width = width;
        this.height = height;
        this.direction = direction;
    }

    @Override
    public void run() {
       while(true){
           if(!getAlive()){
               return;
           }
           try {
               setLocationX(getLocationX() - width / 10 * direction);
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
        g2d.drawRect(getLocationX(), getLocationY(), width, height);
        g2d.setColor(Color.RED);
        g2d.fillRect(getLocationX(), getLocationY(), width, height);

    }
}

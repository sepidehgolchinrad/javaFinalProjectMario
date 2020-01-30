package game.sample.ball.objects.enemy;

import game.sample.ball.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hedgehog extends Enemy implements Runnable{
    public int direction;
    private int width, height;
    private BufferedImage rightImage;
    private BufferedImage leftImage;
    public Hedgehog(int locationX, int locationY, int width, int height, int direction) {
        super(locationX, locationY, width, height);
        this.width = width;
        this.height = height;
        this.direction = direction;
        try {

            //rightImage = ImageIO.read(new File("C:\\Users\\koosh\\Desktop\\java\\java\\supermario\\javaFinalProjectMario\\src\\icons\\Super Mario/Beetle Right.gif"));
            rightImage = ImageIO.read(new File("src/icons/Super Mario/Beetle Right.gif"));

            //leftImage = ImageIO.read(new File("C:\\Users\\koosh\\Desktop\\java\\java\\supermario\\javaFinalProjectMario\\src\\icons\\Super Mario/Beetle Left.gif"));
            leftImage = ImageIO.read(new File("src/icons/Super Mario/Beetle Left.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if(direction == 1)
            g2d.drawImage(rightImage, getLocationX(), getLocationY(), getWidth(), getHeight(), null);
        else
            g2d.drawImage(leftImage, getLocationX(), getLocationY(), getWidth(), getHeight(), null);


    }
}

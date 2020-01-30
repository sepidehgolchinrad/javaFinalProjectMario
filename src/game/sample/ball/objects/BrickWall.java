package game.sample.ball.objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BrickWall extends Wall {
    private int health;
    private BufferedImage image;
    public BrickWall(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
        try {
            image = ImageIO.read(new File("C:\\Users\\koosh\\Desktop\\java\\java\\supermario\\javaFinalProjectMario\\src\\icons\\Super Mario/Brick Block.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        health = 3;
    }
    public void paintComponent(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,getLocationX(), getLocationY(), getWidth(), getHeight(), null);
    }

    @Override
    public boolean checkBelow(GameObject object) {
        if(object.getLocationY() <= getLocationY() + getHeight() && object.getLocationY() >= getLocationY())
        {
            if(object.getLocationX() + object.getWidth() >= getLocationX() && object.getLocationX() <= getLocationX() + getWidth())
            {
                health--;
                return true;
            }
        }
        return false;
    }
    public int getReward() {
        Random rand = new Random();
        int rnd = rand.nextInt(100);
        if(rnd <= 20)
            return 0;
        else if(rnd<=70)
            return 1;
        return -1;
    }
    public boolean isAlive(){
        return health>=1;
    }
}

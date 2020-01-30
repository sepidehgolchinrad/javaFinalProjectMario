package game.sample.ball.objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
    public boolean jump;
    public int coins;
    public int lives;
    public int bullets;
    public int direction;
    private int lastPosX;
    private int lastPosY;

    private BufferedImage imageRight;
    private BufferedImage imageLeft;
    public Player(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
        jump = false;
        coins = 0;
        lives = 3;
        direction = 1;
        bullets = 10;
        try {

            imageRight = ImageIO.read(new File("src/icons/Super Mario/Small Mario Right.png"));
            imageLeft = ImageIO.read(new File("src/icons/Super Mario/Small Mario Left.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        String coinsStr = "Coins  " + coins;
        g2d.setFont(g2d.getFont().deriveFont(18.0f));
        g2d.setColor(Color.RED);
        g2d.drawString(coinsStr,  50, 100);
        String livesStr = "Lives  " + lives;
        g2d.setFont(g2d.getFont().deriveFont(18.0f));
        g2d.setColor(Color.RED);
        g2d.drawString(livesStr,  150, 100);
        String bulletsStr = "Bullets  " + bullets;
        g2d.setFont(g2d.getFont().deriveFont(18.0f));
        g2d.setColor(Color.RED);
        g2d.drawString(bulletsStr,  250, 100);
        if(direction == 1){
            g2d.drawImage(imageRight,getLocationX(), getLocationY(), 50, 40, null);
        }
        else
            g2d.drawImage(imageLeft,getLocationX(), getLocationY(), 50, 40, null);
    }

    public void setLastPosX(int lastPosX) {
        this.lastPosX = lastPosX;
    }

    public void setLastPosY(int lastPosY) {
        this.lastPosY = lastPosY;
    }
    public void fall(){
        this.lives--;
        this.setLocationX(this.lastPosX);
        this.setLocationY(this.lastPosY);
    }
}

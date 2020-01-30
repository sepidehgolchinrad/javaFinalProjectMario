package game.sample.ball.objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Coin extends GameObject {

    private BufferedImage image;
    public Coin(int locationX, int locationY,int width, int height) {
        super(locationX, locationY, width, height);
        try {

            //image = ImageIO.read(new File("C:\\Users\\koosh\\Desktop\\java\\java\\supermario\\javaFinalProjectMario\\src\\icons\\Super Mario/Coin.gif"));
            image = ImageIO.read(new File("src/icons/Super Mario/Coin.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean checkMove(GameObject object) {
        if(object.getLocationY() >= getLocationY() + getHeight() || object.getLocationY() + object.getHeight() <= getLocationY()) {
            return true;
        }
        return object.getLocationX() >= getWidth() + getLocationX() || object.getLocationX() + object.getWidth() <= getLocationX();
    }
    public void paintComponent(Graphics g) {

        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,getLocationX(), getLocationY(), getWidth(), getHeight(), null);
    }
}

package game.sample.ball.objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Coin extends GameObject {

    private BufferedImage image;
    public Coin(int locationX, int locationY) {
        super(locationX, locationY, 30, 30);
        try {
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
        g2d.drawImage(image,getLocationX(), getLocationY(), 30, 30, null);
    }
}

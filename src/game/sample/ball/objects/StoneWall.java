package game.sample.ball.objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StoneWall extends Wall {
    private BufferedImage image;
    public StoneWall(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height);
        try {

            //image = ImageIO.read(new File("C:\\Users\\koosh\\Desktop\\java\\java\\supermario\\javaFinalProjectMario\\src\\icons\\Super Mario/Hard Block.png"));
            image = ImageIO.read(new File("src/icons/Super Mario/Hard Block.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            return object.getLocationX() + object.getWidth() >= getLocationX() && object.getLocationX() <= getLocationX() + getWidth();
        }
        return false;
    }

}

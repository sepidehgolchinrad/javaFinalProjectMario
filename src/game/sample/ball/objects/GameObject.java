package game.sample.ball.objects;

import javax.swing.*;
import java.awt.*;

public class GameObject extends JFrame {
    int LocationX;
    int LocationY;
    int Width;
    int Height;

    public GameObject(int locationX, int locationY,int width, int height){
        this.LocationX = locationX;
        this.LocationY = locationY;
        this.Width = width;
        this.Height = height;
    }
    public void paintComponent(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(12, 12, 12));

        g2d.drawRect(LocationX, LocationY, Width, Height);


       g2d.setColor(new Color(31, 21, 1));
       g2d.fillRect(LocationX, LocationY, Width, Height);

    }


    public int getLocationX() {
        return LocationX;
    }

    public void setLocationX(int locationX) {
        LocationX = locationX;
    }

    public int getLocationY() {
        return LocationY;
    }

    public void setLocationY(int locationY) {
        LocationY = locationY;
    }

    @Override
    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    @Override
    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }
}

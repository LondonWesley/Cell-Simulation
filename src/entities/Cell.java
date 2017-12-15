package entities;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class Cell {


    public double x, y, radius ,dy,dx;
    private double thickness;
    private int r = 161, b = 255, g = 206; // Default cell color

    /* For later use
          Math.cos(2* Math.PI/i)*2; Math.sin(i)*2;
    */
    public Cell(double sx, double sy, double sr){
        x = sx;
        y = sy;
        radius = sr;
        thickness = sr/5;

    }

    public void update(){
        move();
    }
    public void setColor(int red,int blue,int green){
        r = red;
        b = blue;
        g = green;
    }
    public  void setThickness(double thick){
        thickness = thick;
    }
    public void render(Graphics2D g2d){
        g2d.setColor(new Color(r - 30,b - 30,g - 30));
        g2d.fill(new Ellipse2D.Double(x - radius/2,y - radius/2 ,radius,radius));
        g2d.setColor(new Color(r,b,g));
        g2d.fill(new Ellipse2D.Double(x - radius/2 + thickness/2,y - radius/2 + thickness/2,radius - thickness,radius - thickness));
    }

    private void move(){
        x+=dx;
        y+=dy;
    }
}

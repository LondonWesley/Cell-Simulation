package entities;

import java.awt.*;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Cell {
    public Path2D membrane;
    public double x, y;
    private Point2D point = new Point2D.Double(20,20);
  public double[] xPoints = {30,30,40,40,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  public double[] yPoints = {30,40,30,30,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    public Cell(double sx, double sy){
        x = sx;
        y = sy;

         membrane = new Path2D.Double();

        for(int i = 0; i<20; i++){
            xPoints[i] = Math.cos(2* Math.PI/i)*2;
            yPoints[i] = Math.sin(i)*2;
        }

        for(int i = 0; i<5; i++){
            if(i == 0){
                membrane.moveTo(xPoints[i],yPoints[i]);
            } else{
                membrane.lineTo(xPoints[i],yPoints[i]);
            }
        }
        membrane.closePath();

    }

    public void update(){

    }
    public void move(){

    }
}

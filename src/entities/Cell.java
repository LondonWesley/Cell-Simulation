package entities;
import framework.Window;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;


public class Cell {


    public double x, y, radius ,dy,dx;
    private double thickness;
    private int r = 161, b = 255, g = 206; // Default cell color


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
        g2d.fill(new Ellipse2D.Double(x - radius/2,y - radius/2 ,radius*2,radius*2));
        for(int n = 0; n<Window.Cells.size();n++){
            Cell cell = Window.Cells.get(n);
        }
        g2d.setColor(new Color(r,b,g));
        g2d.fill(new Ellipse2D.Double(x - radius/2 + thickness/2,y - radius/2 + thickness/2,radius*2 - thickness,radius*2 - thickness));

    }
    public boolean intersecting(Cell cell){
        Point2D selfC = new Point2D.Double(x,y);
        Point2D targetC = new Point2D.Double(cell.x,cell.y);
        double xDist = selfC.getX() - targetC.getX();
        double yDist = selfC.getY() - targetC.getY();
        double dist =  Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2)); //TODO: figure out why selfC.distance(targetC) returns 0.0
        double r1 = this.radius;
        double r2 = cell.radius;

        if(dist>r1+r2) {
            System.out.println("Final Distance" + dist);
            return false;
        }
        else{
            System.out.println("Current Distance" + dist);
            return true;
        }
    }
    public Point2D[] getPointsOfIntersection(Cell cell){ //TODO: make it so this method finds the intersected points
        Point2D selfC = new Point2D.Double(x,y);
        Point2D targetC = new Point2D.Double(cell.x,cell.y);
        double dist = selfC.distance(targetC);
        double r1 = radius;
        double r2 = cell.radius;
        double a = (r1*r1-r2*r2+dist*dist)/(2*dist);
        double h = Math.sqrt(r*r-a*a);
        Point2D[] points = {};
        return points;
    }
    private void move(){
         /* For later use with movement testing
          Math.cos(2* Math.PI/Window.time)*2; Math.sin(Window.time)*2;
        */
        x+=dx;
        y+=dy;
    }
}

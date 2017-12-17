package entities;
import framework.Window;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;


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

        //Outer membrane of cell
        g2d.setColor(new Color(r - 30,b - 30,g - 30));
        g2d.draw(new Ellipse2D.Double(x - radius,y - radius, radius *2,radius*2));
        for(int n = 0; n<Window.Cells.size();n++){
            Cell cell = Window.Cells.get(n);
        }
        g2d.setColor(new Color(r,b,g));
        //g2d.draw(new Ellipse2D.Double(x - radius/2 + thickness/2,y - radius/2 + thickness/2,radius*2 - thickness,radius*2 - thickness));

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
           //System.out.println("Final Distance" + dist);
            return false;
        }
        else{
            //System.out.println("Current Distance" + dist);
            return true;
        }
    }

    public double acossafe(double x) {
        if (x >= +1.0) return 0;
        if (x <= -1.0) return Math.PI;
        return Math.acos(x);
    }
    public Point2D rotate(Point2D pnt1, Point2D pnt2, double ang){
        double tx = pnt1.getX() - pnt2.getX();
        double ty = pnt1.getY() - pnt2.getY();

        double xrot = tx * Math.cos(ang) + ty * Math.sin(ang);
        double yrot = ty * Math.cos(ang) - tx * Math.sin(ang);

        return new Point2D.Double(pnt1.getX() + xrot, pnt1.getY() + yrot);
    }
    public ArrayList<Point2D> getPointsOfIntersection(Cell cell){ //TODO: make it so this method finds the intersected points
        Point2D C = new Point2D.Double();
        Point2D c = new Point2D.Double();
        double R = 0;
        double r = 0;
        double dx, dy;
        ArrayList<Point2D> points = new ArrayList<Point2D>();


        if(radius>cell.radius) {
             return points;
        } else{
            c = new Point2D.Double(this.x, this.y);
            C = new Point2D.Double(cell.x , cell.y);
            r = radius;
            R = cell.radius;

        }
        dx = C.getX() - c.getX();
        dy = C.getY() - c.getY();

        double dist = Math.sqrt( dx*dx + dy*dy );
        double tx = (dx / dist) * R + C.getX();
        double ty = (dy / dist) * R + C.getY();

        Point2D T = new Point2D.Double(tx,ty);



        Point2D cen = new Point2D.Double(C.getX(),C.getY());
        double ang = acossafe((r*r-dist*dist-R*R)/(-2.0*dist*R));
        points.add(rotate(cen,T,ang));
        points.add(rotate(cen,T,-ang));

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

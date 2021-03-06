package entities;
import framework.ControlHandler;
import framework.Main;
import framework.Window;
import utility.Clip;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;


public class Cell {

    private ArrayList<Cell> Cells = Window.Cells;
    private ArrayList<Clip> Clips = new ArrayList<Clip>();
    public double x, y, radius, dy, dx;
    private double thickness;
    private int r = 161, b = 255, g = 206; // Default cell color
    public String name = "";

    public Cell(double sx, double sy, double sr, String nickname) {
        x = sx;
        y = sy;
        radius = sr;
        thickness = 15;
        name = nickname;
    }

    public void update() {
        move();
    }

    public void setColor(int red, int blue, int green) {
        r = red;
        b = blue;
        g = green;
    }

    public void setThickness(double thick) {
        thickness = thick;
    }

    public boolean isInView(){
        Point2D worldpos = new Point2D.Double((ControlHandler.camX/Window.zoom + x),ControlHandler.camY/Window.zoom + y);

        double scrW,scrH;
            scrW = Window.scrW;
            scrH = Window.scrH;





        if(worldpos.getX() + radius>20&&worldpos.getX() - radius<scrW / Window.zoom - 100){
            if(worldpos.getY() + radius>20&&worldpos.getY() - radius<scrH / Window.zoom - 100) {
                return true;
            } else return false;
        } else
            return false;
    }

    public void render(Graphics2D g2d) {

        if(isInView()) {
            clipIntersections(g2d);
            drawcell(g2d);
            closeCellClips(g2d);
            g2d.setClip(null);
            Clips.clear();
        }
    }

    public boolean intersecting(Cell cell) {
        Point2D selfC = new Point2D.Double(x, y);
        Point2D targetC = new Point2D.Double(cell.x, cell.y);
        double xDist = selfC.getX() - targetC.getX();
        double yDist = selfC.getY() - targetC.getY();
        double dist = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2)); //TODO: figure out why selfC.distance(targetC) returns 0.0
        double r1 = this.radius;
        double r2 = cell.radius;

        if (dist > r1 + r2) {
            //System.out.println("Final Distance" + dist);
            return false;
        } else {
            //System.out.println("Current Distance" + dist);
            return true;
        }
    }

    private void clipIntersections(Graphics2D g2d) {
        if (Cells.size() > 1) {
            for (Cell othercell : Cells) {
                //System.out.println(this.name + " USED COLLISION CHECK ON " + othercell.name);
                if (othercell == this) {
                    //System.out.println(this.name + " TRIED TO TOUCH ITSELF");
                } else if (this.intersecting(othercell)) {
                    //System.out.println(this.name + " SUCCESSFULLY TOUCHED " + othercell.name);
                    //find points of intersections between cells
                    ArrayList<Point2D> intersects = this.getPointsOfIntersection(othercell);
                    //System.out.println(this.name + " HAS ACCESSED P.O.I. FROM " + othercell.name);
                    for (Point2D pnt : intersects) {
                        //System.out.println(this.name + " HAS MARKED A POINT");
                        //g2d.setColor(Color.blue);
                        //g2d.drawString(pnt.getX()+"",20,20);
                        // g2d.draw(new Ellipse2D.Double(pnt.getX() - 10,pnt.getY() - 10,20,20));
                    }
                    AffineTransform old = g2d.getTransform();
                    //Makes sure there's atleast 2 points
                    if (intersects.size() > 0) {
                        //System.out.println(this.name + " HAS DRAWN ITS BOX");
                        Point2D pn1 = intersects.get(0),
                                pn2 = intersects.get(1),
                                midPoint;
                        double mx = (pn1.getX() + pn2.getX()) / 2;
                        double my = (pn1.getY() + pn2.getY()) / 2;
                        midPoint = new Point2D.Double(mx, my);
                        double distance = pn1.distance(pn2);
                        double slopeX = (midPoint.getY() - this.y);
                        double slopeY = (midPoint.getX() - this.x);
                        double ang = -Math.atan2(slopeY, slopeX) + Math.PI;

                        g2d.translate(midPoint.getX(), midPoint.getY());
                        g2d.rotate(ang);
                        //g2d.draw(new Rectangle2D.Double(-radius, 0, radius * 2, radius * 2));
                        g2d.clip(new Rectangle2D.Double(-radius, 0, radius * 2, radius * 2));
                        Clips.add(new Clip(ang, midPoint.getX(), midPoint.getY(), distance));

                        g2d.setTransform(old);

                    }
                }
            }
           // System.out.println(this.name + " ENDED TASK");
        }
    }
    public void closeCellClips(Graphics2D g2d){
        AffineTransform original = g2d.getTransform();
        g2d.setColor(new Color(r - 30, b - 30, g - 30));
        for(Clip clip : Clips){
            g2d.translate(clip.x, clip.y);
            g2d.rotate(clip.ang);
            Path2D visclip = new Path2D.Double();
            visclip.moveTo(clip.size / 2, 0);
            visclip.lineTo(-clip.size / 2, 0);
            visclip.lineTo(-clip.size / 2, thickness);
            visclip.lineTo(clip.size / 2, thickness);
            visclip.closePath();
            g2d.fill(visclip);
            g2d.setTransform(original);
        }
    }
    public void drawcell(Graphics2D g2d){
        g2d.setColor(new Color(r - 30,b - 30,g - 30));
        g2d.fill(new Ellipse2D.Double(x - radius,y - radius, radius *2,radius*2));
        g2d.setColor(new Color(r,b,g));
        g2d.fill(new Ellipse2D.Double(x - (radius - thickness), y - (radius - thickness),(radius - thickness)*2,(radius - thickness)*2));
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


            c = new Point2D.Double(this.x, this.y);
            C = new Point2D.Double(cell.x , cell.y);
            r = radius;
            R = cell.radius;


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
        x+=dx;
        y+=dy;
    }
}

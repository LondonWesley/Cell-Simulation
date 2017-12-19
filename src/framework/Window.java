package framework;

import entities.Cell;

import java.awt.*;


import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.JPanel;


public class Window extends JPanel {

    private Enum RUNNING, STOP;

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int scrW = screenSize.width;
    public static int scrH = screenSize.height;
    public static double time = 1;
    public static Point2D cam;
    public static double zoom = 1;

    Cell cellA,cellB;
    public static ArrayList<Cell> Cells = new ArrayList<Cell>();


    public Window() {

        setBackground(new Color(0,0,0));

        cam = new Point2D.Double(0,0);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Schedule(), 1000, 10);

        cellA = new Cell(420,420, 300, "CellA");
        cellB = new Cell(420,420, 200, "CellB");
        Cells.add(cellA);
        Cells.add(cellB);
        cellB.setColor(255,119,101);


    }
    //cell spawning method for later purposes
    public void spawnCell(double x, double y, double r,String n){
        Cells.add(new Cell( x, y, r,n));
    }

    public void paint(Graphics g) {
        super.paint(g);
        //System.out.println("("+cellA.x+ ", " + cellB.x+")");
        cam.setLocation(new Point2D.Double(ControlHandler.camX,ControlHandler.camY));
        Graphics2D g2d = (Graphics2D) g;
        if(ControlHandler.startDrag !=null && ControlHandler.endDrag != null)
            g2d.draw(new Line2D.Double(ControlHandler.startDrag.getX(), ControlHandler.startDrag.getY(), ControlHandler.endDrag.getX(), ControlHandler.endDrag.getY()));

       g2d.translate(cam.getX(),cam.getY());
       g2d.scale(zoom, zoom);
       g2d.translate(ControlHandler.camX,ControlHandler.camY);


        for(int n = 0; n<Cells.size();n++){
            Cell cell = Cells.get(n);
            cell.render(g2d);
            g2d.setColor(Color.RED);
            g2d.fill(new Ellipse2D.Double(cell.x -5,cell.y - 5, 10, 10));
        }


        g2d.dispose();

    }

    class Schedule extends TimerTask {
        public void run(){
            time+= 0.001;
            for(Cell cell : Cells) {
                cell.update();
            }
            cellA.x = ControlHandler.mouseX;
            cellA.y = ControlHandler.mouseY;
           // cellB.y = 420 + 150*Math.sin(time);

            repaint();
        }

    }
}


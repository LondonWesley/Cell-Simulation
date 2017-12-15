package framework;

import entities.Cell;

import java.awt.*;


import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Window extends JPanel {
    private Enum RUNNING, STOP;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public int scrW = screenSize.width;
    public int scrH = screenSize.height;
    private double time = 1;
    public double zoom = 1;

    Cell cellA,cellB;
    ArrayList<Cell> Cells = new ArrayList<Cell>();

    public Window() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Schedule(), 1000, 10);

        cellA = new Cell(220,220, 100);
        cellB = new Cell(220,220, 1);
        Cells.add(cellA);
        Cells.add(cellB);
        cellB.setColor(255,119,81);

    }
    //cell spawning method for later purposes
    public void spawnCell(double x, double y, double r){
        Cells.add(new Cell( x, y, r));
    }

    public void paint(Graphics g) {
        super.paint(g);


        Graphics2D g2d = (Graphics2D) g;
        System.out.println("("+cellA.x+ ", " + cellA.y+")");
       g2d.translate(scrW/2,scrH/2);
       g2d.scale(zoom, zoom);
       g2d.translate(-scrW/2,-scrH/2);

        for(int i = 1; i<360;i+=20){
        }

        for(int n = 0; n<Cells.size();n++){
            Cell cell = Cells.get(n);
            cell.render(g2d);
            //g2d.setColor(Color.RED);
            //g2d.fill(new Ellipse2D.Double(cell.x -5,cell.y - 5, 10, 10));
        }

        g2d.dispose();

    }

    class Schedule extends TimerTask {
        public void run(){

            for(int n = 0; n<Cells.size();n++){
                Cell cell = Cells.get(n);
                cell.update();
            }

            repaint();
        }

    }
}


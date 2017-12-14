package framework;

import entities.Cell;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Window extends JPanel {
    private Enum RUNNING, STOP;

    ArrayList<Cell> Cells = new ArrayList<Cell>();

    public Window() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Schedule(), 1000, 10);

        Cell cellA = new Cell(220,220, 240);
        Cell cellB = new Cell(500,220, 240);
        Cells.add(cellA);
        Cells.add(cellB);
        cellB.setColor(255,119,81);

    }
    //cell spawning method for later purposes
    public void spawnCell(double x, double y, double r){
        Cells.add(new Cell( x, y,  r));
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        for(int i = 1; i<360;i+=20){

        }

        for(int n = 0; n<Cells.size();n++){
            Cell cell = Cells.get(n);
            cell.render(g2d);
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


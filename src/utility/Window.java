package utility;

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

        Cell celly = new Cell(20,20);
        Cells.add(celly);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        for(int i = 1; i<360;i+=20){

        }

        for(int n = 0; n<Cells.size();n++){
            Cell cell = Cells.get(n);
            g2d.fill(cell.membrane);
        }
        g2d.dispose();
    }

    class Schedule extends TimerTask {
        public void run(){

            repaint();
        }

    }
}


package framework;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static ControlHandler controls;

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int scrW = screenSize.width;
    public static int scrH = screenSize.height;
    public double time = 1;
    public double zoom = 1;

    private Main(int x, int y){

        controls = new ControlHandler();

        setSize(x,y);
        setVisible(true);
        setTitle("Cell Simulation v1.0 alpha");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(controls.mouse);
        addMouseWheelListener(controls.mouse);
        addMouseMotionListener(controls.mouse);

        add(new Window());
    }

    public static void main(String[]args){
        new Main(1200,780);
    }
}

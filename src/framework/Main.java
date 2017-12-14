package framework;

import javax.swing.*;

public class Main extends JFrame {


    private Main(int x, int y){
        setSize(x,y);
        setVisible(true);
        setTitle("Cell Simulation v1.0");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Window());
    }

    public static void main(String[]args){
        new Main(1200,780);
    }
}

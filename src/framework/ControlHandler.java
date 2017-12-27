package framework;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;


public class ControlHandler {

    public static double mouseX = 0;
    public static double mouseY = 0;

    public static double camX = 0;
    public static double camY = 0;

    public static double ShiftX = 0;
    public static double ShiftY = 0;


    public static Point2D startDrag, endDrag;


   public MouseAdapter mouse = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            //System.out.println(e.getX());
        }

        @Override
        public void mousePressed(MouseEvent e){
            super.mousePressed(e);
            startDrag = e.getPoint();
            endDrag = null;
            System.out.println("START("+e.getPoint().getX()+", "+e.getPoint().getY()+")");
            mouseX = e.getX();
            mouseY = e.getY();
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            super.mouseWheelMoved(e);
            if(e.getPreciseWheelRotation()>0)
                Window.zoom -=0.1;
            else
                Window.zoom+=0.1;

        }
        @Override
        public void mouseMoved(MouseEvent e){
            super.mouseMoved(e);
            mouseX = e.getX();
            mouseY = e.getY();

        }
        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            endDrag = e.getPoint();
            double c = Window.zoom + 1;
            camX = (endDrag.getX() - startDrag.getX())/c + ShiftX;
            camY = (endDrag.getY() - startDrag.getY())/c + ShiftY;
            mouseX = e.getX();
            mouseY = e.getY();
        }
        @Override
       public void mouseReleased(MouseEvent e){
            super.mouseReleased(e);
            ShiftX = camX;
            ShiftY = camY;
            endDrag = null;
            mouseX = e.getX();
            mouseY = e.getY();
        }
    };

    public ControlHandler(){

    }
}

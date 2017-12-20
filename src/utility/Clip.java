package utility;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Clip {
    public double ang = 0, x = 0, y = 0,size = 0;
    //Stores a clip's to be used later on
    public Clip(double angle, double nx, double ny, double nsize){
        ang = angle;
        x = nx;
        y = ny;
        size = nsize;
    }
}

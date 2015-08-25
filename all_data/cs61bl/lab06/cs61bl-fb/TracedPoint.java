import java.awt.*;
import java.util.*;
public class TracedPoint extends Point {

    public TracedPoint (int x, int y) {
        super (x, y);
    }
    @Override
    public void move(int toX, int toY){
    	int Currentx;
    	int Currenty;
    	Currentx = (int) super.getX();
    	Currenty = (int) super.getY();
    	super.move(toX,toY);
    	System.out.println("point moved from (" + Currentx +"," +Currenty + ") to (" + toX + "," + toY+")" );
    }	

    public static void main (String [ ] args) {
    	
    }
}


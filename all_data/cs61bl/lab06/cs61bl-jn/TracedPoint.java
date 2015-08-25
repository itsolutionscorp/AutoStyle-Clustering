import java.awt.*;
public class TracedPoint extends Point {

    public TracedPoint (int x, int y) {
        super (x, y);
    }

    public void move(int x, int y) {
    		int prevX = (int) getX();
    		int prevY = (int) getY();
    		super.move(x, y);
    		System.out.println("point moved from (" + prevX + "," + prevY + ") to ("+ x + "," + y +")");
    }
    
}
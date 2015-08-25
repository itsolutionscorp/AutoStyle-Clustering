import java.awt.*;
public class TracedPoint extends Point {

    public TracedPoint (int x, int y) {
        super (x, y);
    }

    // Your move method goes here.
    
    public void move(int x, int y) {
    	int xOld = this.x;
    	int yOld = this.y;
    	this.x = x;
    	this.y = y;
    	System.out.print ("point moved from (" + xOld + "," + yOld + ") to (" + this.x + "," + this.y + ")");
    }


}
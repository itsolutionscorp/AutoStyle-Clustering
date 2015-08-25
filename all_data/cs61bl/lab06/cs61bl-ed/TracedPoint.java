import java.awt.*;
import java.util.*;;
public class TracedPoint extends Point {

    public TracedPoint (int x, int y) {
        super (x, y);
    }
    public static void anotherMoveTo79 (TracedPoint tp){
        tp.move(7, 9);
    }
}
    // Your move method goes here.

  
 /*   public static void main (String [ ] args) {
    	Point p = new Point (3, 4);
    	TracedPoint tp = new TracedPoint (5, 6);
    	ArrayList<Object> a = new ArrayList<Object> ( );
    	a.add (p);
    	a.add (tp);
    	// Move both points to (7, 9).
    	for (int k=0; k<a.size( ); k++) {
    		Point p1 = new Point(4,6);
    	    Object p2 = p1;
    		p2.move (7, 9);
    	}
}
}
    */
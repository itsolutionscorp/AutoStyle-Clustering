import java.awt.*;
import java.util.ArrayList;
public class TracedPoint extends Point {

    public TracedPoint (int x, int y) {
        super (x, y);
    }

    // Your move method goes here.
    public void move (int newx, int newy) {
    	System.out.println("point moved from (" + (int) this.getX() + "," + (int) this.getY() + ") to (" + newx + "," + newy + ")");
    	this.x = newx;
    	this.y = newy;
    }
    
    public static void moveTo79 (Point p) {
        p.move (7, 9);
    }

    public static void anotherMoveTo79 (TracedPoint tp){
        tp.move(7, 9);
    }
    
    public static void printPoint (Point p){
        System.out.println(p);
    }
    
    public static void main (String [ ] args) {
//        TracedPoint p1 = new TracedPoint (5, 6);
//        p1.move (3, 4); // prints: "point moved from (5,6) to (3,4)
//        p1.move (9, 10); // prints: "point moved from (3,4) to (9,10)
//
//        TracedPoint p2 = new TracedPoint (25, 30);
//        p2.move (45, 50); // prints: "point moved from (25,30) to (45,50)
//
//        System.out.println ("p1 is " + p1);
//        System.out.println ("p2 is " + p2);
//        
//        
//        Point p = new Point(3, 4);
//        moveTo79(p);
//        System.out.println ("p is " + p);
        
        
        Point p = new Point (3, 4);
        TracedPoint tp = new TracedPoint (5, 6);
        ArrayList<Object> a = new ArrayList<Object> ( );
        a.add (p);
        a.add (tp);
        // Move both points to (7, 9).
        for (int k=0; k<a.size( ); k++) {
        	((Point) a.get(k)).move (7, 9);
        	
        	
        	TracedPoint tp1 = new TracedPoint(2, 3);
        	printPoint(tp1);
        	
        	
        	
       }
    }
}
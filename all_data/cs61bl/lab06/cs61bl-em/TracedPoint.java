import java.awt.*;
public class TracedPoint extends Point {

    public TracedPoint (int x, int y) {
        super (x, y);
    }

    @Override
    public void move(int newx, int newy) {
    	TracedPoint original = new TracedPoint (this.x, this.y); 
    	super.move(newx, newy);
    	System.out.println("point moved from " + original + "to " + this);    	
    }

    @Override
    public String toString() {
    	return "(" + this.x + "," + this.y + ")";
    }
    
    public static void moveTo79 (Point p) {
        p.move (7, 9);
    }    
    
    public static void main (String [ ] args) {    	
        TracedPoint p1 = new TracedPoint (5, 6);
        p1.move (3, 4); // prints: "point moved from (5,6) to (3,4)
        p1.move (9, 10); // prints: "point moved from (3,4) to (9,10)

        TracedPoint p2 = new TracedPoint (25, 30);
        p2.move (45, 50); // prints: "point moved from (25,30) to (45,50)

        System.out.println ("p1 is " + p1);
        System.out.println ("p2 is " + p2);
        Point p3 = new Point(0, 0);
        TracedPoint p4 = new TracedPoint(1, 1);
        moveTo79(p3); //regular Point, doesn't print anything
        moveTo79(p4); //new TracedPoint type, prints moved from... to ...
    }
    
}
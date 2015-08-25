import java.awt.*;
public class TracedPoint extends Point {
	
	public TracedPoint (int x, int y) {
        super (x, y);
    }

    // Your move method goes here.
	public void move(int a, int b) {
		Point newpoint = new TracedPoint(a,b);
		Point oldpoint = new TracedPoint(x,y);
		System.out.println("point moved from " + oldpoint + "to " + newpoint); 
	}

    public static void main (String [ ] args) {
        TracedPoint p1 = new TracedPoint (5, 6);
        p1.move (3, 4); // prints: "point moved from (5,6) to (3,4)
        p1.move (9, 10); // prints: "point moved from (3,4) to (9,10)

        TracedPoint p2 = new TracedPoint (25, 30);
        p2.move (45, 50); // prints: "point moved from (25,30) to (45,50)

        System.out.println ("p1 is " + p1);
        System.out.println ("p2 is " + p2);
        
        TracedPoint p = new TracedPoint(3, 4);
        anotherMoveTo79(p);
    }
    public static void anotherMoveTo79 (Point tp){
        tp.move(7, 9);
    }
    
  

}

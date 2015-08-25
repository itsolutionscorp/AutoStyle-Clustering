import java.awt.*;
public class TracedPoint extends Point {

    public TracedPoint (int x, int y) {
        super (x, y);

    }

    public void move(int newX, int newY) {
    	System.out.print("point moved from " + "(" + this.x + "," + this.y + ") " + "to ");
    	this.x = newX;
    	this.y = newY;
    	System.out.println("(" +this.x + "," + this.y + ")");
    }
  
    public static void moveTo79 (Point p) {
        p.move (7, 9);
    }
    
    public static void anotherMoveTo69 (TracedPoint tp){
        tp.move(6, 9);
    }
    public static void main (String [ ] args) {
        TracedPoint p1 = new TracedPoint (5, 6);
        p1.move (3, 4); // prints: "point moved from (5,6) to (3,4)
        p1.move (9, 10); // prints: "point moved from (3,4) to (9,10)

        TracedPoint p2 = new TracedPoint (25, 30);
        p2.move (45, 50); // prints: "point moved from (25,30) to (45,50)

        System.out.println ("p1 is " + p1);
        System.out.println ("p2 is " + p2);
        Point p3 = new Point(1,2);
        moveTo79(p3);
        System.out.println("p3 is " + p3);
        
        TracedPoint p4 = new TracedPoint(1,3);
        moveTo79(p4);
        System.out.println("p4 is " + p4);
        
    
        

        

    }
    
}
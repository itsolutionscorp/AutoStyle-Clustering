
	 
 
 import java.awt.*;
import java.util.*;
 public class TracedPoint extends Point {

     public TracedPoint (int x, int y) {
         super (x, y);
       //you can also say this(0,0) to initialize a traced point at 0,0 
     }

     public void move (int x, int y) {
    	 
    	 System.out.println("point moved from ("+ (int) super.getX() + "," + (int) super.getY() + ") to (" + x + "," + y + ")"); 
    	 super.move(x, y);
     }
     public static void moveTo79 (Point p) {
    	 p.move(7, 9);
     }
     
     public static void anotherMoveTo79 (TracedPoint tp) {
    	 tp.move(7, 9);
     }


     public static void main (String [ ] args) {
         TracedPoint p1 = new TracedPoint (5, 6);
         p1.move (3, 4); // prints: "point moved from (5,6) to (3,4)
         p1.move (9, 10); // prints: "point moved from (3,4) to (9,10)

         TracedPoint p2 = new TracedPoint (25, 30);
         p2.move (45, 50); // prints: "point moved from (25,30) to (45,50)

         System.out.println ("p1 is " + p1);
         System.out.println ("p2 is " + p2);
         
         moveTo79(p1);
         
         Point p = new Point(3, 4);
         //anotherMoveTo79(p); doesn't work, wrong type
         
         Point p3 = new Point (3, 4);
         TracedPoint tp = new TracedPoint (5, 6);
         ArrayList<Object> a = new ArrayList<Object> ( );
         a.add (p3);
         a.add (tp);
         // Move both points to (7, 9).
         for (int k=0; k<a.size( ); k++) {
        	 Point p4 = (Point) a.get(k); //need to cast it as a point so it knows
        	 p4.move (7, 9);
         }
     }
 }


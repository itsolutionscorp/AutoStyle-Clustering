import java.awt.*;
public class TracedPoint extends Point {
	int firstNum; 
	int secondNum;
    public TracedPoint (int x, int y) {
        super (x, y);
        firstNum = x;
        secondNum = y;
    }
    public void move(int x, int y) {
    	super.move(x, y);
    	String sentence = "Point moved from (" + firstNum + "," + secondNum + ") to ";
    	firstNum = x; 
    	secondNum = y;
    	sentence += "(" + firstNum + "," + secondNum + ")";
    	System.out.println(sentence);
    	
    }
    // Your move method goes here.

    public static void main (String [ ] args) {
        TracedPoint p1 = new TracedPoint (5, 6);
        p1.move (3, 4); // prints: "point moved from (5,6) to (3,4)
        p1.move (9, 10); // prints: "point moved from (3,4) to (9,10)

        TracedPoint p2 = new TracedPoint (25, 30);
        p2.move (45, 50); // prints: "point moved from (25,30) to (45,50)

        System.out.println ("p1 is " + p1);
        System.out.println ("p2 is " + p2);
    }
}
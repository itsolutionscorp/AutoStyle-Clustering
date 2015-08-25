import java.awt.Point;

public class Line2 {
    
    Point p1, p2;
    double length;

    
    void printLength() {
        double x2 = p2.getX();
        double x1 = p1.getX();
        double y1 = p1.getY();
        double y2 = p2.getY();
        length = Math.sqrt ( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double x2 = p2.getX();
        double x1 = p1.getX();
        double y1 = p1.getY();
        double y2 = p2.getY();
        double angleInDegrees = Math.atan2 ( (y2-y1) , (x2-x1) ) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line2");
        Line2 myLine = new Line2();
        myLine.p1 = new Point(5,10);
        myLine.p2 = new Point(45,40);
        myLine.printLength();
        myLine.printAngle();
        
        		
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 to the point (5, 10), and initialize
		 * myLine's p2 to the point (45, 40). Print the line's length, which
		 * should be 50. Print the line's angle, which should be around 36.87
		 * degrees.
		 */
    }

}

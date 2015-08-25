import java.awt.Point;

public class Line4 {
    
    Point[] points;
    
    void printLength() {
    	double x1 = points[0].getX();
        double x2 = points[1].getX();
        double y1 = points[0].getY();
        double y2 = points[1].getY();
        double length;
        length = Math.sqrt ((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
    	double x1 = points[0].getX();
        double x2 = points[1].getX();
        double y1 = points[0].getY();
        double y2 = points[1].getY();
        double angleInDegrees = Math.atan2 ( (y2-y1) , (x2-x1) ) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line4");
        Line4 myLine = new Line4();
        myLine.points = new Point[2];
        myLine.points[0] = new Point(5,10);
        myLine.points[1] = new Point(45,40);
        myLine.printLength();
        myLine.printAngle();
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 (element 0 of points) to the point (5,
		 * 10), and initialize myLine's p2 (element 1 of points) to the point
		 * (45, 40). Print the line's length, which should be 50. Print the
		 * line's angle, which should be around 36.87 degrees.
		 */
    }

}

import java.awt.Point;

public class Line4 {
    
    Point[] points;
    
    void printLength() {
        double length;
        length = Math.sqrt ( Math.pow(points[1].getX() - points[0].getX(), 2) + Math.pow(points[1].getY() - points[0].getY(), 2 )) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 ( points[1].getY() - points[0].getY() , points[1].getX() - points[0].getX() ) * 180.0 / Math.PI;
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

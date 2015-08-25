import java.awt.Point;

public class Line4 {
    
    Point[] points;
    
    void printLength() {
        double length;
        double x1 = points[0].getX();
        double x2 = points[1].getX();
        double y1 = points[0].getY();
        double y2 = points[1].getY();
        length = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double x1 = points[0].getX();
        double x2 = points[1].getX();
        double y1 = points[0].getY();
        double y2 = points[1].getY();
        double angleInDegrees = Math.atan2 (y2-y1, x2-x1) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line4\n");
		
        Line4 line = new Line4();
        line.points = new Point[2];
        line.points[0] = new Point(5, 10);
        line.points[1] = new Point(45, 40);

        line.printLength();
        line.printAngle();
        
        /*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 (element 0 of points) to the point (5,
		 * 10), and initialize myLine's p2 (element 1 of points) to the point
		 * (45, 40). Print the line's length, which should be 50. Print the
		 * line's angle, which should be around 36.87 degrees.
		 */
    }

}

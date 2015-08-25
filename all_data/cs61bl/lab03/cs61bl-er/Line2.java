import java.awt.Point;

public class Line2 {
    
    Point p1, p2;
    
    void printLength() {
        double length;
        length = Math.sqrt (Math.pow((p2.y-p1.y), 2) + Math.pow((p2.x-p1.x),2));
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 ((p2.y-p1.y), (p2.x-p1.x)) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line2");
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 to the point (5, 10), and initialize
		 * myLine's p2 to the point (45, 40). Print the line's length, which
		 * should be 50. Print the line's angle, which should be around 36.87
		 * degrees.
		 */
        Line2 point_calc = new Line2();
        
        point_calc.p1 = new Point(5, 10);
        point_calc.p2 = new Point(45, 40);
        point_calc.printLength();
        point_calc.printAngle();
    }

}

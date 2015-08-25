import java.awt.Point;

public class Line4 {
    
    Point[] points;
    
    void printLength() {
        double length;
        length = Math.sqrt (Math.pow(points[1].x - points[0].x, 2) + Math.pow(points[1].y - points[0].y, 2) ) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 (points[1].y - points[0].y, points[1].x - points[0].x) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line4");
        Line4 myLine = new Line4();
        myLine.points = new Point[2];
        myLine.points[0] = new Point();
        myLine.points[1] = new Point();
        myLine.points[0].x = 5;
        myLine.points[0].y = 10;
        myLine.points[1].x = 45;
        myLine.points[1].y = 40;
        myLine.printLength();
        myLine.printAngle();
    }

}

import java.awt.Point;

public class Line2 {
    
    Point p1, p2;
    
    void printLength() {
        double length;
        length = Math.sqrt (Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2)) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 (p2.y - p1.y , p2.x - p1.x) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line2");
        Line2 myLine = new Line2();
        myLine.p1 = new Point(5, 10);
        myLine.p2 = new Point(45, 40);
        myLine.printLength();
        myLine.printAngle();
      
    }

}

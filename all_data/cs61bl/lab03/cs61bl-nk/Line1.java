public class Line1 {
    
    int x1, y1, x2, y2;
    
    void printLength() {
        double length;
        length = Math.sqrt (Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2) );
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 (y2-y1 , x2-x1) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line1");
        Line1 myLine = new Line1();
        myLine.x1 = 5;
        myLine.y1 = 10;
        myLine.x2 = 45;
        myLine.y2 = 40;
        myLine.printLength();
        myLine.printAngle();
    }

}

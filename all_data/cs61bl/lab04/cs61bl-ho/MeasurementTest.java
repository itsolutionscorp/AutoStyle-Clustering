import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
    public void testConstructor() {
    	Measurement a = new Measurement();
        assertTrue (a.getFeet() == 0);
        assertTrue (a.getInches() == 0);
    	a = new Measurement(100);
        assertTrue (a.getFeet() == 100);
        assertTrue (a.getInches() == 0);
    	a = new Measurement(100,100);
        assertTrue (a.getFeet() == 108);
        assertTrue (a.getInches() == 4);
    	a = new Measurement(100,-100);
        assertTrue (a.getFeet() == 91);
        assertTrue (a.getInches() == 8);
    }
    public void testPlus() {
    	Measurement a = new Measurement(2,10);
    	Measurement b = new Measurement(3,1);
    	assertTrue (a.plus(b).getFeet() == 5);
    	assertTrue (a.plus(b).getInches() == 11);
    	a = new Measurement(2,10);
    	b = new Measurement(3,11);
    	assertTrue (a.plus(b).getFeet() == 6);
    	assertTrue (a.plus(b).getInches() == 9);
    }
    public void testMinus() {
    	Measurement a = new Measurement(3,1);
    	Measurement b = new Measurement(2,10);
    	assertTrue (a.minus(b).getFeet() == 0);
    	assertTrue (a.minus(b).getInches() == 3);
    	a = new Measurement(3,10);
    	b = new Measurement(2,1);
    	assertTrue (a.minus(b).getFeet() == 1);
    	assertTrue (a.minus(b).getInches() == 9);
    }
    public void testMultiple() {
    	Measurement a = new Measurement(0,2);
    	assertTrue (a.multiple(2).getFeet() == 0);
    	assertTrue (a.multiple(2).getInches() == 4);
    	assertTrue (a.multiple(0).getFeet() == 0);
    	assertTrue (a.multiple(0).getInches() == 0);
    	a = new Measurement(2,10);
    	assertTrue (a.multiple(3).getFeet() == 8);
    	assertTrue (a.multiple(3).getInches() == 6);
    }
    public void testToString() {
    	Measurement a = new Measurement(3,1);
    	assertTrue (a.toString().equals("3'1\""));
    	a = new Measurement(3,13);
    	assertTrue (a.toString().equals("4'1\""));

    }

}

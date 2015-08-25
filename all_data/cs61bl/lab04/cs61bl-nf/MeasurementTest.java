import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	
	public void testConstructor(){
		//test the first constructor and the getFeet and getInches method
		Measurement n = new Measurement();
		assertTrue (n.getFeet() == 0);
		assertTrue (n.getInches() == 0);
		//test the second contructor
		n = new Measurement(6);
		assertTrue (n.getFeet() == 6);
		assertTrue (n.getInches() == 0);
		//test the third constructor
		n = new Measurement(3,5);
		assertTrue (n.getFeet() == 3);
		assertTrue (n.getInches() == 5);

	}
	public void testPlus(){		
		//test the plus method
		Measurement a = new Measurement(2,3);
		Measurement n = new Measurement(3,17);
		Measurement c = a.plus(n);
		assertTrue (c.getFeet() == 6);
		assertTrue (c.getInches() == 8);
		}
		
	public void testMinus(){
		//test the minus method
		Measurement a = new Measurement(2,3);
		Measurement n = new Measurement(3,17);
		Measurement c = n.minus(a);
		assertTrue (c.getFeet() == 2);
		assertTrue (c.getInches() == 2);
	}
	public void testToString(){
		//test toString

		Measurement a = new Measurement(2,3);
		assertTrue (a.toString() == "2'3\"");
		}
		
	}


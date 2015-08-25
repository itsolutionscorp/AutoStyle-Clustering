import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testgetFeet(){
		Measurement n = new Measurement(1,3);
		assertTrue (n.getFeet() == 1);
	}
	public void testgetInches(){
		Measurement n2 = new Measurement(1,13);
		Measurement n3 = new Measurement(1,3);
		assertTrue (n2.getInches() == 1);
		assertTrue (n3.getInches() == 3);
	}
	public void testplus(){
		Measurement n4 = new Measurement(1,3);
		Measurement n5 = new Measurement(0,7);
		n4.plus(n5);
		assertTrue (n4.getFeet() == 1);
	}
	public void testminus(){
		Measurement n6 = new Measurement(2,3);
		Measurement n7 = new Measurement(1,7);
		n6.minus(n7);
		assertTrue (n6.getInches() == 8);
	}
	public void testmultiple(){
		Measurement n8 = new Measurement(1,3);
		n8.multiple(2);
		assertTrue (n8.getFeet() == 2 && n8.getInches() == 6);
	}
	public void testtostring(){
		Measurement n9 = new Measurement(1,3);
		assertEquals ("1'3\"",n9.toString() ); 
	}
}

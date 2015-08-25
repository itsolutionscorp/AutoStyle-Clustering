import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testMeasurement()
	{
		Measurement one = new Measurement();
		Measurement two = new Measurement(5);
		Measurement three = new Measurement(5,6);
		assertTrue (one.getFeet() == 0);
		assertTrue (one.getInches()==0);
		assertTrue (two.getFeet() == 5);
		assertTrue (two.getInches()==0);
		assertTrue (three.getFeet()==5);
		assertTrue (three.getInches()==6);
	}
	
	public void testPlus()
	{
		Measurement one = new Measurement(8,6);
		Measurement two = new Measurement (0,36);
		one.plus(two);
		assertTrue(one.getFeet() == 11);
		assertTrue(one.getInches() == 6);
	}
	
	public void testMinus()
	{
		Measurement one = new Measurement(5,6);
		Measurement two = new Measurement (1,8);
		Measurement three = new Measurement (0, 35);
		one.minus(two);
		assertTrue(one.getFeet() == 3);
		assertTrue(one.getInches() == 10);
		one.minus(three);
		assertTrue(one.getFeet() == 0);
		assertTrue(one.getInches() == 11);
	}
	
	public void testMultiply()
	{
		Measurement one = new Measurement(8,6);
		Measurement two = new Measurement (0,37);
		one.multiple(3);
		assertTrue(one.getFeet() == 25);
		assertTrue(one.getInches() == 6);
		two.multiple(3);
		assertTrue(two.getFeet() == 9);
		assertTrue(two.getInches() == 3);
	}
	
	public void testToString()
	{
		Measurement one = new Measurement();
		Measurement two = new Measurement(5);
		Measurement three = new Measurement(5,6);
		assertTrue(one.toString().equals("0\'0\""));
		assertTrue(two.toString().equals("5\'0\""));
		assertTrue(three.toString().equals("5\'6\""));
	}
}

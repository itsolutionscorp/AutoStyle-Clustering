import junit.framework.TestCase;


public class MeasurementTest extends TestCase 
{
	public void testContructor1()
	{
		Measurement m = new Measurement();
		assertTrue(m.getFeet() == 0);
		assertTrue(m.getInches() == 0);
		assertTrue(m.toString().equals("0'0\""));
	}
	public void testConstructor2()
	{
		Measurement m = new Measurement(5);
		assertTrue(m.getFeet() == 5);
		assertTrue(m.getInches() == 0);
	}
	public void testConstructor3()
	{
		Measurement m = new Measurement(5, 5);
		assertTrue(m.getFeet() == 5);
		assertTrue(m.getInches() == 5);
		m = new Measurement(5, 13);
		assertTrue(m.getFeet() == 6);
		assertTrue(m.getInches() == 1);
		m = new Measurement(5, 27);
		assertTrue(m.getFeet() == 7);
		assertTrue(m.getInches() == 3);
	}
	public void testPlus()
	{
		Measurement m1 = new Measurement(1, 1);
		Measurement m2 = new Measurement(2, 2);
		Measurement m3 = m1.plus(m2);
		assertTrue(m1.getFeet() == 1);
		assertTrue(m1.getInches() == 1);
		assertTrue(m2.getFeet() == 2);
		assertTrue(m2.getInches() == 2);
		assertTrue(m3.getFeet() == 3);
		assertTrue(m3.getInches() == 3);
		m1 = new Measurement(1, 11);
		m2 = new Measurement(2, 10);
		m3 = m2.plus(m1);
		assertTrue(m3.getFeet() == 4);
		assertTrue(m3.getInches() == 9);
	}
	public void testMinus()
	{
		Measurement m1 = new Measurement(2, 2);
		Measurement m2 = new Measurement(1, 1);
		Measurement m3 = m1.minus(m2);
		assertTrue(m1.getFeet() == 2);
		assertTrue(m1.getInches() == 2);
		assertTrue(m2.getFeet() == 1);
		assertTrue(m2.getInches() == 1);
		assertTrue(m3.getFeet() == 1);
		assertTrue(m3.getInches() == 1);
		assertTrue(m2 != m3);
		m1 = new Measurement(2, 0);
		m2 = new Measurement(1, 9);
		m3 = m1.minus(m2);
		assertTrue(m3.getFeet() == 0);
		assertTrue(m3.getInches() == 3);
	}
	public void testMultiple()
	{
		Measurement m1 = new Measurement(5, 5);
		Measurement m2 = m1.multiple(2);
		Measurement m3 = m1.multiple(3);
		Measurement m4 = m1.multiple(5);
		assertTrue(m1.toString().equals("5'5\""));
		assertTrue(m2.toString().equals("10'10\""));
		assertTrue(m3.toString().equals("16'3\""));
		assertTrue(m4.toString().equals("27'1\""));
	}
}

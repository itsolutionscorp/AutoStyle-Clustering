import junit.framework.TestCase;


public class MeasurementXTest extends TestCase {

	
	
	public void testFeet()
	{
		MeasurementX check = new MeasurementX(5,0);
		assertTrue (check.myFeet == 5);
	}
	
	public void testInches()
	{
		MeasurementX check2 = new MeasurementX(5,0);
		assertTrue ("NOOOO", check2.myInches == 0);
		
	}
	
	public void testNull()
	{
		MeasurementX check3 = new MeasurementX(5,0);
		assertNotNull(check3);
	}
	
	public void testEquality()
	{
		MeasurementX check2 = new MeasurementX(5,0);
		MeasurementX check3 = new MeasurementX(5,0);
		assertEquals(check2.myFeet, check3.myFeet);
		
	}
	
	
	
}

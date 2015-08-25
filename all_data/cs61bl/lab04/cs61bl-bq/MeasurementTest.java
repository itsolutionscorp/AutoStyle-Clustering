import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testConstructor() {
		Measurement m1 = new Measurement();
		assertEquals(m1.myFeet, 0); //Error because the myFeet and myInches are private variables for the get functions
		assertEquals(m1.myInches, 0);
		
		Measurement m2 = new Measurement(2);
		assertEquals(m2.myFeet, 2);
		assertEquals(m2.myInches, 0);
		
		Measurement m3 = new Measurement(2, 3);
		assertEquals(m3.myFeet, 2);
		assertEquals(m3.myInches, 3);
	}
	
	public void testGetFeet() {
		Measurement m1 = new Measurement();
		assertEquals(m1.getFeet(), 0);
		
		Measurement m2 = new Measurement(2);
		assertEquals(m2.getFeet(), 2);
		
		Measurement m3 = new Measurement(2, 3);
		assertEquals(m3.getFeet(), 2);
	}
	
	public void testGetInches() {
		Measurement m1 = new Measurement();
		assertEquals(m1.getInches(), 0);
		
		Measurement m2 = new Measurement(2);
		assertEquals(m2.getInches(), 0);
		
		Measurement m3 = new Measurement(2, 3);
		assertEquals(m3.getInches(), 3);
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(2);
		Measurement m12 = m1.plus(m2);
		assertEquals(m12.getFeet(), 2);
		assertEquals(m12.getInches(), 0);
		assertEquals(m2.getFeet(), 2);
		
		Measurement m3 = new Measurement();
		Measurement m4 = new Measurement(2, 3);
		Measurement m34 = m3.plus(m4);
		assertEquals(m34.getFeet(), 2);
		assertEquals(m34.getInches(), 3);
		
		Measurement m24 = m2.plus(m4);
		assertEquals(m24.getFeet(), 4);
		assertEquals(m24.getInches(), 3);
		
		Measurement m5 = new Measurement(3, 9);
		Measurement m54 = m5.plus(m4);
		assertEquals(m54.getFeet(), 6);
		assertEquals(m54.getInches(), 0);
	}
	
	public void testMinus(){
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(2);
		Measurement m21 = m2.minus(m1);
		assertEquals(m21.getFeet(), 2);
		assertEquals(m21.getInches(), 0);
		assertEquals(m1.getFeet(), 0);
		
		Measurement m3 = new Measurement();
		Measurement m4 = new Measurement(2, 3);
		Measurement m43 = m4.minus(m3);
		assertEquals(m43.getFeet(), 2);
		assertEquals(m43.getInches(), 3);
		
		Measurement m42 = m4.minus(m2);
		assertEquals(m42.getFeet(), 0);
		assertEquals(m42.getInches(), 3);
		
		Measurement m5 = new Measurement(3, 9);
		Measurement m54 = m5.minus(m4);
		assertEquals(m54.getFeet(), 1);
		assertEquals(m54.getInches(), 6);
		
		Measurement m6 = new Measurement(2, 7);
		Measurement m56 = m5.minus(m6);
		assertEquals(m56.getFeet(), 1);
		assertEquals(m56.getInches(), 2);
		
		Measurement m55 = m5.minus(m5);
		assertEquals(m55.getFeet(), 0);
		assertEquals(m55.getInches(), 0);	
	}
	
	public void testMultiple(){
		Measurement m1 = new Measurement();
		Measurement m14 = m1.multiple(4);
		assertEquals(m14.getFeet(), 0);
		assertEquals(m14.getInches(), 0);
		
		Measurement m2 = new Measurement(2);
		Measurement m20 = m2.multiple(0);
		assertEquals(m20.getFeet(), 0);
		assertEquals(m20.getInches(), 0);
		
		Measurement m3 = new Measurement(0, 7);
		Measurement m32 = m3.multiple(2);
		assertEquals(m32.getFeet(), 1);
		assertEquals(m32.getInches(), 2);
		
		Measurement m4 = new Measurement(2, 6);
		Measurement m43 = m4.multiple(3);
		assertEquals(m43.getFeet(), 7);
		assertEquals(m43.getInches(), 6);
	}
	
	public void testToString(){
		Measurement m1 = new Measurement();
		assertEquals(m1.toString(), "0'" + "0" + '"');
		
		Measurement m2 = new Measurement(5);
		assertEquals(m2.toString(), "5'" + "0" + '"');
		
		Measurement m3 = new Measurement(6, 3);
		assertEquals(m3.toString(), "6'" + "3" + '"');
			
	}
}

import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testMeasurement(){
		Measurement m1 = new Measurement();
		assertTrue(m1.getFeet() == 0);
		assertTrue(m1.getInches() == 0);
	
		Measurement m2 = new Measurement(5);
		assertTrue(m2.getFeet() == 5);
		assertTrue(m2.getInches() == 0);
		
		
		Measurement m3 = new Measurement(5, 8);
		assertTrue(m3.getFeet() == 5);
		assertTrue(m3.getInches() == 8);
		
		Measurement m4 = new Measurement(0);
		assertTrue(m4.getFeet() == 0);
		assertTrue(m4.getInches() == 0);
		
		Measurement m5 = new Measurement(0, 0);
		assertTrue(m5.getFeet() == 0);
		assertTrue(m5.getInches() == 0);
		}
	
	public void testgetFeet(){
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(5);
		Measurement m3 = new Measurement(7, 10);
		assertTrue(m1.getFeet() == 0);
		assertTrue(m2.getFeet() == 5);
		assertTrue(m3.getFeet() == 7);
	}
	public void testgetInches(){
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(5);
		Measurement m3 = new Measurement(7, 10);
		assertTrue(m1.getInches() == 0);
		assertTrue(m2.getInches() == 0);
		assertTrue(m3.getInches() == 10);
	}
	
	public void testmultiple(){
		Measurement m1 = new Measurement();
		Measurement m2 = new Measurement(5);
		Measurement m3 = new Measurement(7, 10);
		Measurement n1 = m1.multiple(2);
		Measurement n2 = m2.multiple(2);
		Measurement n3 = m3.multiple(2);
		Measurement n4 = m3.multiple(0);
		assertTrue(m1.getInches() == 0);
		assertTrue(m1.getFeet() == 0);
		assertTrue(m2.getInches() == 0);
		assertTrue(m2.getFeet() == 5);
		assertTrue(m3.getInches() == 10);
		assertTrue(m3.getFeet() == 7);
		assertTrue(n1.getInches() == 0);
		assertTrue(n1.getFeet() == 0);
		assertTrue(n2.getInches() == 0);
		assertTrue(n2.getFeet() == 10);
		assertTrue(n3.getInches() == 8);
		assertTrue(n3.getFeet() == 15);
		assertTrue(n4.getInches()==0);
		assertTrue(n4.getFeet() == 0);
		
	}
	
	public void testtoString(){
		Measurement m = new Measurement(1,2);
		String s = new String("1'2''");
		String t = m.toString();
		assertEquals(t, s);
		
	}
	
	public void testplus(){
		Measurement n1 = new Measurement(1, 2);
		Measurement n2 = new Measurement(3, 4);
		Measurement n3 = n1.plus(n2);
		assertTrue(n3.getFeet() == 4);
		assertTrue(n2.getFeet() == 3);
		assertTrue(n1.getFeet() == 1);
		assertTrue(n1.getInches() == 2);
		assertTrue(n2.getInches() == 4);
		assertTrue(n3.getInches() == 6);		
		Measurement n4 = new Measurement(0, 8);
		Measurement n5 = n4.plus(n2);
		assertTrue(n5.getFeet() == 4);
		assertTrue(n5.getInches() == 0);
	}
	
	public void testminus(){
		Measurement n1 = new Measurement(5, 2);
		Measurement n2 = new Measurement(3, 4);
		Measurement n3 = n1.minus(n2);
		assertTrue(n3.getFeet() == 1);
		assertTrue(n2.getFeet() == 3);
		assertTrue(n1.getFeet() == 5);
		assertTrue(n1.getInches() == 2);
		assertTrue(n2.getInches() == 4);
		assertTrue(n3.getInches() == 10);
		Measurement n4 = new Measurement();
		assertTrue(n1.minus(n4).getFeet() == 5 );
		assertTrue(n1.minus(n4).getInches() == 2 );
		assertTrue(n1.minus(n1).getFeet() == 0);
		assertTrue(n1.minus(n1).getInches() == 0);
		
	}
}

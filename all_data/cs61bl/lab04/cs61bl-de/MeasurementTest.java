import junit.framework.TestCase;


public class MeasurementTest extends TestCase {

	public void testConstructor() {
		Measurement m1 = new Measurement();
		assertTrue(m1.getFeet()== 0);
		assertTrue(m1.getInches() == 0);
		Measurement m2 = new Measurement(5);
		assertTrue(m2.getFeet() == 5);
		assertTrue(m2.getInches() == 0);	
		Measurement m3 = new Measurement(5, 4);
		assertTrue(m3.getFeet() == 5);
		assertTrue(m3.getInches() ==4);
		
	}
	
	public void testGetFeet() {
		Measurement x = new Measurement (0,0); //should be 0 feet
		Measurement p = new Measurement (4, 13); // should be 5 feet, 1 inch
		Measurement b = new Measurement (0, 13); //should be 1 foot, 1 inch
		assertTrue(x.getFeet()==0);
		assertTrue(p.getFeet()==5);
		assertTrue(b.getFeet()==1);
		
	}
	
	public void testGetInches() {
		Measurement h = new Measurement(0, 100); // 8 feet, 6 inches
		Measurement t = new Measurement (1, 14); //should be 2 feet, 2 inches
		assertTrue(h.getInches() == 4);
		assertTrue(t.getInches()==2);	
		
	}
	
	public void testPlus() {
		Measurement m1 = new Measurement(5, 4);
		Measurement m2 = new Measurement(2, 3);
		Measurement m3 = new Measurement(4, 11);
		Measurement m4 = new Measurement(7, 7);
		m1.plus(m2);
		assertTrue(m1.getFeet() == 7);
		assertTrue(m1.getInches() == 7);
		assertTrue(m2.getFeet()==2); //m2 feet unchanged
		assertTrue(m2.getInches() == 3); //m2 inches unchanged
		m3.plus(m4);
		assertTrue(m3.getFeet() == 12); //tests overflow of inch to feet
		assertTrue(m3.getInches() == 6); //tests leftover inches
		assertTrue(m4.getFeet()==7); //m4 feet unchanged
		assertTrue(m4.getInches() == 7); //m4 inches unchanged
			
		
	}
	
	public void testMinus() {
		Measurement n1 = new Measurement(9, 5);
		Measurement n2 = new Measurement(2, 7);
		Measurement n3 = new Measurement(6, 11);
		Measurement n4 = new Measurement(3, 12);
		n1.minus(n2);
		assertTrue(n1.getFeet() == 6);
		assertTrue(n1.getInches() == 10);
		assertTrue(n2.getFeet()==2); //n2 feet unchanged
		assertTrue(n2.getInches() == 7); //n2 inches unchanged
		
		n3.minus(n4);
		assertTrue(n3.getFeet() == 2); //tests overflow of inch to feet
		assertTrue(n3.getInches() == 11); //tests leftover inches
		assertTrue(n4.getFeet()==4); //n4 feet unchanged
		assertTrue(n4.getInches() == 0); //n4 inches unchanged
		
		
	}
	
	public void testMultiple() {
		Measurement n1 = new Measurement(1, 1);
		Measurement n2 = new Measurement(0, 0);
		Measurement n3 = new Measurement(6, 11);
		Measurement n4 = new Measurement(3, 12);
		n1.multiple(4);
		n2.multiple(10);
		n3.multiple(0);
		n4.multiple(1);
		
		assertTrue(n1.getFeet() == 4);
		assertTrue(n1.getInches() == 4);
		assertTrue(n2.getFeet()==0); //n2 feet unchanged
		assertTrue(n2.getInches() == 0); //n2 inches unchanged
		assertTrue(n3.getFeet() == 0); //tests multiply by 0 == 0
		assertTrue(n3.getInches() == 0); //tests multiply by 0 == 0
		assertTrue(n4.getFeet()== 4); //tests n*1 = n
		assertTrue(n4.getInches() == 0); //tests n*1 = n
		
		
		
	}
	
	public void testString() {
		Measurement t1 = new Measurement(1, 100);
		Measurement t2 = new Measurement(0, 12);
		Measurement t3 = new Measurement(5, 3);
		Measurement t4 = new Measurement(0, 0);
		Measurement t5 = new Measurement(1);
		Measurement t6 = new Measurement();
		assertEquals(9 + "'" + 4 + "\"", t1.toString()); //tests spill over from inch to foot
		assertEquals(1 + "'" + 0 + "\"", t2.toString()); //tests spill over 
		assertEquals(5 + "'" + 3 + "\"", t3.toString()); //tests basic case
		assertEquals(0 + "'" + 0 + "\"", t4.toString());
		assertEquals(1 + "'" + 0 + "\"", t5.toString()); // tests single arg constructor
		assertEquals(0 + "'" + 0 + "\"",t6.toString()); // tests 0 arg constructor
		
		
	}
	
}

import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor1() {
        Measurement c = new Measurement();
        assertTrue (c.myFeet==0);
        assertTrue (c.myInches== 0);

    }
    public void testConstructor2() {
	    Measurement c = new Measurement(5);
	    assertTrue (c.getFeet() ==5);
	    assertTrue (c.getInches() == 0);
	}
	public void testConstructor3() {
	    Measurement c = new Measurement(5,6);
	    assertTrue (c.getFeet() ==5);
	    assertTrue (c.getInches() == 6);     
	}
	public void testGetter(){
		Measurement c = new Measurement(5,6);
		assertTrue (c.getFeet() == 5);
		assertTrue (c.getInches() == 6);
	}
	public void testPlus() {
		Measurement m1 = new Measurement(1, 2); 
		Measurement m2 = new Measurement(1, 5);
		assertTrue(m1.plus(m2).getFeet()== 2);
		assertTrue(m1.plus(m2).getInches()== 7);
		
		Measurement m3 = new Measurement(1, 10); 
		Measurement m4 = new Measurement(2, 5);
		assertTrue(m3.plus(m4).getFeet()== 4);
		assertTrue(m3.plus(m4).getInches()== 3);
		
		Measurement m5 = new Measurement(1, 7); 
		Measurement m6 = new Measurement(2, 5);
		assertTrue(m5.plus(m6).getFeet()== 4);
		assertTrue(m5.plus(m6).getInches()== 0);
		
	
	}
	
	public void testMinus() {
		Measurement m1 = new Measurement(2, 5); 
		Measurement m2 = new Measurement(1, 3);
		assertTrue(m1.minus(m2).getFeet()== 1);
		assertTrue(m1.minus(m2).getInches()== 2);
		
		Measurement m3 = new Measurement(2, 2); 
		Measurement m4 = new Measurement(1, 3);
		assertTrue(m3.minus(m4).getFeet()== 0);
		assertTrue(m3.minus(m4).getInches()== 11);
		
		Measurement m5 = new Measurement(7, 2); 
		Measurement m6 = new Measurement(1, 2);
		assertTrue(m5.minus(m6).getFeet()== 6);
		assertTrue(m5.minus(m6).getInches()== 0);
	
	}
	public void testMultiple() {
		Measurement m1 = new Measurement(0, 7);
		assertTrue(m1.multiple(2).getFeet()==1);
		assertTrue(m1.multiple(2).getInches()==2);
		
		Measurement m2 = new Measurement(1, 7);
		assertTrue(m2.multiple(3).getFeet()==4);
		assertTrue(m2.multiple(3).getInches()==9);
		
		Measurement m3 = new Measurement(1, 2);
		assertTrue(m3.multiple(4).getFeet()==4);
		assertTrue(m3.multiple(4).getInches()==8);

		
	}
	



}

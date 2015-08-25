import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testInit(){
		Measurement m1 = new Measurement();
		assertTrue (m1.getFeet()==0);
		assertTrue (m1.getInches()==0);
		Measurement m2 = new Measurement(2);
		assertTrue (m2.getFeet()==2);
		assertTrue (m2.getInches()==0);
		Measurement m3 = new Measurement(3,9);
		assertTrue (m3.getFeet()==3);
		assertTrue (m3.getInches()==9);
		Measurement m4 = new Measurement(3,13);
		assertTrue (m4.getFeet()==4);
		assertTrue (m4.getInches()==1);
	}
	
	public void testPlus(){
		Measurement m1 = new Measurement(2,2);
		Measurement m2 = new Measurement(1,2);
		Measurement m3 = m1.plus(m2);
		assertTrue (m3.getFeet()==3);
		assertTrue (m3.getInches()==4);
		Measurement m4 = new Measurement(0,9);
		Measurement m5 = new Measurement(1,10);
		Measurement m6 = m4.plus(m5);
		assertTrue (m6.getFeet()==2);
		assertTrue (m6.getInches()==7);	
	}
	
	public void testMinus(){
		Measurement m1 = new Measurement(2,9);
		Measurement m2 = new Measurement(1,1);
		Measurement m3 = m1.minus(m2);
		assertTrue (m3.getFeet()==1);
		assertTrue (m3.getInches()==8);
		Measurement m4 = new Measurement(2,9);
		Measurement m5 = new Measurement(1,7);
		Measurement m6 = m4.minus(m5);
		assertTrue (m6.getFeet()==1);
		assertTrue (m6.getInches()==2);	
	}
	
	public void testMultiple(){
		Measurement m1 = new Measurement(2,2);
		Measurement m2 = m1.multiple(5);
		assertTrue (m2.getFeet()==10);
		assertTrue (m2.getInches()==10);
		Measurement m3 = new Measurement(2,9);
		Measurement m4 = m3.multiple(2);
		assertTrue (m4.getFeet()==5);
		assertTrue (m4.getInches()==6);	
	}

}

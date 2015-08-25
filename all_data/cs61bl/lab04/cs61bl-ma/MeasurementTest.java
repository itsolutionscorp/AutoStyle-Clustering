import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testMeasurement(){
		Measurement m1 = new Measurement();
		assertTrue(m1.getFeet() ==0);
		assertTrue(m1.getInches()==0);
		
		Measurement m2 = new Measurement(2);
		assertTrue(m2.getFeet() ==2);
		assertTrue(m2.getInches()==0);
		
		Measurement m3 = new Measurement(3, 1);
		assertTrue(m3.getFeet() ==3);
		assertTrue(m3.getInches()==1);
		
		Measurement m4 = new Measurement(3, 13);
		assertTrue(m4.getFeet() ==4);
		assertTrue(m4.getInches()==1);
		
		Measurement m5 = new Measurement(5, 12);
		assertTrue(m5.getFeet() ==6);
		assertTrue(m5.getInches()==0);
	}
	public void testPlus(){
		Measurement m6 = new Measurement(5, 12);
		Measurement m7 = m6.plus(new Measurement(0, 12));
		assertTrue(m7.getFeet() == 7);
		assertTrue(m7.getInches()==0);
		
		Measurement m8 = m6.plus(new Measurement(5,15));
		assertTrue(m8.getFeet()==12);
		assertTrue(m8.getInches()==3);
		
	}
	public void testMinus(){
		Measurement m9 = new Measurement (7,6);
		Measurement m10 = m9.minus(new Measurement(3,17));
		assertTrue(m10.getFeet()==3);
		assertTrue(m10.getInches()==1);
		
		Measurement m11 = m9.minus(new Measurement(7,6));
		assertTrue(m11.getFeet()==0);
		assertTrue(m11.getInches()==0);
		
		Measurement m12 = m9.minus(new Measurement(0,12));
		assertTrue(m12.getFeet()==6);
		assertTrue(m12.getInches()==6);
	}
	
	public void testMultiple(){
		Measurement m13 = new Measurement(2,16);
		Measurement m14 = m13.multiple(1);
		assertTrue(m14.getFeet() == 3);
		assertTrue(m14.getInches()==4);
		
		
		Measurement m15 = m13.multiple(0);
		assertTrue(m15.getFeet() == 0);
		assertTrue(m15.getInches()==0);
		
		Measurement m16 = m13.multiple(3);
		assertTrue(m16.getFeet() == 10);
		assertTrue(m16.getInches()==0);
		
		Measurement m20 = new Measurement(5,12);
		Measurement m21 = m20.multiple(2);
		assertTrue(m21.getFeet() == 12);
		assertTrue(m21.getInches()==0);
		
		
		
		
		
	}
	public void testtoString(){
		Measurement m17 = new Measurement(3,15);
		assertTrue(m17.toString().equals("4'3\""));
		
		Measurement m18 = new Measurement(7,0);
		assertTrue(m18.toString().equals("7'0\""));
		
		Measurement m19 = new Measurement(89,12);
		assertTrue(m19.toString().equals("90'0\""));
	}


}

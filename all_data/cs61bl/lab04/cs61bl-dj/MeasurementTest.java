import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testInit(){
		Measurement m1= new Measurement();
		assertTrue (m1.f == 0);
		assertTrue (m1.i == 0);
		
		Measurement m2= new Measurement(10);
		assertTrue (m2.f == 10);
		assertTrue (m2.i == 0);
		
		Measurement m3= new Measurement(10,6);
		assertTrue (m3.f == 10);
		assertTrue (m3.i == 6);

		
	}
	
	public void testGetFeet(){
		Measurement m= new Measurement(10,7);
		assertTrue (m.getFeet() == 10);
		
		Measurement m1= new Measurement(10);
		assertTrue (m1.getFeet() == 10);
		Measurement m2= new Measurement();
		assertTrue (m2.getFeet() == 0);

	}
	
	public void testGetInches(){
		Measurement m= new Measurement(10,7);
		assertTrue (m.getInches() == 7);
		
		Measurement m1= new Measurement(10);
		assertTrue (m1.getInches() == 0);
		
		Measurement m2= new Measurement();
		assertTrue (m2.getInches() == 0);

	}
	
	public void testPlus(){
		Measurement m1= new Measurement(10,10); //normal add
		Measurement m2= new Measurement(2,1);
		Measurement r= new Measurement(12,11);
		assertTrue (m1.plus(m2).equals(r)); 
		
		Measurement m3= new Measurement(10,10); //conversion needed
		Measurement m4= new Measurement(2,3);
		Measurement s= new Measurement(13,1);
		assertTrue (m3.plus(m4).equals(s)); 

	}
	
	public void testMinus(){
		Measurement m1= new Measurement(10,10); // normal deduction
		Measurement m2= new Measurement(2,1);
		Measurement r= new Measurement(8,9);
		assertTrue (m1.minus(m2).equals(r));
		
		Measurement m3= new Measurement(10,5); //conversion needed
		Measurement m4= new Measurement(2,10);
		Measurement s= new Measurement(7,7);
		assertTrue (m3.minus(m4).equals(s)); 
	}
	
	public void testMultiple(){
		Measurement m1= new Measurement(0,7);
		Measurement r= new Measurement(1,9);
		assertTrue (m1.multiple(3).equals(r));
		
		Measurement m3= new Measurement(3);
		Measurement s= new Measurement(9,0);
		assertTrue (m3.multiple(3).equals(s)); 
		
		Measurement m4= new Measurement();
		Measurement t= new Measurement(0,0);
		assertTrue (m4.multiple(3).equals(t)); 	
	}
	
	public void testToString(){
		Measurement m = new Measurement(12,3);
		assertTrue (m.toString().equals("12'3\"")); 
	}
	

}

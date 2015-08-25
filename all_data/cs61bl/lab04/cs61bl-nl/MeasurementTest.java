import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor(){
		// To test the Constructor with no initial value
		Measurement m= new Measurement();
		assertTrue(m.getFeet()==0);
		assertTrue(m.getInches()==0);
		
		// To test the Constructor with one initial value feet
		Measurement m1=new Measurement(1);
		assertTrue(m1.getFeet()==1);
		assertTrue(m1.getInches()==0);
		
		//To test the Constructor with 2 initial value feet, inches
		Measurement m2= new Measurement(3,13);
		assertTrue(m2.getFeet()==4);
		assertTrue(m2.getInches()==1);
		
	}
	
	public void testGetFeet(){
		Measurement m= new Measurement(2,14);
		assertTrue(m.getFeet()==3);
	}
	
	public void testGetInches(){
		Measurement m= new Measurement(2,14);
		assertTrue(m.getInches()==2);
	}
	
	public void testPlus(){
		Measurement m1= new Measurement(3,4);
		Measurement m2 = new Measurement(4,10);
		m1.plus(m2);
		assertTrue(m1.getFeet()==8);
		assertTrue(m1.getInches()==2);
		
	}
	
	public void testMinus(){
		Measurement m1= new Measurement(4,4);
		Measurement m2 = new Measurement(3,10);
		m1.minus(m2);
		assertTrue(m1.getFeet()==0);
		assertTrue(m1.getInches()==6);
	}
	
	
	public void testMultiple(){
		Measurement m= new Measurement(4,4);
		m.multiple(3);
		assertTrue(m.getFeet()==13);
		assertTrue(m.getInches()==0);
		
	}
	

}

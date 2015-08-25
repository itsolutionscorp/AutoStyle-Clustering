import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testMeasurement() {
		
		Measurement a = new Measurement();
		assertTrue(a.getFeet()==0 && a.getInches()==0);
		
		Measurement b= new Measurement(10);
		assertTrue(b.getFeet()==10 && b.getInches()==0);
		
		Measurement c= new Measurement(10, 5);
		assertTrue(c.getFeet()==10 && c.getInches()==5);
	}
	
	public void testPlus(){
		
		Measurement a = new Measurement(10, 10);
		Measurement b = new Measurement(10, 10);
		Measurement answer= a.plus(b);
		
		assertTrue(answer.getFeet()==21 && answer.getInches()== 8);
	
		
		Measurement c = new Measurement(10, 5);
		Measurement d = new Measurement(10, 5);
		Measurement answer2 = c.plus(d);
		
		assertTrue(answer2.getFeet()==20 & answer2.getInches()==10 );
	
		//not sure why answer2.equals(c.plus(d)) does not work...
	}
	
	public void testMinus(){
		
		Measurement bigger = new Measurement(10, 10);
		Measurement smaller = new Measurement(0, 11);
		Measurement answer= bigger.minus(smaller);
		
		assertTrue(answer.getFeet()==9 && answer.getInches()== 11);
		
		Measurement c = new Measurement(10, 5);
		Measurement d = new Measurement(5, 5);
		Measurement answer2 = c.minus(d);
		
		assertTrue(answer2.getFeet()==5 & answer2.getInches()==0 );
		
	}
	
	public void testMultiple(){
		
		Measurement a = new Measurement(1, 2);
		Measurement answer= a.multiple(10);
		
		assertTrue(answer.getFeet()==11 && answer.getInches()== 8);
		
		
		Measurement b  = new Measurement(0, 2);
		Measurement answer2= b.multiple(2);
		
		assertTrue(answer2.getFeet()==0 & answer2.getInches()==4 );
		
		Measurement c  = new Measurement(0, 2);
		Measurement answer3= c.multiple(0);
		
		assertTrue(answer3.getFeet()==0 & answer3.getInches()==0 );
		
	}
	
}

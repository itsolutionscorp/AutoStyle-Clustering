import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testMeasurement(){
		Measurement lynda = new Measurement(0, 62);
		assertTrue(lynda.feet == 5);
		assertTrue(lynda.inches == 2);
		Measurement zero = new Measurement();
		assertTrue(zero.feet==0);
		assertTrue(zero.inches ==0);
		Measurement ladder = new Measurement(2);
		assertTrue(ladder.feet == 2);
}
	
	public void testGetInches(){
		Measurement lynda = new Measurement(0, 62);
		assertTrue(lynda.inches == 2);
		Measurement rabbit = new Measurement(0, 13);
		assertTrue(rabbit.inches == 1);
		Measurement zero = new Measurement();
		assertTrue(zero.inches ==0);
	}
	
	public void testGetFeet(){
		Measurement rabbit = new Measurement(0, 13);
		assertTrue(rabbit.feet == 1);
		Measurement dog= new Measurement(2,15);
		assertTrue(dog.feet == 3);
		Measurement zero = new Measurement();
		assertTrue(zero.feet==0);
	}
	
	public void testPlus(){
		Measurement rabbit = new Measurement(0, 13);
		Measurement dog= new Measurement(2,15);
		rabbit.plus(dog);
		assertTrue(rabbit.feet == 4);
		assertTrue(rabbit.inches == 4);
		
	}
	
	public void testMinus(){
		Measurement rabbit = new Measurement(0, 13);
		Measurement dog= new Measurement(2,15);
		dog.minus(rabbit);
		assertTrue(dog.feet == 2);
		assertTrue(dog.inches == 2);
		Measurement lynda = new Measurement(0, 62);
		lynda.minus(rabbit);
		assertTrue(lynda.feet == 4);
		assertTrue(lynda.inches == 1);	
	}
	
	public void testMultiple(){
		Measurement rabbit = new Measurement(0, 13);
		rabbit.multiple(2);
		assertTrue(rabbit.feet == 2);
		assertTrue(rabbit.inches == 2);
		Measurement dog= new Measurement(2,15);
		dog.multiple(2);
		assertTrue(dog.feet == 6);
		assertTrue(dog.inches == 6);
	}
}



import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testMeasurement(){
		Measurement t = new Measurement();
		assertTrue(t.getFeet() == 0);
		assertTrue(t.getInches() == 0);
		
		Measurement t2 = new Measurement(5);
		assertTrue(t2.getFeet() == 5);
		assertTrue(t2.getInches() == 0);

		Measurement t3 = new Measurement(5, 7);
		assertTrue(t3.getFeet() == 5);
		assertTrue(t3.getInches() == 7);
		
		Measurement t5 = new Measurement(5, 12);
		assertTrue(t5.getFeet() == 6);
		assertTrue(t5.getInches() == 0);
		
		Measurement t4 = new Measurement(5, 126);
		assertTrue(t4.getFeet() == 15);
		assertTrue(t4.getInches() == 6);
	}
	

	
	public void testGetFeet() {
		Measurement t = new Measurement(5,8);
		assertTrue(t.getFeet() == 5);
		
	}
	
	public void testGetInches() {
		Measurement t = new Measurement(5,8);
		assertTrue(t.getInches() == 8);
	}
	
	public void testPlus() {
		Measurement a1 = new Measurement(4,3);
		Measurement b1 = new Measurement(3,4);
		Measurement answer1 = a1.plus(b1);
		assertTrue(answer1.getFeet() == 7);
		assertTrue(answer1.getInches() == 7);
		
		Measurement a4 = new Measurement(4,3);
		Measurement b4 = new Measurement(0,0);
		Measurement answer4 = a4.plus(b4);
		assertTrue(answer4.getFeet() == 4);
		assertTrue(answer4.getInches() == 3);
		
		Measurement a2 = new Measurement(4,8);
		Measurement b2 = new Measurement(3,4);
		Measurement answer2 = a2.plus(b2);
		assertTrue(answer2.getFeet() == 8);
		assertTrue(answer2.getInches() == 0);
		
		Measurement a3 = new Measurement(4,8);
		Measurement b3 = new Measurement(2,11);
		Measurement answer3 = a3.plus(b3);
		assertTrue(answer3.getFeet() == 7);
		assertTrue(answer3.getInches() == 7);
	}
	
	public void testMinus(){
		Measurement a1 = new Measurement(4,7);
		Measurement b1 = new Measurement(3,4);
		Measurement answer1 = a1.minus(b1);
		assertTrue(answer1.getFeet() == 1);
		assertTrue(answer1.getInches() == 3);
		
		Measurement a4 = new Measurement(4,7);
		Measurement b4 = new Measurement(0,0);
		Measurement answer4 = a4.minus(b4);
		assertTrue(answer4.getFeet() == 4);
		assertTrue(answer4.getInches() == 7);
		
		Measurement a2 = new Measurement(4,8);
		Measurement b2 = new Measurement(3,8);
		Measurement answer2 = a2.minus(b2);
		assertTrue(answer2.getFeet() == 1);
		assertTrue(answer2.getInches() == 0);
		
		Measurement a3 = new Measurement(3,8);
		Measurement b3 = new Measurement(2,11);
		Measurement answer3 = a3.minus(b3);
		assertTrue(answer3.getFeet() == 0);
		assertTrue(answer3.getInches() == 9);
	}
	
	public void testMultiple(){
		Measurement a1 = new Measurement(4,3);
		Measurement answer1 = a1.multiple(2);
		assertTrue(answer1.getFeet() == 8);
		assertTrue(answer1.getInches() == 6);
		
		Measurement a4 = new Measurement(4,3);
		Measurement answer4 = a4.multiple(0);
		assertTrue(answer4.getFeet() == 0);
		assertTrue(answer4.getInches() == 0);
		
		Measurement a2 = new Measurement(5,8);
		Measurement answer2 = a2.multiple(3);
		assertTrue(answer2.getFeet() == 17);
		assertTrue(answer2.getInches() == 0);
		
		Measurement a3 = new Measurement(3,9);
		Measurement answer3 = a3.multiple(9);
		assertTrue(answer3.getFeet() == 33);
		assertTrue(answer3.getInches() == 9);
	}
	
	public void testToString(){
		Measurement a1 = new Measurement(4,3);
		String answer1 = a1.toString();
		assertEquals("4'3\"", answer1);
		
		Measurement a2 = new Measurement(0,0);
		String answer2 = a2.toString();
		assertEquals("0'0\"", answer2);
	}
}


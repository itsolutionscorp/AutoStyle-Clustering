import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor1(){
		Measurement Bill = new Measurement();
		assertTrue(Bill.getFeet() == 0);
		assertTrue(Bill.getInches() == 0);
	}
	
	public void testConstructor2(){
		Measurement Charles = new Measurement(100);
		assertTrue(Charles.getFeet() == 100);
		assertTrue(Charles.getInches() == 0);
	}
	
	public void testConstructor3(){
		Measurement Parth = new Measurement(1000000, 10);
		assertTrue(Parth.getFeet() == 1000000);
		assertTrue(Parth.getInches() == 10);
		Measurement inchTest = new Measurement(1000000, 13);
		assertTrue(inchTest.getFeet() == 1000001);
		assertTrue(inchTest.getInches() == 1);
		Measurement inchTest2 = new Measurement(1000000, 25);
		assertTrue(inchTest2.getFeet() == 1000002);
		assertTrue(inchTest2.getInches() == 1);
	}
	
	public void testPlus(){
		Measurement Billy = new Measurement(100, 10);
		Measurement Bobby = new Measurement(111, 1);
		Billy = Billy.plus(Bobby);
		assertTrue(Billy.getFeet() == 211);
		assertTrue(Billy.getInches() == 11);
		Measurement SUPERMAN = new Measurement(100, 11);
		Measurement BATMAN = new Measurement(111, 1);
		BATMAN = BATMAN.plus(SUPERMAN);
		assertTrue(BATMAN.getFeet() == 212);
		assertTrue(BATMAN.getInches() == 0);
	}
	
	public void testMinus(){
		Measurement hai = new Measurement(100, 1);
		Measurement bai = new Measurement(111, 10);
		bai = bai.minus(hai);
		assertTrue(bai.getFeet() == 11);
		assertTrue(bai.getInches() == 9);
		Measurement SUPERMAN = new Measurement(311, 13);
		Measurement BATMAN = new Measurement(100, 1);
		SUPERMAN = SUPERMAN.minus(BATMAN);
		assertTrue(SUPERMAN.getFeet() == 212);
		assertTrue(SUPERMAN.getInches() == 0);
	}
	
	public void testMultiple(){
		Measurement doggy = new Measurement(5,3);
		doggy = doggy.multiple(3);
		assertTrue(doggy.getFeet() == 15);
		assertTrue(doggy.getInches() == 9);
		Measurement kitty = new Measurement(5,4);
		kitty = kitty.multiple(3);
		assertTrue(kitty.getFeet() == 16);
		assertTrue(kitty.getInches() == 0);
	}
	
	public void testToString(){
		Measurement m = new Measurement(23,26);
		assertTrue(m.toString().equals("25'2\""));
	}
}

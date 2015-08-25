import static org.junit.Assert.*;

import org.junit.Test;


public class MeasurementTest {

	@Test
	public void test() {
	}
	
	@Test
	public void testConstructors() {
		Measurement a = new Measurement();
		assertTrue (a.getFeet() == 0 && a.getInches() == 0);
		
		Measurement b = new Measurement(5);
		assertTrue (b.getFeet() == 5 && b.getInches() == 0);
		
		Measurement c = new Measurement(5, 12);
		assertTrue (c.getFeet() == 6 && c.getInches() == 0);
		
		
		Measurement d = new Measurement(5, 24);
		assertTrue (d.getFeet() == 7 && d.getInches() == 0);
		

	}
	
	@Test
	public void testPlus() {
		Measurement a = new Measurement();		
		Measurement b = new Measurement(5);
		Measurement add1 = a.plus(b);
		assertTrue (add1.getFeet() == 5);
		assertTrue (add1.getInches() == 0);
		
		
		Measurement c = new Measurement(5, 16);
		Measurement add2 = c.plus(b);
		assertTrue (add2.getFeet() == 11 && add2.getInches() == 4);

		
		
		Measurement d = new Measurement(5, 24);
		Measurement add3 = d.plus(c);
		assertTrue (add3.getFeet() == 13 && add3.getInches() == 4);

	}
	
	
	@Test
	public void testMinus() {
		Measurement a = new Measurement(7);		
		Measurement b = new Measurement(5);
		Measurement min1 = a.minus(b);
		assertTrue (min1.getFeet() == 2);
		assertTrue (min1.getInches() == 0);
		
		
		Measurement c = new Measurement(5, 16);
		Measurement min2 = c.minus(b);
		assertTrue (min2.getFeet() == 1 && min2.getInches() == 4);

		
		
		Measurement d = new Measurement(7, 24);
		Measurement min3 = d.minus(c);
		assertTrue (min3.getFeet() == 2 && min3.getInches() == 8);


	}
	
	@Test
	public void testMultiple() {
		Measurement a = new Measurement(0, 7);		

		Measurement mul1 = a.multiple(3);
		assertTrue (mul1.getFeet() == 1);
		assertTrue (mul1.getInches() == 9);
		
		
		Measurement b = new Measurement();		

		Measurement mul2 = b.multiple(3);
		assertTrue (mul2.getFeet() == 0);
		assertTrue (mul2.getInches() == 0);
		
		Measurement c = new Measurement(3, 7);		

		Measurement mul3 = c.multiple(3);
		assertTrue (mul3.getFeet() == 10);
		assertTrue (mul3.getInches() == 9);
		
		Measurement d = new Measurement(3);		

		Measurement mul4 = d.multiple(3);
		assertTrue (mul4.getFeet() == 9);
		assertTrue (mul4.getInches() == 0);

	}
	
	@Test
	public void testString() {
		Measurement a = new Measurement();
		String s1 = a.toString();
		assertEquals ("0 ' 0 '' ", s1);
		
		Measurement b = new Measurement(5);
		String s2 = b.toString();
		assertEquals ("5 ' 0 '' ", s2);
		
		Measurement c = new Measurement(5, 12);
		String s3 = c.toString();
		assertEquals ("6 ' 0 '' ", s3);
		
		
		Measurement d = new Measurement(5, 24);
		String s4 = d.toString();
		assertEquals ("7 ' 0 '' ", s4);
	}
	

}

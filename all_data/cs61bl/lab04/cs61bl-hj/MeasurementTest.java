import static org.junit.Assert.*;

import org.junit.Test;


public class MeasurementTest {

	@Test
	public void testConstructor() {
		Measurement m0 = new Measurement();
		assertEquals(0, m0.getFeet());
		assertEquals(0, m0.getInches());
		
		Measurement m1 = new Measurement(5);
		assertEquals(5, m1.getFeet());
		assertEquals(0, m1.getInches());
		
		Measurement m2 = new Measurement(1,6);
		assertEquals(1, m2.getFeet());
		assertEquals(6, m2.getInches());
	}
	
	@Test
	public void testPlus(){
		Measurement m0 = new Measurement(1,5);
		Measurement m1 = new Measurement(1,5);
		Measurement m2 = m0.plus(m1);
		assertEquals(2, m2.getFeet());
		assertEquals(10, m2.getInches());
		
		Measurement m3 = new Measurement(1, 8);
		Measurement m4 = m0.plus(m3);
		assertEquals(3, m4.getFeet());
		assertEquals(1, m4.getInches());
	}
	
	@Test
	public void testMinus(){
		Measurement m0 = new Measurement(3,5);
		Measurement m1 = new Measurement(3,4);
		Measurement m2 = m0.minus(m1);
		assertEquals(0, m2.getFeet());
		assertEquals(1, m2.getInches());
		
		Measurement m3 = new Measurement(1,6);
		Measurement m4 = m0.minus(m3);
		System.out.println(m0.getFeet());
		System.out.println(m4.getFeet());
		assertEquals(1, m4.getFeet());
		assertEquals(11, m4.getInches());
	}
	
	@Test
	public void testMultiple(){
		Measurement m0 = new Measurement(0,7);
		Measurement m2 = m0.multiple(3);
		assertEquals(1, m2.getFeet());
		assertEquals(9, m2.getInches());
		
		Measurement m3 = new Measurement(2, 5);
		Measurement m4 = m3.multiple(5);
		assertEquals(12, m4.getFeet());
		assertEquals(1, m4.getInches());
	}
	
	@Test
	public void testToString(){
		Measurement m0 = new Measurement(1,5);
		String shouldBe = "1'5\"";
		System.out.println(m0.toString());
		assertEquals(shouldBe, m0.toString());
	}
}

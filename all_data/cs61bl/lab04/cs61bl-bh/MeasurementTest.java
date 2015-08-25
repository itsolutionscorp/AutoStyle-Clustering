import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;


public class MeasurementTest {

	@Test
    public void testgetFeet() {
		Measurement ex1 = new Measurement(10);
		assertTrue(ex1.getFeet() == 10);
		Measurement extra1 = new Measurement(9,12);
		assertTrue(extra1.getFeet() == 9);
    }
	@Test
    public void testgetInches() {
    	Measurement ex2 = new Measurement(0,1);
    	assertTrue(ex2.getInches() == 1);
    	Measurement extra2 = new Measurement(3,8);
    	assertTrue(extra2.getInches() == 8);
    }
	@Test
    public void testPlus() {
    	Measurement ex3 = new Measurement(1);
    	Measurement ex4 = new Measurement(1);
    	Measurement newex3 = ex3.plus(ex4);
    	assertTrue(newex3.getFeet() == 2);
    	Measurement extra3 = new Measurement(3,6);
    	Measurement extra4 = new Measurement(5,9);
    	Measurement newextra3 = extra3.plus(extra4);
    	assertTrue(newextra3.getFeet() == 9);
    	assertTrue(newextra3.getInches() == 3);

    }
    @Test
    public void testMinus(){
    	Measurement ex5 = new Measurement(2);
    	Measurement ex6 = new Measurement(1);
    	Measurement newex5 = ex5.minus(ex6);
    	assertTrue(newex5.getFeet() == 1);
    	Measurement extra3 = new Measurement(5,6);
    	Measurement extra4 = new Measurement(3,9);
    	Measurement newextra3 = extra3.minus(extra4);
    	assertTrue(newextra3.getFeet() == 1);
    	assertTrue(newextra3.getInches() == 9);
    }
    @Test
    public void testMultiple(){
    	Measurement ex7 = new Measurement(1);
    	Measurement extra7 = ex7.multiple(3);
    	assertTrue(extra7.getFeet() == 3);
    	Measurement extra3 = new Measurement(1,2);
    	Measurement newextra3 = extra3.multiple(3);
    	assertTrue(newextra3.getFeet() == 3);
    	assertTrue(newextra3.getInches() == 6);
    	
    }
    @Test
    public void testtoString(){
    	Measurement ex8 = new Measurement(2,6);
    	assertTrue(ex8.toString().equals("2'6"+'"')); 
    }
    
}

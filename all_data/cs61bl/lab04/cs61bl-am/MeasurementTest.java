import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;


public class MeasurementTest extends TestCase {

	@Test
	public void testConstructor() {
		Measurement c = new Measurement(0);
        assertTrue (c.getFeet() == 0); 
    
	}
	
	public void testGetFeet () {
		Measurement c = new Measurement(2);//checks the constructor with one parameter and the testGetFeet
        assertTrue (c.getFeet() == 2); 
        assertTrue (c.getInches() == 0);
        Measurement j = new Measurement();//checks the constructor with no parameters and the testGetFeet
        assertTrue (j.getFeet() == 0); 
        assertTrue (j.getInches() == 0);
       
		
	}
	public void testGetInches () {
	    Measurement k = new Measurement(5,3);//checks the constructor with two parameters and the testGetInches
		assertTrue (k.getFeet() == 5);
        assertTrue (k.getInches() == 3);
		
	}
	public void testMeasurementPlus () {
		Measurement k = new Measurement(5,3);
		Measurement j = new Measurement(15,18);
		k.plus(j);
		assertTrue (k.getFeet() == 20);
        assertTrue (k.getInches() == 21); 
		
	}
	public void testMeasurementMinus () {
		Measurement k = new Measurement(15,0);
		Measurement j = new Measurement(10,10);
		k.minus(j);
		assertTrue (k.getFeet() == 5);
        assertTrue (k.getInches() == -10 ); 
		
	}
	public void testMeasurmentMultiple () {
		Measurement k = new Measurement(2,3);
		assertTrue (k.multiple(2).getFeet() == 4);
		assertTrue (k.multiple(2).getInches() == 0);// since the instance variables stay what they are after changing  they
		//go double twice by line 53. Not sure if it is suppose to be designed that way or not What do you think Zhan? 
		
		
	}
	public void testToString () {
		Measurement k = new Measurement(2,3);
		assertTrue (k.toString().equals("2\'3\""));
	}
}

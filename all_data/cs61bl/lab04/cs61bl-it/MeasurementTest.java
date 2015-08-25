import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor1() {
		Measurement M = new Measurement();  
        assertTrue (M.getFeet() == 0);    // test first constructor, getFeet and getInches
        assertTrue (M.getInches() == 0);   // test first constructor, getFeet and getInches
	}
	public void testConstructor2() {
        Measurement M1 = new Measurement(12);  
        assertTrue (M1.getFeet() == 12);    // test second constructor, getFeet and getInches
        assertTrue (M1.getInches() == 0);   // test second constructor, getFeet and getInches
	}
	public void testConstructor3() {
        
        Measurement M2 = new Measurement(12, 10);  
        assertTrue (M2.getFeet() == 12);    // test third constructor, getFeet and getInches
        assertTrue (M2.getInches() == 10);   // test third constructor, getFeet and getInches
	}
public void testConstructor31() {
        
        Measurement M2 = new Measurement(1, 12);  
        assertTrue (M2.getFeet() == 2);    // test third constructor for conditional, getFeet and getInches
        assertTrue (M2.getInches() == 0);   // test third constructor f0r conditional, getFeet and getInches
	}
	
	public void testplus1() {
		Measurement M = new Measurement(); 
		Measurement m1 = new Measurement(12,10);
		Measurement M2= M.plus(m1);
		assertTrue (M2.getFeet() == 12);    // test plus method for basic functionality 
		assertTrue (M2.getInches() == 10);  
		
		// test other cases of input the would throw a bug 
	}
	public void testplus2() {
		Measurement M = new Measurement(); 
		Measurement m1 = new Measurement(0,24);
		Measurement M2= M.plus(m1);
		assertTrue (M2.getFeet() == 2);    // test plus method for conversion 
		assertTrue (M2.getInches() == 0);  
		
		// test other cases of input the would throw a bug 
	}
	public void testplus3() {
		Measurement M = new Measurement(); 
		Measurement m1 = new Measurement(0,24);
		Measurement M2 = M.plus(m1);
		assertTrue (M2 != M);    // test plus method for creation of a new object
		assertTrue (M2 != m1);    // test plus method for creation of a new object
		// test other cases of input the would throw a bug 
	}

	public void testminus1() {
		Measurement M = new Measurement(1,1); 
		Measurement m1 = new Measurement(1,1);
		Measurement M2= M.minus(m1);
		assertTrue (M2.getFeet() == 0);    // test minus method for basic functionality
		assertTrue (M2.getInches() == 0);   
		
	
		// test other cases of input the would throw a bug 
	}
	public void testminus2() {
		Measurement M = new Measurement(12,10); 
		Measurement m1 = new Measurement(0,13);
		Measurement M2= M.minus(m1);
		assertTrue (M2.getFeet() == 11);    // test minus method for conversion1
		assertTrue (M2.getInches() == 9);   
		// test other cases of input the would throw a bug 
	}
	public void testminut3() {
		Measurement M = new Measurement(12,0); 
		Measurement m1 = new Measurement(11,6);
		Measurement M2= M.minus(m1);
		assertTrue (M2.getFeet() == 0);    // test minus method for conversion2
		assertTrue (M2.getInches() ==6);   
		// test other cases of input the would throw a bug 
	}
	
	public void testminus4() {
		Measurement M = new Measurement(12,12); 
		Measurement m1 = new Measurement(0,12);
		Measurement M2= M.minus(m1);
		assertTrue (M2 != M);    // test minus method for return of new object
		assertTrue (M2 != m1);   
		
	
		// test other cases of input the would throw a bug 
	}
	public void testmultiple() {
		Measurement M = new Measurement(0,7); 
		Measurement M2= M.multiple(3);
		assertTrue (M2.getFeet() == 1);    // test multiple method for basic functionality and conversion
		assertTrue (M2.getInches() == 9);   
		// test other cases of input the would throw a bug 
	}
	public void testtoString() {
		Measurement M = new Measurement(); 
		String m= M.toString();
		String s= "0' 0\"";
		assertEquals (m , s);   // test toString method for basic functionality

		//(myFeet + "\' " + myInches + "\"");
		// test other cases of input the would throw a bug 
	}
	//toString
	
}

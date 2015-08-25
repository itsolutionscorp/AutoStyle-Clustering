import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor1() {
		Measurement a = new Measurement();
		assertTrue (a.getFeet()== 0);
		assertTrue (a.getInches() == 0);
		        
    }
	public void testConstructor2() {
		Measurement a = new Measurement(5);
		assertTrue (a.getFeet()== 5);
		assertTrue (a.getInches() == 0);
        
    }
	public void testConstructor3() {
		Measurement a = new Measurement(0, 400);
		assertTrue (a.getFeet()== 33);
		assertTrue (a.getInches() == 4);
        
    }
	public void testgetFeet() {
		Measurement a = new Measurement();
		assertTrue (a.getFeet()== 0);
		Measurement b = new Measurement(4);
		assertTrue (b.getFeet()== 4);
		Measurement c = new Measurement(4,6);
		assertTrue (c.getFeet()== 4);
        
    }
	public void testgetInches() {
		Measurement a = new Measurement();
		assertTrue (a.getInches()== 0);
		Measurement b = new Measurement(4);
		assertTrue (b.getInches()== 0);
		Measurement c = new Measurement(4,6);
		assertTrue (c.getInches()== 6);
        
    }
	public void testplus() {
		Measurement test = new Measurement(5, 100);
		Measurement a = new Measurement(5,6);		
		assertTrue (a.plus(test).getFeet()== 18);
		assertTrue (a.plus(test).getInches()== 10);
    }
	public void testminus() {
		Measurement test = new Measurement(5, 100);
		Measurement a = new Measurement(5, 500);		
		assertTrue (a.minus(test).getFeet()== 33);
		assertTrue (a.minus(test).getInches()== 4);
    }
	public void testmultiple() {
		Measurement a = new Measurement(5, 100);		
		assertTrue (a.multiple(3).getFeet()== 40);
		assertTrue (a.multiple(2).getInches()== 8);
    }
	public void testtoString() {
		Measurement a = new Measurement(5, 100);
		assertTrue (a.toString().equals("13\'4\""));
    }

}

import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testInit(){
		Measurement m = new Measurement();
		assertTrue(m.getFeet()==0);
		assertTrue(m.getInches()==0);
	}
	
	public void testOneArgConst(){
		Measurement m = new Measurement(1);
		assertTrue(m.getFeet()==1);
		assertTrue(m.getInches()==0);
	}
	
	public void testTwoArgConst(){
		Measurement m = new Measurement(1,2);
		assertTrue(m.getFeet()==1);
		assertTrue(m.getInches()==2);
	}
	
	public void testGetFeet(){
		Measurement m = new Measurement();
		assertTrue(m.getFeet()==0);
		Measurement n = new Measurement(0,14);
		assertTrue(n.getFeet()==1);
	}
	
	public void testGetInches(){
		Measurement m = new Measurement();
		assertTrue(m.getInches()==0);
		Measurement o = new Measurement(0,3);
		assertTrue(o.getInches()==3);
		Measurement n = new Measurement(0,20);
		assertTrue(n.getInches()==8);
	}
	
	public void testPlus(){
		Measurement m = new Measurement(3,5);
		Measurement n = new Measurement(5,7);
		Measurement o = m.plus(n);
		assertTrue(o.getFeet()==9);
		assertTrue(o.getInches()==0);
		Measurement a = new Measurement(2,5);
		Measurement b = new Measurement(5,3);
		Measurement c = a.plus(b);
		assertTrue(c.getFeet()==7);
		assertTrue(c.getInches()==8);
		Measurement x = new Measurement(0,4);
		Measurement y = new Measurement(0,24);
		Measurement z = x.plus(y);
		assertTrue(z.getFeet()==2);
		assertTrue(z.getInches()==4);
		
	}
	
	public void testMinus(){
		Measurement n = new Measurement(5,7);
		Measurement m = new Measurement(3,5);
		Measurement o = n.minus(m);
		assertTrue(o.getInches() == 2);
		assertTrue(o.getFeet()==2);
		Measurement b = new Measurement(5,8);
		Measurement a = new Measurement(3,9);
		Measurement c = b.minus(a);
		assertTrue(c.getFeet()==1);
		assertTrue(c.getInches()==11);
		Measurement x = new Measurement(3,4);
		Measurement y = new Measurement(0,26);
		Measurement z = x.minus(y);
		assertTrue(z.getFeet()==1);
		assertTrue(z.getInches()==2);
	}
	
	public void testMultiple(){
		Measurement n = new Measurement(5,7);
		Measurement o = n.multiple(3);
		assertTrue(o.getFeet()==16);
		assertTrue(o.getInches()==9);
		Measurement x = new Measurement(3,21);
		Measurement y = x.multiple(5);
		assertTrue(y.getFeet()==23);
		assertTrue(y.getInches()==9);
		Measurement a = new Measurement(5,0);
		Measurement b = a.multiple(2);
		assertTrue(b.getFeet()==10);
		assertTrue(b.getInches()==0);
		Measurement c = new Measurement(0,4);
		Measurement d = c.multiple(10);
		assertTrue(d.getFeet()==3);
		assertTrue(d.getInches()==4);
	}
	
	public void testToString(){
		Measurement m = new Measurement(5);
		assertEquals(m.toString(), "5\'0\"");
		Measurement n = new Measurement(3,4);
		assertEquals(n.toString(), "3\'4\"");
		Measurement o = new Measurement(0,3);
		assertEquals(o.toString(), "0\'3\"");
		Measurement a = new Measurement(0,14);
		assertEquals(a.toString(), "1\'2\"");
	}

}

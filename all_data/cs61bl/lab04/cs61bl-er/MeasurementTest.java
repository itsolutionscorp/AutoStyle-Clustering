import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor_1(){
		Measurement m = new Measurement();
		assertTrue(m.getFeet() == 0 );
		assertTrue(m.getInches() == 0);
	}
	public void testConstructor_2(){
		Measurement m = new Measurement(3);
		assertTrue(m.getFeet() == 3 );
		assertTrue(m.getInches() == 0);
		Measurement n = new Measurement(0);
		assertTrue(n.getFeet() == 0 );
		assertTrue(n.getInches() == 0);
	}

	public void testConstructor_3(){
		Measurement m = new Measurement(3,5);
		assertTrue(m.getFeet() == 3 );
		assertTrue(m.getInches() == 5);
		Measurement n = new Measurement(0,5);
		assertTrue(n.getFeet() == 0 );
		assertTrue(n.getInches() == 5);
		Measurement o = new Measurement(0,0);
		assertTrue(o.getFeet() == 0 );
		assertTrue(o.getInches() == 0);	
	}
    public void testPlus_1(){
    	Measurement m = new Measurement();
    	Measurement n = new Measurement(3,5);
    	Measurement p = n.plus(m);
        assertTrue(p.getFeet() == 3);
        assertTrue(p.getInches() == 5);	
    }
    public void testPlus_2(){
    	Measurement m = new Measurement(0,1);
    	Measurement n = new Measurement(3,5);
    	Measurement p = n.plus(m);
        assertTrue(p.getFeet() == 3);
        assertTrue(p.getInches() == 6);	
    }
    public void testPlus_3(){
    	Measurement m = new Measurement(1);
    	Measurement n = new Measurement(3,5);
    	Measurement p = n.plus(m);
        assertTrue(p.getFeet() == 4);
        assertTrue(p.getInches() == 5);	
    }
    public void testPlus_4(){
    	Measurement m = new Measurement(3);
    	Measurement n = new Measurement(0,5);
    	Measurement p = n.plus(m);
        assertTrue(p.getFeet() == 3);
        assertTrue(p.getInches() == 5);	
    }
    public void testMinus_1(){
    	Measurement m = new Measurement(3,5);
    	Measurement n = new Measurement();
    	Measurement p = m.minus(n);
        assertTrue(p.getFeet() == 3);
        assertTrue(p.getInches() == 5);	
    }
    public void testMinus_2(){
    	Measurement m = new Measurement(3,5);
    	Measurement n = new Measurement(1);
    	Measurement p = m.minus(n);
        assertTrue(p.getFeet() == 2);
        assertTrue(p.getInches() == 5);	
    }
    public void testMinus_3(){
    	Measurement m = new Measurement(3,5);
    	Measurement n = new Measurement(0,1);
    	Measurement p = m.minus(n);
        assertTrue(p.getFeet() == 3);
        assertTrue(p.getInches() == 4);	
    }
    public void testMinus_4(){
    	Measurement m = new Measurement(1);
    	Measurement n = new Measurement(0,5);
    	Measurement p = m.minus(n);
        assertTrue(p.getFeet() == 0);
        assertTrue(p.getInches() == 7);	
    }
    public void testMultiple_1(){
    	Measurement m = new Measurement();
    	Measurement p = m.multiple(0);
        assertTrue(p.getFeet() == 0);
        assertTrue(p.getInches() == 0);	
    }
    
    public void testMultiple_2(){
    	Measurement m = new Measurement(1,1);
    	Measurement p = m.multiple(0);
        assertTrue(p.getFeet() == 0);
        assertTrue(p.getInches() == 0);	
    }
    
    public void testMultiple_3(){
    	Measurement m = new Measurement(1,1);
    	Measurement p = m.multiple(1);
        assertTrue(p.getFeet() == 1);
        assertTrue(p.getInches() == 1);	
    }
    
    public void testMultiple_4(){
    	Measurement m = new Measurement(1,0);
    	Measurement p = m.multiple(5);
        assertTrue(p.getFeet() == 5);
        assertTrue(p.getInches() == 0);	
    }
    
    public void testString(){
    	Measurement m = new Measurement(5,10);
    	assertTrue(m.toString().equals(5 + "'" + 10 + "\""));
    }
}

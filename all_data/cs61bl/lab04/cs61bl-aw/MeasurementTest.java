import java.util.ArrayList;

import junit.framework.TestCase;

public class MeasurementTest extends TestCase {
    
	public void testConstructor()
    {
    	
          Measurement a=new Measurement();
          assertTrue (a.myfeet == 0);
          assertTrue (a.myinches == 0);
          Measurement b=new Measurement(3);
          assertTrue (b.myfeet ==3);
          assertTrue (b.myinches == 0);
          Measurement c=new Measurement(5,3);
          assertTrue (c.myfeet == 5);
          assertTrue (c.myinches ==3);
    
    }
    
	
    public void testDecrement()
    {
    	
    	Measurement m1=new Measurement(5,3);
    	Measurement m2=new Measurement(7,2);
    	m1.minus(m2);
    	assertTrue (m1.myfeet == 10);
    	assertTrue (m1.myinches == 0);
    	
    }
    
    
    public void testIncrement()
    {
    	Measurement m1=new Measurement(5,3);
    	Measurement m2=new Measurement(7,2);
    	m1.plus(m2);  
    	assertTrue (m1.myfeet == 0);
    	assertTrue (m1.myinches == 6);
    	
    }
    
    public void testMultiple()
    {
    	
    	Measurement m1=new Measurement(5,3);
    	m1.multiple(5);
    	assertTrue (m1.myfeet == 1);
    	assertTrue (m1.myinches == 17);
    	
    }
    public void testToString()
    {   ArrayList<Measurement> a = new ArrayList<Measurement>();
    	a.add(new Measurement(5,3));
    	a.add(new Measurement(7,2));	
    	Measurement m2=new Measurement(0,6);
    }
    }


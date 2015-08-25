import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testIsEmpty(){
		IntSequence test =new IntSequence(2);
		assertTrue(test.isEmpty() ==true);
		test.add(1);
		assertTrue(test.isEmpty() == false);
		
	}
	
	public void testSize()
	{IntSequence test =new IntSequence(2);
	assertTrue(test.size()==0);
	IntSequence test1 = new IntSequence(100);
	assertTrue(test1.size()==0);
	test1.add(1);
	assertTrue(test1.size()==1);
	}
	
	public void testElementAt()
	{IntSequence test = new IntSequence(5);
	test.add(5);
	assertTrue(test.elementAt(0)==5);
	test.add(4);
	assertTrue(test.elementAt(0)==4);
	assertTrue(test.elementAt(1)==5);
	test.add(2);
	test.add(1);
	assertTrue(test.elementAt(0)==1);
	assertTrue(test.elementAt(1)==2);
	assertTrue(test.elementAt(2) == 4);
	
	
	
	
		
	}
	public void testadd(){
		IntSequence test = new IntSequence(5);
		test.add(1);
		assertEquals(test.toString(),"1");
		test.add(2);
		assertTrue(test.myCount == 2);
		assertEquals(test.toString(),"2 1");
		test.add(3);
		assertEquals(test.toString(),"3 2 1");
		assertTrue(test.myCount == 3);
		
		}

    public void testInsert(){
    	IntSequence test = new IntSequence(100);
    	test.add(1);
    	assertEquals(test.toString(),"1");
    	test.add(2);
    	test.add(3);
    	test.add(4);
    	test.add(5);
    	
    	assertEquals(test.toString(),"5 4 3 2 1");
    	test.insert(99, 5);
    	assertEquals(test.toString(),"5 4 3 2 1 99");
    	test.add(100);
    	assertEquals(test.toString(),"100 5 4 3 2 1 99");
    	test.insert(250,3);
    	assertEquals(test.toString(),"100 5 4 250 3 2 1 99");
    	assertTrue(test.myCount == 8);
    	test.insert(222, 99);
    	assertEquals(test.toString(),"100 5 4 250 3 2 1 99");
    	assertTrue(test.myCount == 8);
    
    }
    public void testContains(){
    	IntSequence test = new IntSequence(50);
    	
    	assertTrue(test.contains(1) ==false);
    	assertTrue(test.contains(0)==false);
    	test.add(1);
    	assertTrue(test.contains(1)==true);
    	assertTrue(test.contains(100)==false);
    	test.add(3);
    	assertTrue(test.contains(3)==true);
    	test.remove(1);
    	assertTrue(test.contains(1)==false);
    	test.remove(0);
    	assertTrue(test.contains(3)==false);
    	
    }
    
}
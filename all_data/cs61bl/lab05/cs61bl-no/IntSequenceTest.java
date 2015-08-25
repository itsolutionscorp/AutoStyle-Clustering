import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
    public void testConstructor() {
		IntSequence test1 = new IntSequence(10);
		assertTrue(test1.myCount == 0);
		assertTrue(test1.myValues.length == 10);
    }
	
    public void testIsEmpty() {
    	IntSequence test1 = new IntSequence(10);
    	assertTrue(test1.isEmpty());
    	test1.add(5);
    	assertFalse(test1.isEmpty());
    }
    
    public void testSize() {
    	IntSequence test1 = new IntSequence(10);
    	assertTrue(test1.size()==0);
    	test1.add(5);
		test1.add(1);
		test1.add(3);
    	assertTrue(test1.size()==3);
    }
    
    public void testElementAt(){
    	IntSequence test1 = new IntSequence(10);
    	test1.add(5);
		test1.add(1);
		test1.add(3);
		assertTrue(test1.elementAt(0)==5);
		assertTrue(test1.elementAt(1)==1);
		assertTrue(test1.elementAt(2)==3);
    	
    }
    public void testRemove() {
    	IntSequence test1 = new IntSequence(10);
    	test1.add(5);
		test1.add(1);
		test1.add(3);
    	assertTrue(test1.remove(2) == 3);
    	assertTrue(test1.myCount == 2);
    	test1.add(7);
    	test1.add(9);
    	assertTrue(test1.remove(0) == 5);
    	assertTrue(test1.elementAt(0)==1);
    	assertTrue(test1.elementAt(1)==7);
    	assertTrue(test1.elementAt(2)==9);
    	assertTrue(test1.myCount == 3);
    	assertTrue(test1.remove(1)==7);
    	assertTrue(test1.elementAt(0)==1);
    	assertTrue(test1.elementAt(1)==9);
    	assertTrue(test1.myCount == 2);
    	
    }
    public void testInsert() {
    	IntSequence test1 = new IntSequence(10);
    	test1.add(5);
		test1.add(1);
		test1.add(3);
		test1.insert(2, 1);
    	assertTrue(test1.myCount == 4);
    	assertTrue(test1.elementAt(0)==5);
    	assertTrue(test1.elementAt(1)==2);
    	assertTrue(test1.elementAt(2)==1);
    	assertTrue(test1.elementAt(3)==3);
    	test1.insert(7,0);
    	assertTrue(test1.myCount == 5);
    	assertTrue(test1.elementAt(1)==5);
    	assertTrue(test1.elementAt(2)==2);
    	assertTrue(test1.elementAt(3)==1);
    	assertTrue(test1.elementAt(4)==3);
    	assertTrue(test1.elementAt(0)==7);
    	test1.insert(9,4);
    	assertTrue(test1.myCount == 6);
    	assertTrue(test1.elementAt(0)==7);
    	assertTrue(test1.elementAt(1)==5);
    	assertTrue(test1.elementAt(2)==2);
    	assertTrue(test1.elementAt(3)==1);
    	assertTrue(test1.elementAt(4)==9);
    	assertTrue(test1.elementAt(5)==3);
    	test1.insert(10,6);
    	assertTrue(test1.elementAt(0)==7);
    	assertTrue(test1.elementAt(1)==5);
    	assertTrue(test1.elementAt(2)==2);
    	assertTrue(test1.elementAt(3)==1);
    	assertTrue(test1.elementAt(4)==9);
    	assertTrue(test1.elementAt(5)==3);
    	assertTrue(test1.elementAt(6)==10);
    	assertTrue(test1.myCount == 7);
    }
    
    public void testContains() {
    	IntSequence test1 = new IntSequence(10);
    	test1.add(5);
		test1.add(1);
		test1.add(3);
		assertTrue(test1.contains(5));
		assertTrue(test1.contains(1));
		assertTrue(test1.contains(3));
		assertFalse(test1.contains(6));
    	
    }
	public void testToString() {
		IntSequence test1 = new IntSequence(10);
		
		test1.add(5);
		test1.add(1);
		test1.add(3);
		assertEquals(test1.toString(), "5 1 3");
		
		IntSequence test2 = new IntSequence(10);
		assertEquals(test2.toString(), "");
		
		
	}
}

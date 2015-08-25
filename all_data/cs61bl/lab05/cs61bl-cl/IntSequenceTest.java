import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
    public void testConstractor(){
	IntSequence S= new IntSequence(6);
	assertEquals (S.toString()," 0 0 0 0 0 0 ");
}
    public void testAdd() {
    	IntSequence S= new IntSequence(4);
    	IntSequence S1= new IntSequence(3);
    	
    	S.add(6);
    	S.add(5);
    	S.add(3);
    	S.add(-11);
    	S.add(9);
    	assertEquals (S.toString () , "2 6 5 3 -11 9 ");
    	S1.add(3);
    	S1.add(-7);
    	S1.add(42);
    	assertEquals(S.toString(),"3 -7 42");
    }
    	
    public void testInsert () {
    	IntSequence S= new IntSequence(6);
    	S.add(3);
    	S.add(-7);
    	S.add(42);
    	assertEquals(S.toString(),"3 -7 42 0 0 0 ");
    	S.insert(9, 3);
    	assertEquals(S.toString(),"3 -7 9 42 0 0 ");
    	S.insert(5,6);
    	assertEquals(S.toString(),"3 -7 9 42 0 5 ");
    	
    }
    public void testRemove () {
    	IntSequence S= new IntSequence(6);
    	S.add(3);
    	S.add(-7);
    	S.insert(9,3);
    	S.insert(42,4);
    	S.insert(5, 5);
    	assertEquals(S.toString(),"3 -7 9 42 5 0 ");
    	S.remove(0);
    	assertEquals(S.toString(),"-7 9 42 5 0 0");
    	
     }
    public void testContains() {
    	IntSequence S = new IntSequence(6);
    	S.add(3);
    	assertTrue(S.contains(0));
    	assertTrue(S.contains(3));
    	S.add(0);
    	assertEquals(S.toString(),"3 0 0 0 0 0");
    	assertTrue(S.contains(0));
    	S.remove(1);
    	assertTrue(S.contains(3));
    	S.insert(42, 4);
    	assertTrue(S.contains(42));
    	assertEquals(S.size(),1);
    	assertEquals(S.elementAt(0),3);
    	assertEquals(S.elementAt(4),42);
    	
    }
   
}

import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	public void testTwostring(){
		IntSequence i = new IntSequence(10);
//		 i.myValues ={3, -7, 42, -11, 0, 6, 9, 0, 0, 0};
    	i.add(3);
    	i.add(-7);
    	i.add(42);
    	i.add(-11);
    	i.add(0);
    	i.add(6);
    	i.add(9);

		assertEquals("3 -7 42 -11 0 6 9 0 0 0 ",i.toString(i.myValues));
	}
	
	public void testRemove(){
		IntSequence i = new IntSequence(10);
    	i.add(3);
    	i.add(-7);
    	i.add(42);
    	i.add(-11);
    	i.add(0);
    	i.add(6);
    	i.add(9);
    	i.remove(i.myValues,2);
		assertEquals("3 -7 -11 0 6 9 0 0 0 0 ",i.toString(i.myValues));
	}
	
	public void testInsert() {
		IntSequence i = new IntSequence(4);
		i.insert(i.myValues,1, 1);
		assertEquals("0 1 0 0 ", i.toString(i.myValues));
	}
	public void testContains(){
		IntSequence i = new IntSequence(4);
		i.add(9);
		assertTrue(i.contains(9));
		assertFalse(i.contains(5));
	}
}

import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	// @Test do i not need the test property?
	public void testRemove(){
		IntSequence a = new IntSequence(10);
		a.add(1);a.add(2);a.add(3);a.add(4);a.add(5);
		assertEquals(a.toString(), "1 2 3 4 5");
		a.remove(0);
		assertEquals(a.toString(), "2 3 4 5");
		a.remove(2);
		assertEquals(a.toString(), "2 3 5");
		a.remove(2);
		assertEquals(a.toString(), "2 3");
		a.remove(2); //check out of bounds
		assertEquals(a.toString(), "2 3");
	}
	
	public void testInsert(){
		IntSequence a = new IntSequence(10);
		a.add(1); a.add(2); a.add(3);
		//String x = a.toString();
		//System.out.println(x);
		a.insert(9, 0);//check beginning
		assertEquals(a.toString(), "9 1 2 3");
		a.insert(9, 2); //check middle
		assertEquals(a.toString(), "9 1 9 2 3");
		a.insert(9, 5);//check end
		assertEquals(a.toString(), "9 1 9 2 3 9");
		a.insert(9, 7);//check out of bounds
		assertEquals(a.toString(), "9 1 9 2 3 9");
	}
	
	public void testContains(){
		IntSequence a = new IntSequence(10);
		a.add(1); a.add(2); a.add(3);
		assertTrue(a.contains(1));
		assertFalse(a.contains(6));
	}

}

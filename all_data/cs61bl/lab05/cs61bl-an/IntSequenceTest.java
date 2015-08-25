import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence myS = new IntSequence(10);
		assertTrue(myS.isEmpty() == true);
		
	}
	
	public void testSize(){
		IntSequence myS = new IntSequence(10);
		assertTrue(myS.size() == 0);
		myS.add(0);
		assertTrue(myS.size() == 1);
		myS.add(1);
		myS.add(2);
		myS.add(3);
		myS.add(4);
		myS.add(5);
		myS.add(6);
		myS.add(7);
		myS.add(8);
		myS.add(9);
		assertTrue(myS.size() == 10);
	}
	
	public void testElementAt(){
		IntSequence myS = new IntSequence(10);
		assertTrue(myS.elementAt(0) == 0);
		myS.add(1);
		assertTrue(myS.elementAt(0) == 1);
	}
	
	public void testAdd(){
		IntSequence myS = new IntSequence(5);
		myS.add(1);
		assertEquals("1 0 0 0 0", myS.toString());
		
	}
	
	public void testRemove(){
		IntSequence myS = new IntSequence(5);
		myS.add(1);
		myS.add(2);
		myS.add(3);
		myS.add(4);
		myS.remove(0);
		assertEquals("2 3 4 0 0", myS.toString());
		myS.remove(2);
		assertEquals("2 3 0 0 0", myS.toString());
	}
	
	public void testInsert(){
		IntSequence myS = new IntSequence(7);
		myS.add(1);
		myS.add(2);
		myS.add(3);
		myS.add(4);
		myS.insert(100,2);
		assertEquals("1 2 100 3 4 0 0", myS.toString());
		myS.insert(99,4);
		assertEquals("1 2 100 3 99 4 0", myS.toString());
		myS.insert(0,0);
		assertEquals("0 1 2 100 3 99 4", myS.toString());
	}
	
	public void testContains(){
		IntSequence myS = new IntSequence(7);
		myS.add(1);
		myS.add(2);
		assertTrue(myS.contains(1) == true);
		assertTrue(myS.contains(2) == true);
		assertTrue(myS.contains(0) == false);
	}
}

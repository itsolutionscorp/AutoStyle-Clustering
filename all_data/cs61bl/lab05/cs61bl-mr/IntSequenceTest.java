import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence mySequence = new IntSequence(10);
		assertTrue(mySequence.size()==0);
		assertTrue(mySequence.isEmpty());
		for (int i = 0; i < 10; i++) {
			assertTrue(mySequence.myValues[i]==0);
		}
	}
	public void testAdd() {
		IntSequence mySequence = new IntSequence(10);
		mySequence.add(2);
		mySequence.add(1);
		assertTrue(mySequence.size() == 2);
		assertTrue(mySequence.myCount == 2);
		assertTrue(mySequence.myValues[0]==2);
		assertTrue(mySequence.myValues[1]==1);
	}
	public void testElementAt() {
		IntSequence mySequence = new IntSequence(10);
		mySequence.add(2);
		mySequence.add(1);
		assertTrue(mySequence.elementAt(0) == 2);
		assertTrue(mySequence.elementAt(1) == 1);
	}
	public void testToString() {
		IntSequence mySequence = new IntSequence(10);
		assertEquals(mySequence.toString(), "");		
		mySequence.add(2);
		mySequence.add(1);
		assertEquals(mySequence.toString(), "2 1");
	}
	public void testRemove() {
		IntSequence mySequence = new IntSequence(10);
		mySequence.add(2);	
		mySequence.remove(0);
		assertTrue(mySequence.size() == 0);
		mySequence.add(4);
		mySequence.add(6);
		mySequence.add(8);
		mySequence.remove(1);
		assertTrue(mySequence.size() == 2);
		assertTrue(mySequence.elementAt(1)==8);
	}
	
	public void testInsert() {
		IntSequence mySequence = new IntSequence(10);
		mySequence.add(2);	
		mySequence.add(4);
		mySequence.add(6);
		mySequence.add(8);
		mySequence.insert(5, 2);
		assertTrue(mySequence.size()==5);
		assertTrue(mySequence.elementAt(2)==5);
		assertTrue(mySequence.elementAt(3)==6);
		assertTrue(mySequence.elementAt(4)==8);
	}
	public void testContains() {
		IntSequence mySequence = new IntSequence(10);
		mySequence.add(2);	
		mySequence.add(4);
		mySequence.add(6);
		assertTrue(mySequence.contains(2));
		assertFalse(mySequence.contains(3));
		assertTrue(mySequence.contains(6));
	}
}

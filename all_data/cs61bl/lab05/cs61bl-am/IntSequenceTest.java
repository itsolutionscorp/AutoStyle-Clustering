import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testAdd() {
		IntSequence blah = new IntSequence(20);
		blah.add(3);
		blah.add(2);
		assertTrue(blah.myValues[0]==3);
		assertTrue(blah.myValues[1]==2);
		
		IntSequence lah = new IntSequence(1);
		lah.add(3);
		//(lah.add(2); will error
		
	}
	
	public void testIsEmpty() {
		IntSequence blah = new IntSequence(20);
		assertTrue(blah.isEmpty());
		
	}
	
	public void testSize() {
		IntSequence blah = new IntSequence(20);
		blah.add(3);
		blah.add(2);
		assertTrue(blah.myCount==2);
	}
	
	public void testInsert() {
		IntSequence blah = new IntSequence(20);
		blah.add(3);
		blah.add(2);
		blah.insert(1, 0);
		assertTrue(blah.myValues[0]==1);
		assertTrue(blah.myValues[1]==3);
		assertTrue(blah.myValues[2]==2);
		
	}

	public void testRemove() {
		IntSequence blah = new IntSequence(20);
		blah.add(3);
		blah.add(2);
		blah.add(1);
		blah.remove(1);
		assertTrue(blah.myValues[0]==3);
		assertTrue(blah.myValues[1]==1);
		assertTrue(blah.myValues[2]==0);
		assertTrue(blah.myCount==2);
	
		
}
	
	public void testContains() {
		IntSequence blah = new IntSequence(20);
		blah.add(3);
		blah.add(2);
		blah.add(1);
		assertTrue(blah.contains(1));
		assertTrue(blah.contains(2));
		assertTrue(blah.contains(3));
		assertFalse(blah.contains(4));
	}
	}

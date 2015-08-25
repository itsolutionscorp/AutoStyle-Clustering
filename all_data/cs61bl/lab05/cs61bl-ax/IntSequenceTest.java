import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	
	public void testIsEmpty() {
		IntSequence seq = new IntSequence(3);
		assertTrue( seq.isEmpty() );
		seq.add(1);
		assertTrue( !(seq.isEmpty()) );
	}
	
	public void testSize() {
		IntSequence seq = new IntSequence(3);
		assertTrue( seq.size() == 0 );
		seq.add(1);
		assertTrue( seq.size() == 1 );
		seq.add(2);
		seq.add(3);
		assertTrue( seq.size() == 3 );
	}
	
	public void testElementAt() {
		IntSequence seq = new IntSequence(3);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		assertTrue( seq.elementAt(0) == 1 );
		assertTrue( seq.elementAt(1) == 2 );
	}
	
	public void testToString() {
		IntSequence seq = new IntSequence(3);
		assertEquals( seq.toString(), "" );
		seq.add(1);
		seq.add(2);
		seq.add(3);
		assertEquals( seq.toString(), "1 2 3" );
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(3);
		seq.add(1);
		assertTrue( seq.elementAt(0) == 1 );
		assertTrue( seq.size() == 1 );
		seq.add(2);
		assertTrue( seq.elementAt(1) == 2 );
		assertTrue( seq.size() == 2 );
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(3);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		assertTrue( seq.remove() == 3);
		assertTrue( seq.size() == 2 );
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(10);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.insert(10, 0);
		assertTrue( seq.size() == 4 );
		assertEquals( seq.toString(), "10 1 2 3" );
		seq.add(4);
		seq.insert(20, 3);
		assertTrue( seq.size() == 6 );
		assertEquals( seq.toString(), "10 1 2 20 3 4");
	}
	
	public void testContains() {
		IntSequence seq = new IntSequence(10);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		assertTrue( !(seq.contains(5)) );
		assertTrue( seq.contains(1) );
	}
	
	
}

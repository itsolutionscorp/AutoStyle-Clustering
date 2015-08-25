
import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

	public void testConstructorandIsEmpty() {
		ResizableIntSequence seq = new ResizableIntSequence(20);
		assertTrue(seq.isEmpty());
	}
	
	public void testContains() {
		ResizableIntSequence seq = new ResizableIntSequence(20);
		seq.add(3);
		seq.add(5);
		seq.add(7);
		seq.add(9);
		seq.add(11);
		assertTrue(seq.toString().equals("3 5 7 9 11"));
		assertTrue(seq.contains(3));
		assertTrue(seq.contains(7));
		assertTrue(seq.contains(11));
	}
	
	public void testAdd() {	
		ResizableIntSequence seq = new ResizableIntSequence(4);
		seq.add(1);
		assertFalse(seq.isEmpty());
		assertTrue(seq.elementAt(0)==1);
		seq.add(2);
		seq.add(4);
		seq.add(5);
		assertTrue(seq.toString().equals("1 2 4 5"));
		assertTrue(seq.myValues.length == 4);
		seq.add(6);
		assertTrue(seq.toString().equals("1 2 4 5 6"));
		assertTrue(seq.myValues.length == 8);

	}
	
	public void testRemove() {
		ResizableIntSequence seq = new ResizableIntSequence(15);
		for (int i = 1; i <=21; i ++) {
			seq.add(i);
		}
		assertTrue(seq.toString().equals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21"));
		assertTrue(seq.myCount == 21);
		assertTrue(seq.myValues.length == 30);
		for (int i = 2; i < 21; i ++) {
			seq.remove(i);
		}
		assertTrue(seq.toString().equals("1 2"));
		assertTrue(seq.myCount == 2);

	}
	
	public void testRemoveZeroes() {
		ResizableIntSequence seq = new ResizableIntSequence(20);
		seq.add(0);
		seq.add(1);
		seq.add(0);
		seq.add(0);
		seq.add(0);
		assertTrue(seq.toString().equals("0 1 0 0 0"));
		seq.removezeroes();
		assertTrue(seq.toString().equals("1"));
	}
	
	public void testInsert() {
		ResizableIntSequence seq = new ResizableIntSequence(20);
		seq.add(3);
		seq.add(5);
		seq.add(7);
		seq.add(9);
		seq.add(11);
		assertTrue(seq.toString().equals("3 5 7 9 11"));
		seq.insert(2,  0);
		assertTrue(seq.toString().equals("2 3 5 7 9 11"));
		seq.insert(4,  3);
		assertTrue(seq.toString().equals("2 3 5 4 7 9 11"));
		seq.insert(10,  7);
		assertTrue(seq.toString().equals("2 3 5 4 7 9 11 10"));
	}

	public void testSize() {
		ResizableIntSequence seq = new ResizableIntSequence(20);
		seq.add(2);
		seq.add(4);
		seq.add(6);
		seq.add(8);
		assertTrue(seq.size()==4);
	}
	
	public void testElementAt() {
		ResizableIntSequence seq = new ResizableIntSequence(20);
		seq.add(2);
		seq.add(4);
		seq.add(6);
		seq.add(8);
		assertTrue(seq.elementAt(0)==2);
		assertTrue(seq.elementAt(1)==4);
		assertTrue(seq.elementAt(2)==6);
		assertTrue(seq.elementAt(3)==8);
	}
	
	public void testToString() {
		ResizableIntSequence seq = new ResizableIntSequence(20);
		seq.add(2);
		seq.add(4);
		seq.add(6);
		seq.add(8);
		//assertTrue(seq.toString().equals("2 4 6 8"));
		assertEquals(seq.toString(), "2 4 6 8");	
	}
	
}

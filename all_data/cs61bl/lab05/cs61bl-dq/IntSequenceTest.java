import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testConstructor() {
		IntSequence seq = new IntSequence(5);
		int numZeros = 0;
		for (int i = 0; i < seq.myValues.length; i++) {
			if (seq.myValues[i] == 0) {
				numZeros++;
			}
		}
		int expectedZeros = 5;
		assertEquals(expectedZeros, numZeros);
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(5);
		seq.add(1);
		seq.add(2);
		seq.insert(3, 1);
		int expected = 3;
		int actual = seq.myValues[1];
		assertEquals(expected, actual);
		assertEquals(3, seq.myCount);
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(5);
		seq.add(1);
		seq.add(3);
		seq.add(5);
		assertEquals(false, seq.isEmpty());
		
		seq.remove(0);
		seq.remove(1);
		seq.remove(2);
		assertEquals(true, seq.isEmpty());
		assertEquals(0, seq.myCount);
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(5);
		seq.add(4);
		seq.insert(6, 0);
		int expected1 = 6;
		int actual1 = seq.myValues[0];
		assertEquals(expected1, actual1);
		assertEquals(4, seq.myValues[1]);
		
		seq.insert(9, 0);
		int expected2 = 6;
		int actual2 = seq.myValues[1];
		assertEquals(expected2, actual2);
		assertEquals(9, seq.myValues[0]);
	}
	
	public void testIsEmpty() {
		IntSequence seq = new IntSequence(5);
		seq.add(2);
		assertEquals(false, seq.isEmpty());
		
		seq.remove(0);
		assertEquals(true, seq.isEmpty());
	}
	
	public void testSize() {
		IntSequence seq = new IntSequence(5);
		assertEquals(0, seq.size());
		seq.add(1);
		assertEquals(1, seq.size());
		seq.insert(2, 0);
		assertEquals(2, seq.size());
		seq.insert(7, 4);
		assertEquals(2, seq.size());
	}
	
	public void testElementAt() {
		IntSequence seq = new IntSequence(5);
		seq.add(3);
		assertEquals(3, seq.elementAt(0));
		
		seq.add(1);
		assertEquals(1, seq.elementAt(1));
	}
	
	public void testToString() {
		IntSequence seq = new IntSequence(5);
		seq.add(1);
		seq.add(3);
		seq.add(5);
		String expected = "1 3 5";
		String actual = seq.toString();
		assertEquals(expected, actual);
	}
	
	public void testContains() {
		IntSequence seq = new IntSequence(5);
		seq.add(8);
		assertEquals(true, seq.contains(8));
		assertEquals(false, seq.contains(0));
	}
	
}

import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void testConstructor() {
		IntSequence seq;
		seq = new IntSequence(50);
		assertTrue (seq.myCount == 0);
		assertTrue (seq.myValues != null);
	}
	
	@Test
	public void testAdd() {
		IntSequence seq;
		seq = new IntSequence(50);
		seq.add(2);
		assertTrue (seq.myCount == 1);
		assertTrue (seq.myValues[0] == 2);
		seq.add(3);
		assertTrue (seq.myCount == 2);
		assertTrue (seq.myValues[1] == 3);
		assertTrue (seq.myValues[0] == 2);
	}
	
	@Test
	public void testInsert() {
		IntSequence seq;
		seq = new IntSequence(50);
		seq.insert(1, 0);
		assertTrue (seq.myCount == 1);
		assertTrue (seq.myValues[0] == 1);
		seq.insert(4, 0);
		assertTrue (seq.myCount == 2);
		assertTrue (seq.myValues[0] == 4);
		seq.insert(5, 1);
		assertTrue (seq.myCount == 3);
		assertTrue (seq.myValues[1] == 5);
	}
	
	@Test
	public void testIsEmpty() {
		IntSequence seq;
		seq = new IntSequence(50);
		assertTrue (seq.isEmpty() == true);
		seq.add(3);
		assertTrue (seq.isEmpty() == false);
	}
	
	@Test
	public void testSize() {
		IntSequence seq;
		seq = new IntSequence(50);
		assertTrue (seq.size() == 0);
		seq.add(3);
		assertTrue (seq.size() == 1);
		seq.add(4);
		seq.add(4);
		assertTrue (seq.size() == 3);
	}
	
	@Test
	public void testElementAt() {
		IntSequence seq;
		seq = new IntSequence(50);
		seq.add(3);
		assertTrue (seq.elementAt(0) == 3);
		seq.insert(7, 1);
		assertTrue (seq.elementAt(1) == 7);
	}
	
	@Test
	public void testtoString() {
		IntSequence seq;
		seq = new IntSequence(50);
		assertTrue (seq.toString().equals(""));
		seq.add(3);
		assertTrue (seq.toString().equals("3"));
		seq.add(4);
		seq.add(5);
		String check;
		check = "3 4 5";
		assertTrue (seq.toString().equals(check));
	}

	@Test
	public void testRemove() {
		IntSequence seq;
		seq = new IntSequence(50);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.remove(0);
		assertTrue (seq.elementAt(0) == 2);
		assertTrue (seq.myCount == 3);
		assertTrue (seq.toString().equals("2 3 4"));
		seq.remove(4); //Nothing should have changed
		assertTrue (seq.elementAt(0) == 2);
		assertTrue (seq.myCount == 3);
		assertTrue (seq.toString().equals("2 3 4"));
	}
	
	@Test
	public void testContains() {
		IntSequence seq;
		seq = new IntSequence(50);
		seq.add(1);
		assertTrue (seq.contains(1));
		seq.insert(3, 0);
		assertTrue (seq.contains(3));
	}
}

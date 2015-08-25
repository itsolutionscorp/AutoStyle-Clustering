import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public static void testAdd() {
		ResizableIntSequence seq = new ResizableIntSequence(1);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		assertTrue(seq.toString().equals("1 2 3 4 5 6 7"));
	}
	public static void testRemove() {
		ResizableIntSequence seq = new ResizableIntSequence(2);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.remove(0);
		seq.remove(0);
		seq.remove(0);
		seq.remove(0);
		seq.remove(0);
		seq.remove(0);
		seq.remove(0);
		assertTrue(seq.toString().equals(""));
		assertTrue(seq.myValues.length == 1);
	}
	
	public static void testInsert() {
		ResizableIntSequence seq = new ResizableIntSequence(2);
		seq.add(1);
		seq.add(2);
		seq.insert(3, 1);
		seq.insert(4, 0);
		seq.insert(5, 2);
		seq.add(6);
		seq.add(7);
		assertTrue(seq.toString().equals("4 1 5 3 2 6 7"));
	}
}

import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

	public ResizableIntSequenceTest(String name) {
		super(name);
	}

	public void testConstructor(){
		ResizableIntSequence seq1 = new ResizableIntSequence(5);
		assertTrue(seq1.toString().equals(""));
		assertTrue(seq1.myCount == 0);
	}
	
	public void testAdd(){
		ResizableIntSequence seq1 = new ResizableIntSequence(2);
		seq1.add(2);
		seq1.add(4);
		seq1.add(6);
		assertTrue(seq1.toString().equals("2 4 6"));
		assertTrue(seq1.myCount == 3);
		assertTrue(seq1.myValues.length == 4);
	}
		
	public void testInsert(){
		ResizableIntSequence seq1 = new ResizableIntSequence(3);
		seq1.add(2);
		seq1.add(4);
		seq1.add(6);
		seq1.insert(3, 1);
		assertTrue(seq1.toString().equals("2 3 4 6"));
		assertTrue(seq1.myCount == 4);
		
	}
	
	public void testRemove(){
		ResizableIntSequence seq1 = new ResizableIntSequence(5);
		seq1.add(2);
		seq1.add(4);
		seq1.add(6);
		seq1.remove(2);
		assertTrue(seq1.myCount == 2);
		assertTrue(seq1.myValues.length == 2);
		assertTrue(seq1.toString().equals("2 4") );
		seq1.remove(0);
		assertTrue(seq1.toString().equals("4"));
		assertTrue(seq1.myCount == 1);
		assertTrue(seq1.myValues.length == 1);
		seq1.remove(0);
		assertTrue(seq1.toString().equals(""));
		assertTrue(seq1.myCount == 0);
		assertTrue(seq1.myValues.length == 0);
	}
}



import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testConstructor() {
		
		ResizableIntSequence seq = new ResizableIntSequence(2);
		assertEquals(seq.myValues.length, 2); 
		
	
	}
	public void testAdd() {
		ResizableIntSequence seq = new ResizableIntSequence(2);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);

		assertEquals (seq.toString(), "1 2 3 4");
		assertEquals(seq.myValues.length, 12); // increase capacity by 10 when full 2 + 10 = 12
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.add(8);
		seq.add(9);
		seq.add(10);
		seq.add(11);
		seq.add(12);
		seq.add(13);
		seq.add(14);
		assertEquals(seq.myValues.length, 22); //increase capacity by 10 second time
		
	}
	public void testInsert() {
		ResizableIntSequence seq = new ResizableIntSequence(3);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		assertEquals (seq.toString(), "1 2 3 4"); //correctly increase capacity once sequence is full
		seq.insert(8, 0);
		assertEquals (seq.toString(), "8 1 2 3 4"); //insert at beginning
		seq.insert(9, 2);
		assertEquals (seq.toString(), "8 1 9 2 3 4"); //insert at middle
		seq.insert(6, 5);
		assertEquals (seq.toString(), "8 1 9 2 3 6 4"); //insert at end
		seq.insert(2, 7);
		assertEquals (seq.toString(), "8 1 9 2 3 6 4 2"); //insert at end
		
		
	}
	public void testRemove() {
		ResizableIntSequence seq = new ResizableIntSequence(10);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.remove(1);
		
		assertEquals (seq.toString(), "1 3");
	
		assertEquals (seq.myValues.length, 9); // reduce capacity first round
		seq.remove(1);
		assertEquals (seq.toString(), "1");
		
		assertEquals (seq.myValues.length, 8); // reduce capacity second round
		seq.remove(0);
		assertEquals (seq.toString(), "");
		assertEquals (seq.myValues.length, 7);
		
		
		
		
	}
}

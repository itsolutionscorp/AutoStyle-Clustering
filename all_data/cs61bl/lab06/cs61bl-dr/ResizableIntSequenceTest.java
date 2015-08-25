import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	public void testAdd(){
		ResizableIntSequence r = new ResizableIntSequence(2);
		r.add(3);
		r.add(4);
		assertEquals(r.toString(), "3 4"); // original functionality
		r.add(2);
		r.add(2);
		assertEquals(r.toString(), "3 4 2 2"); // expands array
	}
	public void testInsert(){
		ResizableIntSequence r = new ResizableIntSequence(3);
		r.add(1);
		r.add(5);
		r.add(3);
		r.insert(10, 1);
		assertEquals(r.toString(), "1 10 5 3"); // inserts into full array
		r.insert(7,  0); 
		assertEquals(r.toString(), "7 1 10 5 3"); // inserts correctly in beginning
	}

}

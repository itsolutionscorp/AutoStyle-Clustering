import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd() {
		ResizableIntSequence r = new ResizableIntSequence(6);
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(4);
		assertEquals (r.toString(), "1 2 3 4");
	}
	
	public void testInsert () {
		ResizableIntSequence r = new ResizableIntSequence(3);
		r.insert(1, 0);
		r.insert(2, 1);
		r.insert(3, 2);
		r.insert(4, 3);
		assertEquals (r.toString(), "1 2 3 4");
	}
}

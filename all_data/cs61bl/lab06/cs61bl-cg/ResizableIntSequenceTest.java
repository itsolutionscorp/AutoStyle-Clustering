import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence r = new ResizableIntSequence(5);
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(4);
		r.add(5);
		assertTrue(r.myValues.length == 5);
		r.add(6);
		assertTrue(r.myValues[5] == 6);
		assertTrue(r.myValues.length == 10);
	}
	
	public void testInsert() {
		ResizableIntSequence r = new ResizableIntSequence(5);
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(4);
		r.add(5);
		assertTrue(r.myValues.length == 5);
		r.insert(6, 0);
		assertTrue(r.myValues[0] == 6);
		assertTrue(r.myValues.length == 10);
		r.myCount = 15;
	}
	
	public void testRemove() {
		ResizableIntSequence r = new ResizableIntSequence(20);
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(4);
		r.add(5);
		r.remove();
		assertTrue(r.myValues.length == 15);
	}
}

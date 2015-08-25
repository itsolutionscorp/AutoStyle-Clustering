import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence a = new ResizableIntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		assertEquals(a.toString(), "1 2 3 4 5 6 7");
		assertTrue(a.myValues.length == 10);
	}
	
	
	public void testInsert() {
		ResizableIntSequence a = new ResizableIntSequence(4);
		a.add(1);
		a.add(2);
		a.add(4);
		a.add(5);
		a.insert(3, 2);
		assertTrue(a.myValues[2] == 3 && a.myValues[3] == 4 && a.myValues.length == 8);
	}
	
	public void testRemove() {
		ResizableIntSequence a = new ResizableIntSequence(8);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.remove(0);
		assertTrue(a.myValues.length == 4);
		assertEquals(a.toString(), "2 3 4");
		
		ResizableIntSequence b = new ResizableIntSequence(10);
		b.add(1);
		b.add(2);
		b.add(3);
		b.add(4);
		b.add(5);
		b.add(6);
		b.remove(0);
		b.remove(1);
		assertTrue(b.myValues.length == 5);
		assertEquals(b.toString(), "2 4 5 6");
		
	}
}


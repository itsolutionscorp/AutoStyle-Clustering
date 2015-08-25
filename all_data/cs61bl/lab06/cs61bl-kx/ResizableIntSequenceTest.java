import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd(){
		ResizableIntSequence r = new ResizableIntSequence(2);
		r.add(9);
		r.add(7);
		r.add(10);
		assertEquals("9 7 10", r.toString());
		r.add(89);
		r.add(11);
		assertEquals("9 7 10 89 11", r.toString());
		assertTrue(r.myValues.length==12);
	}
	
	public void testInsert(){
		ResizableIntSequence r = new ResizableIntSequence(2);
		r.add(9);
		r.add(7);
		r.insert(1, 1);
		assertEquals("9 1 7", r.toString());
		
		r.insert(5, 3);
		assertEquals("9 1 7 5", r.toString());
		assertTrue(r.myValues.length==12);
		
		r.insert(8, 2);
		assertEquals("9 1 8 7 5", r.toString());
		
		
	}
	public void testRemove(){
		ResizableIntSequence r = new ResizableIntSequence(10);
		r.add(9);
		
		r.add(7);
		r.add(8);
		r.add(4);
		r.remove(0);
		
		assertTrue(r.myValues.length==4);
		assertEquals("7 8 4", r.toString());
		
		r.remove(2);
		assertTrue(r.myValues.length==3);
		assertEquals("7 8", r.toString());
		
		r.add(5);
		r.add(9);
		r.remove(1);
		assertTrue(r.myValues.length==4);
		assertEquals("7 5 9", r.toString());
		
	}

}

import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd() {
		IntSequence test1 = new ResizableIntSequence(3);
		test1.add(4);
		test1.add(1);
		test1.add(2);
		test1.add(3);
		assertEquals("4 1 2 3", test1.toString());
		assertEquals(4, test1.size());
		assertEquals(13, test1.myValues.length);
	}
	public void testInsert() {
		IntSequence test1 = new ResizableIntSequence(3);
		test1.add(4);
		test1.add(1);
		test1.add(2);
		test1.insert(10, 0);
		assertEquals("10 4 1 2", test1.toString());
		assertEquals(4, test1.size());
		assertEquals(13, test1.myValues.length);
		
		IntSequence test2 = new ResizableIntSequence(3);
		test2.add(4);
		test2.add(1);
		test2.add(2);
		test2.insert(10, 2);
		assertEquals("4 1 10 2", test2.toString());
		assertEquals(4, test2.size());
		assertEquals(13, test2.myValues.length);
		
		IntSequence test3 = new ResizableIntSequence(3);
		test3.add(4);
		test3.add(1);
		test3.add(2);
		test3.insert(10, 3);
		assertEquals("4 1 2 10", test3.toString());
		assertEquals(4, test3.size());
		assertEquals(13, test3.myValues.length);
	}
	
	public void testRemove() {
		IntSequence test1 = new ResizableIntSequence(11);
		test1.add(4);
		test1.add(1);
		test1.add(2);
		test1.remove(0);
		assertEquals("1 2", test1.toString());
		assertEquals(2, test1.size());
		assertEquals(8, test1.myValues.length);
		
		IntSequence test2 = new ResizableIntSequence(11);
		test2.add(4);
		test2.add(1);
		test2.add(2);
		test2.remove(2);
		assertEquals("4 1", test2.toString());
		assertEquals(2, test2.size());
		assertEquals(8, test2.myValues.length);
		
		IntSequence test3 = new ResizableIntSequence(11);
		test3.add(4);
		test3.add(1);
		test3.add(2);
		test3.remove(1);
		assertEquals("4 2", test3.toString());
		assertEquals(2, test3.size());
		assertEquals(8, test3.myValues.length);
	}
}

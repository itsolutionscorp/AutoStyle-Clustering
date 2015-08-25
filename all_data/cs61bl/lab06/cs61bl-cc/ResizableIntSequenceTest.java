import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence r = new ResizableIntSequence(3);
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(4);
		assertEquals(r.toString(), "1 2 3 4");
		assertEquals(r.myValues.length, 13);
		assertEquals(r.myCount, 4);

		ResizableIntSequence r1 = new ResizableIntSequence(3);
		r1.add(1);
		r1.add(2);
		assertEquals(r1.toString(), "1 2");
		assertEquals(r1.myValues.length, 3);
		assertEquals(r1.myCount, 2);

		ResizableIntSequence r2 = new ResizableIntSequence(3);
		r2.add(1);
		r2.add(2);
		r2.add(1);
		r2.add(2);
		r2.add(1);
		r2.add(2);
		r2.add(1);
		r2.add(2);
		r2.add(1);
		r2.add(2);
		r2.add(1);
		r2.add(2);
		r2.add(1);
		r2.add(2);
		assertEquals(r2.toString(), "1 2 1 2 1 2 1 2 1 2 1 2 1 2");
		assertEquals(r2.myValues.length, 23);
		assertEquals(r2.myCount, 14);

	}

	public void testInsert() {
		ResizableIntSequence r = new ResizableIntSequence(4);
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(4);
		r.insert(999, 2);
		assertEquals(r.toString(), "1 2 999 3 4");
		assertEquals(r.myValues.length, 14);
		assertEquals(r.myCount, 5);
		
		
		ResizableIntSequence r1 = new ResizableIntSequence(4);
		r1.add(1);
		r1.add(2);
		r1.add(3);
		r1.add(4);
		r1.insert(999, 0);
		assertEquals(r1.toString(), "999 1 2 3 4");
		assertEquals(r1.myValues.length, 14);
		assertEquals(r1.myCount, 5);
		
		
		
		ResizableIntSequence r2 = new ResizableIntSequence(4);
		r2.add(1);
		r2.add(2);
		r2.add(3);
		r2.add(4);
		r2.insert(999, 3);
		assertEquals(r2.toString(), "1 2 3 999 4");
		assertEquals(r2.myValues.length, 14);
		assertEquals(r2.myCount, 5);
	}
	
	
	public void testremove() {
		ResizableIntSequence r = new ResizableIntSequence(4);
		r.add(1);
		r.add(2);
		r.add(3);
		r.remove(0);
		assertEquals(r.toString(), "2 3");
		assertEquals(r.myValues.length, 4);
		assertEquals(r.myCount, 2);
		r.add(4);
		r.add(5);
		r.add(6);
		r.remove(1);
		assertEquals(r.toString(), "2 4 5 6");
		assertEquals(r.myValues.length, 5);
		assertEquals(r.myCount, 4);
		
	}

}

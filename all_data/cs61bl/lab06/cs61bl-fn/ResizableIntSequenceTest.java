import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testInitia() {
		ResizableIntSequence c= new ResizableIntSequence(5);
		assertTrue(5==c.myValues.length);
	}
	public void testisEmpty() {
		ResizableIntSequence c= new ResizableIntSequence(5);
		assertTrue(c.isEmpty());
	} //test after adding and deleting
	public void testsize() {
		ResizableIntSequence c= new ResizableIntSequence(5);
		assertTrue(c.size()==0);
		//insert >>size=1
	}
	public void testAt() {
		ResizableIntSequence c= new ResizableIntSequence(5);
		c.myCount=3;
		c.myValues[2]=5;
		assertTrue(c.elementAt(2)==5);
	}
	public void testAdd() {
		ResizableIntSequence c= new ResizableIntSequence(4);
		c.add(3);
		assertTrue(c.elementAt(0)==3);
		assertTrue(c.size()==1);
		//extra test for flexible size
		c.add(4);
		c.add(5);
		c.add(4);
		c.add(6);
		assertTrue(c.size()==5 && c.myValues.length==8);
		assertEquals(c.toString(),"3 4 5 4 6");
	}
	public void testtoString() {
		ResizableIntSequence c= new ResizableIntSequence(5);
		assertEquals("",c.toString());
		c.add(3);
		c.add(5);
		assertEquals("3 5",c.toString());
	}
	public void testRemove() {
		ResizableIntSequence c= new ResizableIntSequence(10);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		assertTrue(c.elementAt(1)==4);
		c.remove(1);
		assertTrue(c.elementAt(1)==5);
		assertTrue(c.size()==3);
		//extra test for flexible size
		c.remove(1);
		assertEquals(c.toString(),"3 6");
		c.remove(0);
		assertTrue(c.myValues.length==5);
	}
	public void testInsert() {
		ResizableIntSequence c= new ResizableIntSequence(10);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.insert(1,1);
		assertTrue(c.toString().equals("3 1 4 5 6"));
		c= new ResizableIntSequence(10);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.insert(1,0);
		assertTrue(c.toString().equals("1 3 4 5 6"));
		c= new ResizableIntSequence(5);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.insert(1,3);
		assertTrue(c.toString().equals("3 4 5 1 6"));
		//extra tests for flexible size
		c.insert(1,3);
		assertTrue(c.size()==6 && c.myValues.length==10);
		assertTrue(c.toString().equals("3 4 5 1 1 6"));
	}
	public void testContains() {
		ResizableIntSequence c= new ResizableIntSequence(10);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		assertTrue(c.contains(3));
		assertTrue(c.contains(6));
		assertFalse(c.contains(1));
	}
}

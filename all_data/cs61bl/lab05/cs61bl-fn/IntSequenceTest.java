import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	public void testInitia() {
		IntSequence c= new IntSequence(5);
		assertTrue(5==c.myValues.length);
	}
	public void testisEmpty() {
		IntSequence c= new IntSequence(5);
		assertTrue(c.isEmpty());
	} //test after adding and deleting
	public void testsize() {
		IntSequence c= new IntSequence(5);
		assertTrue(c.size()==0);
		//insert >>size=1
	}
	public void testAt() {
		IntSequence c= new IntSequence(5);
		c.myCount=3;
		c.myValues[2]=5;
		assertTrue(c.elementAt(2)==5);
	}
	public void testAdd() {
		IntSequence c= new IntSequence(5);
		c.add(3);
		assertTrue(c.elementAt(0)==3);
		assertTrue(c.size()==1);
	}
	public void testtoString() {
		IntSequence c= new IntSequence(5);
		assertEquals("",c.toString());
		c.add(3);
		c.add(5);
		assertEquals("3 5",c.toString());
	}
	public void testRemove() {
		IntSequence c= new IntSequence(5);
		c.add(3);
		c.add(4);
		c.add(5);
		assertTrue(c.elementAt(1)==4);
		c.remove(1);
		assertTrue(c.elementAt(1)==5);
		assertTrue(c.size()==2);
	}
	public void testInsert() {
		IntSequence c= new IntSequence(10);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.insert(1,1);
		assertTrue(c.toString().equals("3 1 4 5 6"));
		c= new IntSequence(10);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.insert(1,0);
		assertTrue(c.toString().equals("1 3 4 5 6"));
		c= new IntSequence(10);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.insert(1,3);
		assertTrue(c.toString().equals("3 4 5 1 6"));
	}
	public void testContains() {
		IntSequence c= new IntSequence(10);
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		assertTrue(c.contains(3));
		assertTrue(c.contains(6));
		assertFalse(c.contains(1));
	}
}
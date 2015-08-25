import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testAdd() {
		ResizableIntSequence b = new ResizableIntSequence(20);
		for (int q = 0; q < 20; q++){
			b.add(q);
		}
		assertTrue(b.myCount == 20);
		assertTrue(b.elementAt(3)==3);
		b.add(21);
		assertTrue(b.myCount == 21);
	}
	public void testInsert() {
		ResizableIntSequence c = new ResizableIntSequence(10);
		for (int q = 0; q < 10; q++){
			c.add(q);
		}
		c.insert(5, 1);
		assertTrue(c.elementAt(1) == 5);
		assertTrue(c.toString().equals("0 5 1 2 3 4 5 6 7 8 9"));
	}
	public void testRemove(){
		ResizableIntSequence c = new ResizableIntSequence(20);
		for (int q = 0; q < 10; q++){
			c.add(q);
		}
		assertTrue(c.remove(2)==2);
		assertTrue(c.toString().equals("0 1 3 4 5 6 7 8 9"));
		assertTrue(c.myValues.length == 10);
	}
}

import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testConstructor(){
		IntSequence a = new IntSequence(20);
		assertTrue(a.isEmpty()); 
	}
	
	public void testAdd() {
		IntSequence b = new IntSequence(20);
		for (int q = 0; q < 20; q++){
			b.add(q);
		}
		assertTrue(b.myCount == 20);
		assertTrue(b.elementAt(3)==3);
	}
	public void testInsert() {
		IntSequence c = new IntSequence(20);
		for (int q = 0; q < 10; q++){
			c.add(q);
		}
		c.insert(5, 1);
		assertTrue(c.elementAt(1) == 5);
		System.out.println(c.toString());
		assertTrue(c.toString().equals("0 5 1 2 3 4 5 6 7 8 9"));
	}
	public void testToString() {
		IntSequence c = new IntSequence(20);
		for (int q = 0; q < 10; q++){
			c.add(q);
		}
		assertTrue(c.toString().equals("0 1 2 3 4 5 6 7 8 9"));
	}
	
	public void testRemove(){
		IntSequence c = new IntSequence(20);
		for (int q = 0; q < 10; q++){
			c.add(q);
		}
		assertTrue(c.remove(2)==2);
		assertTrue(c.toString().equals("0 1 3 4 5 6 7 8 9"));
	}
	public void testContains() {
		IntSequence c = new IntSequence(20);
		for (int q = 0; q < 10; q++){
			c.add(q);
		}
		assertTrue(c.contains(5));
		assertFalse(c.contains(234));
	}
}

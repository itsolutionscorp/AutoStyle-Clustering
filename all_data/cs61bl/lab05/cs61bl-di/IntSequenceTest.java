import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testRemove(){
		IntSequence a = new IntSequence(5);
		a.add(2);
		a.add(4);
		a.add(6);
		a.add(8);
		assertTrue(a.myCount ==4);
		a.remove(2);
		assertEquals("2 4 8", a.toString());
		assertTrue(a.myValues[2] == 8);
		assertTrue(a.myCount == 3);
		a.remove(a.myCount-1);
		assertTrue(a.myValues[2] ==0);
		assertTrue(a.myValues[1] ==4);
		a.remove(0);
		a.remove(0);
		assertEquals("Position not in sequence", a.remove(0)); //Doesn't finish running since we programmed in a System.exit
			
	}
	
	
	public void testInsert(){	
		IntSequence b = new IntSequence(5);
		b.add(1);
		b.add(3);
		b.add(5);
		b.add(7);
		assertTrue(b.myCount == 4);
		b.insert(2, 3);
		assertTrue(b.myCount == 5);
		assertTrue(b.myValues[4] == 7);
		assertTrue(b.myValues[3] == 2);
		
	}

	
	public void testContains() {
		IntSequence c = new IntSequence(5);
		c.add(1);
		c.add(3);
		c.add(5);
		c.add(7);
		c.insert(2, 3);
		assertTrue(c.contains(1));
		assertFalse(c.contains(10));
		assertTrue(c.contains(2));
	}
}

import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence Values = new IntSequence(20);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		//System.out.println(Values.elementAt(0));
		//assertEquals (Values.elementAt(0), "Unable to access.");
				
	}
	public void testtoString() {
		IntSequence Values = new IntSequence(20);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add(3);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertTrue (Values.elementAt(0)== 3);
		
		Values.add(7);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertTrue (Values.elementAt(1)== 7);
		assertTrue (Values.contains(7) == true);
		assertTrue (Values.contains(3) == true);
		String s = "3 7";
		assertEquals(Values.toString(), s);
	}
	public void testInsert() {
		IntSequence Values = new IntSequence(20);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add(5);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertTrue (Values.elementAt(0)== 5);
		
		Values.add(10);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertTrue (Values.elementAt(1)== 10);
		
		Values.insert(8,0);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 3);
		//System.out.print(Values.elementAt(4));
		assertTrue (Values.elementAt(0)== 8);
		assertTrue (Values.elementAt(2) == 10);
		
	}
	public void testAdd() {
		IntSequence Values = new IntSequence(20);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add(4);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), 4);
		assertTrue (Values.contains(4) == true);
		
		
		Values.add(2);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertEquals (Values.elementAt(1), 2);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(4) == true);
		
		
	}
	public void testRemove() {
		IntSequence Values = new IntSequence(20);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add(5);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), 5);
		assertTrue (Values.contains(5) == true);
		
		Values.add(10);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertEquals (Values.elementAt(1), 10);
		assertTrue (Values.contains(5) == true);
		assertTrue (Values.contains(10) == true);
		
		Values.remove(0);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), 10);
		assertFalse (Values.contains(5) == true);
		
		Values.remove(0);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		assertFalse (Values.contains(5) == true);
		assertFalse (Values.contains(10) == true);
		
	}
}

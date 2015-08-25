import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testConstructor(){
		IntSequence i = new IntSequence(10);
		assertTrue(i.isEmpty());
		assertTrue(i.size() == 0);
	}
	
	public void testAdd(){
		IntSequence i = new IntSequence(10);
		assertTrue(i.isEmpty());
		assertTrue(i.size() == 0);
		
		i.add(5);
		assertFalse(i.isEmpty());
		assertTrue(i.size() == 1);
		assertTrue(i.elementAt(0) == 5);
		
		i.add(27);
		assertTrue(i.size() == 2);
		assertTrue(i.elementAt(1) == 27);
		
		i.add(3);
		i.add(1);
		i.add(0);
		i.add(90);
		i.add(40);
		i.add(4);
		i.add(900);
		i.add(57);
		
		assertTrue(i.toString().equals("5 27 3 1 0 90 40 4 900 57"));
	}
				
	public void testRemove(){
		IntSequence i = new IntSequence(5);
		i.add(5);
		i.add(1);
		i.add(39);
		i.add(10000);
		i.add(9);
		
		assertFalse(i.isEmpty());
		assertTrue(i.toString().equals("5 1 39 10000 9"));
		
		i.remove(3);
		assertTrue(i.toString().equals("5 1 39 9"));
		assertTrue(i.size() == 4);
		
		i.remove(3);
		assertTrue(i.toString().equals("5 1 39"));
		i.remove(2);
		assertTrue(i.toString().equals("5 1"));
		i.remove(1);
		assertTrue(i.toString().equals("5"));
		i.remove(0);
		assertTrue(i.toString().equals(""));
	}
	
	public void testInsert(){
		IntSequence i = new IntSequence(5);
		
		i.insert(5, 0);
		assertTrue(i.toString().equals("5"));
		
		i.add(5);
		i.add(10);
		i.add(15);
		assertTrue(i.toString().equals("5 5 10 15"));
		
		i.insert(25000, 0);
		assertTrue(i.toString().equals("25000 5 5 10 15"));
		
		i.remove(3);
		i.insert(15, 2);
		assertTrue(i.toString().equals("25000 5 15 5 15"));
		
		i.remove(4);
		i.insert(30, 4);
		assertTrue(i.toString().equals("25000 5 15 5 30"));
		
		
	}
	
	public void testContains(){
		IntSequence i = new IntSequence(5);
		i.add(1);
		i.add(2);
		i.add(3);
		i.add(4);
		
		assertTrue(i.contains(2));
		assertFalse(i.contains(5));
		assertFalse(i.contains(0));
	}
	
}

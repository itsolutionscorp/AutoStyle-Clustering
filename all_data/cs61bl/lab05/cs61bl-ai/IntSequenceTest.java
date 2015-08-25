import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testIsEmpty(){
		IntSequence sequence = new IntSequence(10);
		assertTrue(sequence.isEmpty());
		sequence.insert(4,0);
		assertFalse(sequence.isEmpty());
	}
	
	
	public void testSize(){
		IntSequence s = new IntSequence(3);
		assertTrue(s.size() == 0);
		s.insert(4,0);
		s.insert(4,1);
		assertTrue(s.size() == 2);
		s.insert(4,1);
		assertTrue(s.size() == 3);
	}
	
	public void testElementAt(){
		IntSequence s = new IntSequence(3);
		s.insert(3,0);
		s.insert(4,1);
		s.insert(7,1);
		assertTrue(s.elementAt(0)==3);
		assertTrue(s.elementAt(1)==7);
		assertTrue(s.elementAt(2)==4);
	}
	
	public void testAdd(){
		IntSequence s = new IntSequence(3);
		s.add(3);
		s.add(4);
		s.add(7);
		assertTrue(s.elementAt(0)==3);
		assertTrue(s.elementAt(1)==4);
		assertTrue(s.elementAt(2)==7);
	}
	
	public void testToString(){
		IntSequence s = new IntSequence(3);
		s.add(3);
		s.add(4);
		s.add(7);
		assertTrue(s.toString().equals("3 4 7") );
		
	}
	
	public void testRemove(){
		IntSequence s = new IntSequence(10);
		s.add(3);
		s.add(4);
		s.add(7);
		s.add(3);
		s.add(4);
		s.add(7);
		s.add(10);
		assertTrue(s.remove(2) == 7);
		assertTrue(s.toString().equals("3 4 3 4 7 10"));
	}
	
	public void testInsert(){
		IntSequence s = new IntSequence(10);
		s.add(3);
		s.add(4);
		s.add(7);
		s.add(3);
		s.add(4);
		s.add(7);
		s.add(10);
		s.insert(5, 5);
		assertTrue(s.toString().equals("3 4 7 3 4 5 7 10"));
	}
	
}

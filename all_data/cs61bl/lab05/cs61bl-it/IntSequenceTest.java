import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	public void testConstructor(){
		IntSequence s = new IntSequence(3); 
		assertTrue(s.isEmpty());
		
		s.add(3);
		assertFalse(s.isEmpty());
		
	}
	
	public void testToString (){
		IntSequence s = new IntSequence (5); 
		s.add(2);
		s.add(3);
		s.add(11);
		s.add(7);
		String exp = "2 3 11 7";
		assertEquals(s.toString(), exp);
	}
	
	
	public void testRemove(){
		IntSequence s = new IntSequence (5);
		s.add(3);
		s.add(1);
		s.add(2);
		s.add(5);
		s.add(7);
		
		s.remove(4);
		assertEquals(s.size(), 4);
		assertFalse(s.isEmpty());
		assertEquals(s.elementAt(3),5);
		s.remove(0); 
		 
		assertEquals(s.size(), 3); 
		assertEquals(s.elementAt(0),1);
		s.remove(2); 
		
		assertEquals(s.myCount, 2); 
		assertEquals(s.elementAt(1),2);
		
		
	}
	public void testInsert(){
		IntSequence s = new IntSequence (5);
		s.add(3);
		s.add(1);
		s.add(2);
		s.add(5);
		s.add(7);
		s.insert(9,4);
		assertEquals(s.myValues[0], 3);
		String exp = "3 1 2 5 9";
		System.out.println(s.toString());
		assertEquals(s.toString(), exp );
	}
}

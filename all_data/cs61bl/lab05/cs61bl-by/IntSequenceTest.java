import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testisEmpty(){
		IntSequence a = new IntSequence(0);
		assertTrue(a.isEmpty());
		
		a = new IntSequence(1);
		assertTrue(a.isEmpty());
		
		a.add(1);
		assertFalse(a.isEmpty());
	}
	
	public void testsize(){
		IntSequence a = new IntSequence(0);
		assertTrue(a.size()==0);
		a = new IntSequence(1);
		assertTrue(a.size()==0);
		a.add(1);
		assertTrue(a.size()==1);
	}
	
	public void testelementAt(){
		IntSequence a = new IntSequence(3);
		a.add(2);
		a.add(3);
		a.add(4);
		assertTrue(a.elementAt(0)==2);
		assertTrue(a.elementAt(1)==3);
		assertTrue(a.elementAt(2)==4);
	}
	
	public void testIntSequence(){
		IntSequence a = new IntSequence(0);
		assertTrue(a.myCount ==0);
		assertTrue(a.myValues.length == 0);
		
		a = new IntSequence(1);
		assertTrue(a.myCount == 0);
		assertTrue(a.myValues.length == 1);		
	}
	
	public void testtoString(){
		IntSequence a = new IntSequence(3);
		a.add(2);
		a.add(3);
		a.add(4);
		String s = a.toString();
		assertTrue(s.equals("2 3 4"));
		a = new IntSequence(0);
		s = a.toString();
		assertTrue(s.equals(""));
		a = new IntSequence(3);
		a.add(2);
		s = a.toString();
		assertTrue(s.equals("2"));
	}
	
	public void testadd(){
		IntSequence a = new IntSequence(3);
		a.add(2);
		a.add(3);
		a.add(4);
		assertTrue(a.myCount==3);
		assertTrue(a.elementAt(0)==2);
		assertTrue(a.elementAt(1)==3);
		assertTrue(a.elementAt(2)==4);
	}

	public void testremove(){
		IntSequence a = new IntSequence(3);
		a.add(2);
		a.add(3);
		a.add(4);

		assertTrue(a.remove(2) == 4);
		assertTrue(a.myCount == 2);
		assertTrue(a.elementAt(0) == 2);
		assertTrue(a.elementAt(1) == 3);

		a.add(4);
		assertTrue(a.remove(0)== 2);
		assertTrue(a.myCount == 2);
		assertTrue(a.elementAt(0) == 3);
		assertTrue(a.elementAt(1) == 4);
		
	}

	public void testinsert(){
		IntSequence a = new IntSequence(10);
		a.add(1);
		a.add(2);
		a.add(3);
		a.insert(4, 0);
		assertTrue(a.elementAt(0)==4);
		assertTrue(a.elementAt(1)==1);
		assertTrue(a.elementAt(2)==2);
		assertTrue(a.elementAt(3)==3);
		assertTrue(a.myCount == 4);
		
		a.insert(5, 4);
		assertTrue(a.elementAt(0)==4);
		assertTrue(a.elementAt(1)==1);
		assertTrue(a.elementAt(2)==2);
		assertTrue(a.elementAt(3)==3);
		assertTrue(a.elementAt(4)==5);
		assertTrue(a.myCount == 5);
	}

	public void testcontains(){
		IntSequence a = new IntSequence(10);
		a.add(1);
		a.add(2);
		a.add(3);
		assertTrue(a.contains(1));
		assertTrue(a.contains(2));
		assertTrue(a.contains(3));
		assertFalse(a.contains(7));
	}
}

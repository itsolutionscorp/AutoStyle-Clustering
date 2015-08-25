import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	//isEmpty()
	public void testisEmpty(){
		IntSequence s = new IntSequence(5);
		assertTrue (s.isEmpty());
		
		IntSequence w = new IntSequence(3);
		w.add(1);
		w.add(2);
		w.add(3);
		assertFalse(w.isEmpty());
	}
	
	//size
	public void testsize(){
		IntSequence t = new IntSequence(3);
		assertTrue(t.size() == 0);
	
		IntSequence x = new IntSequence(4);
		x.add(1);
		assertTrue(x.size() == 1);
	}
	
	//elementAt
	
	public void testelementAt(){
		IntSequence x = new IntSequence(4);
		x.add(1);
		assertTrue(x.elementAt(0) == 1);
	}
	
	//add
	public void testadd(){
		IntSequence x = new IntSequence(4);
		x.add(1);
		assertTrue(x.elementAt(0) == 1);
	}
	//toString
	public void testtoString(){
		
		IntSequence x = new IntSequence(4);
		x.add(1);
		x.add(2);
		x.add(3);
		assertEquals(x.toString(), "1 2 3 ");
		
	}
	
	//Remove
	
	public void testRemove(){
		IntSequence x = new IntSequence(5);
		x.add(1);
		x.add(2);
		x.add(3);
		x.add(4);
		x.remove(2);
		assertEquals(x.toString(), "1 2 4 ");
		assertTrue(x.size() == 3);
		
	}
	
	//Insert
	
	public void testInsert(){
		IntSequence y = new IntSequence(3);
		y.add(1);
		y.add(2);
		System.out.println(y.toString());
		y.insert(4, 1);
		System.out.println(y.toString());
		assertEquals(y.toString(), "1 4 2 ");
		
		IntSequence g = new IntSequence(5);
		g.add(1);
		g.add(2);
		g.add(3);
		g.add(4);
		g.insert(5,2);
		assertEquals(g.toString(), "1 2 5 3 4 ");
		
		IntSequence f = new IntSequence(3);
		f.add(1);
		f.add(2);
		f.insert(6, 0);
		assertEquals(f.toString(), "6 1 2 ");
		
	
	}
	
	public void testcontains(){
		IntSequence g = new IntSequence(5);
		g.add(1);
		g.add(2);
		g.add(3);
		g.add(4);
		assertTrue(g.contains(4));
		
		IntSequence f = new IntSequence(3);
		f.add(1);
		f.add(2);
		assertFalse(f.contains(0));
	}
}

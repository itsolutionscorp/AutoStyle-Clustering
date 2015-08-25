import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd(){
		ResizableIntSequence a = new ResizableIntSequence(10);
		a.add(1);
		assertTrue(a.elementAt(0) == 1);
		for (int i = 2; i <= 10; i++) {
			a.add(i);
		}
		assertTrue(a.elementAt(9) == 10);
		assertTrue(a.elementAt(4) == 5);
		
		a.add(11);
		assertTrue(a.elementAt(10) == 11);
		
		a.add(12);
		assertTrue(a.elementAt(11) == 12);
		assertTrue(a.size() == 12);

	}

	public void testInsert(){
		ResizableIntSequence b = new ResizableIntSequence(7);
		assertTrue(b.size() == 0);
		for (int i = 1; i < 8; i +=2){
			b.add(i);
		}
		assertTrue(b.size() == 4);
		
		b.insert(7, 0);
		assertTrue(b.size() == 5);
		assertTrue(b.elementAt(0) ==7);
		assertTrue(b.elementAt(3) == 5);
		
		b.insert(6, 3);
		assertTrue(b.size() == 6);
		assertTrue(b.elementAt(0) == 7);
		assertTrue(b.elementAt(3) == 6);
		assertTrue(b.elementAt(5) == 7);
		
		b.insert(10, 5);
		assertTrue(b.size() == 7);
		assertTrue(b.elementAt(0) == 7);
		assertTrue(b.elementAt(3) == 6);
		assertTrue(b.elementAt(5) == 10);
		assertTrue(b.elementAt(6) == 7);
		
		b.insert(11, 0);
		assertTrue(b.size() == 8);
		assertTrue(b.elementAt(0) == 11);
		assertTrue(b.elementAt(6) == 10);
		assertTrue(b.elementAt(7) == 7);
		
		b.insert(0, 7);
		assertTrue(b.size() == 9);
		assertTrue(b.elementAt(0) == 11);
		assertTrue(b.elementAt(6) == 10);
		assertTrue(b.elementAt(7) == 0);
		assertTrue(b.elementAt(8) == 7);
	}
	
	public void testRemove(){
			ResizableIntSequence a = new ResizableIntSequence(10);
			a.add(1);
			assertTrue(a.elementAt(0) == 1);
			for (int i = 2; i <= 10; i++) {
				a.add(i);
			}
			
			a.remove(0);
			a.remove(0);
			a.remove(0);
			a.remove(0);
			int removed = a.remove(0);
			assertTrue(a.elementAt(0) == 6);
			assertTrue(removed == 5);
			assertTrue(a.length() == 7);
			
			ResizableIntSequence b = new ResizableIntSequence(100);
			for (int i = 1; i <= 100; i++) {
				b.add(i);
			}

			for (int i = 1; i <= 50; i++) {
				b.remove(10);
			}
			
			assertTrue(b.elementAt(0) == 1);
			assertTrue(b.length() == 75);
			assertTrue(b.size() == 50);
			
			for (int i = 1; i <= 13; i++) {
				b.remove(0);
			}
			assertTrue(b.length() == 56);
			
			ResizableIntSequence c = new ResizableIntSequence(2);
			c.add(1);
			c.add(100);
			c.remove(1);
			assertTrue(c.elementAt(0) == 1);
			assertTrue(c.length() == 1);
			assertTrue(c.size() == 1);

	}
}

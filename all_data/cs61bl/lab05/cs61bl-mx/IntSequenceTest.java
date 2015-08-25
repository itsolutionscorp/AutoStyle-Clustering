import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testAdd(){
		IntSequence a = new IntSequence(10);
		a.add(1);
		assertTrue(a.elementAt(0) == 1);
		for (int i = 2; i <= 10; i++) {
			a.add(i);
		}
		assertTrue(a.elementAt(9) == 10);
		assertTrue(a.elementAt(4) == 5);
	}
	public void testInsert(){
		IntSequence b = new IntSequence(10);
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
		
	}
	
	public void testIsEmpty() {
		IntSequence c = new IntSequence(10);
		assertTrue(c.isEmpty() == true);
		for (int i = 1; i < 8; i +=2){
			c.add(i);
		}
		assertTrue(c.isEmpty() == false);
	}
	
	public void testtoString() {
		IntSequence d = new IntSequence(10);
		for (int i = 1; i < 8; i++){
			d.add(i);
		}
		String dString = d.toString();
		assertEquals(dString, "1 2 3 4 5 6 7");
	}
	
	public void testRemove() {
		IntSequence e = new IntSequence(10);
		for (int i = 1; i < 8; i++){
			e.add(i);
		}
		int num1 = e.remove(0);
		assertTrue(e.elementAt(0) == 2);
		assertTrue(e.size() == 6);
		assertTrue(num1 == 1);
		
		int num2 = e.remove(4);
		assertTrue(e.elementAt(4) == 7);
		assertTrue(e.size() == 5);
		assertTrue(num2 == 6);
		
		int num3 = e.remove(4);
		assertTrue(e.elementAt(3) == 5);
		assertTrue(e.size() == 4);
		assertTrue(num3 == 7);
	}
	
	public void testContains() {
		IntSequence f = new IntSequence(10);
		for (int i = 1; i < 8; i++){
			f.add(i);
		}
		boolean result1 = f.contains(5);
		assertTrue(result1 == true);
		
		boolean result2 = f.contains(10);
		assertTrue(result2 == false);
	}
	
}

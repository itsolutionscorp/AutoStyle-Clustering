import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence test = new IntSequence(1);
		assertTrue(test.isEmpty());
		
	}
	
	public void testtoString(){
		IntSequence test = new IntSequence(10);
		test.add(1);
		test.add(2);
		test.add(0);
		assertTrue(test.toString().equals("1 2 0"));
		
		IntSequence test1 = new IntSequence(1);
		assertTrue(test1.toString().equals(""));
		
		IntSequence test3 = new IntSequence(1);
		test3.add(10);
		assertTrue(test3.toString().equals("10"));
	}

	public void testelementAt(){
		IntSequence test = new IntSequence(10);
		test.add(1);
		test.add(2);
		test.add(0);
		
		assertEquals(test.elementAt(0),1);
		assertEquals(test.elementAt(2),0);
		assertTrue(test.toString().equals("1 2 0"));
		
		
		test.insert(5, 0);
		assertTrue(test.toString().equals("5 1 2 0"));
		
		IntSequence test1 = new IntSequence(3);
		test1.add(10);
		test1.add(20);
		test1.add(5);
		assertEquals(test1.elementAt(2),5);
		
	}
	
	public void testRemove(){
		IntSequence test = new IntSequence(3);
		test.add(7);
		test.add(8);
		test.add(9);
		test.remove(1);
		assertTrue(test.toString().equals("7 9"));
		assertEquals(test.size(),2);
	}
	
	public void testSize(){
		IntSequence test = new IntSequence(3);
		test.add(7);
		test.add(8);
		test.add(9);
		assertEquals(test.size(), 3);
		
		IntSequence test1 = new IntSequence(3);
		test1.add(7);
		test1.add(8);
		assertEquals(test1.size(), 2);
	}
	
	public void testcontain(){
		IntSequence test = new IntSequence(10);
		test.add(0);
		test.add(8);
		test.add(1);
		
		assertTrue(test.contains(8));
		assertTrue(test.contains(10)==false);
	}
	
}

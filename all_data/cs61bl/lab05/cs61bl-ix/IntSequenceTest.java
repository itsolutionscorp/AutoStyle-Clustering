import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	
	public void testRemove() {
	IntSequence testsequence = new IntSequence(10);
	

	testsequence.add(1);
	testsequence.add(2);
	testsequence.add(3);
	testsequence.add(4);
	testsequence.add(5);
	testsequence.remove(2);
	String values = testsequence.toString();
	String afterDelete1 = "1 2 4 5";
	assertEquals(afterDelete1,values);
	
	
	}
	public void testRemoveFirst() {
		IntSequence testsequence = new IntSequence(10);
		testsequence.add(1);
		testsequence.add(2);
		testsequence.add(3);
		testsequence.add(4);
		testsequence.add(5);
		testsequence.remove(0);
		String values2 = testsequence.toString();
		String afterDelete2 = "2 3 4 5";
		assertEquals(afterDelete2,values2);


	}
	
	public void testInsert1() {
		IntSequence testsequence = new IntSequence(10);
		testsequence.add(1);
		testsequence.add(2);
		testsequence.add(3);
		testsequence.add(4);
		testsequence.add(5);
		testsequence.insert(10000,0);
		String values2 = testsequence.toString();
		String afterDelete2 = "10000 1 2 3 4 5";
		assertEquals(afterDelete2,values2);


	}
	
	
	public void testInsert2() {
		IntSequence testsequence = new IntSequence(10);
		testsequence.add(1);
		testsequence.add(2);
		testsequence.add(3);
		testsequence.add(4);
		testsequence.add(5);
		testsequence.insert(9994,3);
		String values2 = testsequence.toString();
		String afterDelete2 = "1 2 3 9994 4 5";
		assertEquals(afterDelete2,values2);


	}
	
	public void testContains() {
		IntSequence testsequence = new IntSequence(10);
		testsequence.add(1);
		testsequence.add(2);
		testsequence.add(3);
		testsequence.add(4);
		testsequence.add(5);
		assertTrue(testsequence.contains(3));
		assertEquals(testsequence.contains(10),false);
	}
	

}

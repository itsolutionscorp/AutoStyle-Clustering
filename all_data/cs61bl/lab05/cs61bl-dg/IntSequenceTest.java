import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testIntSequence(){
		IntSequence Seq = new IntSequence(5);
		assertTrue(Seq.maxLength == 5);
		assertTrue(Seq.myCount == 0);
		assertTrue(Seq.myValues.length == Seq.maxLength);
	}
	
	public void testAdd(){
		IntSequence Seq = new IntSequence(5);
		Seq.add(3);
		assertTrue(Seq.myValues[0] == 3);
		assertTrue(Seq.myCount == 1);
		Seq.add(6);
		assertTrue(Seq.myValues[1] == 6);
		assertTrue(Seq.myCount == 2);	
	}
	
	public void testIsEmpty(){
		IntSequence Seq = new IntSequence(4);
		assertTrue(Seq.isEmpty() == true);
		Seq.add(5);
		assertTrue(Seq.isEmpty() == false);
	}
	
	public void testSize(){
		IntSequence Seq = new IntSequence(3);
		assertTrue(Seq.size() == 0);
		Seq.add(5);
		assertTrue(Seq.size() == 1);
		Seq.add(8);
		assertTrue(Seq.size() == 2);
	}
	
	public void testElementAt(){
		IntSequence Seq = new IntSequence(5);
		Seq.add(0);
		Seq.add(1);
		Seq.add(2);
		Seq.add(3);
		Seq.add(4);
		assertTrue(Seq.elementAt(1) == 1);
		assertTrue(Seq.elementAt(2) == 2);
		assertTrue(Seq.elementAt(3) == 3);
	}
	
	public void testToString(){
		IntSequence Seq = new IntSequence(5);
		Seq.add(0);
		Seq.add(1);
		Seq.add(2);
		Seq.add(3);
		Seq.add(4);
		assertTrue(Seq.toString().equals("0 1 2 3 4 "));
	}
	
	public void testInsert() {
        IntSequence Seq = new IntSequence(5);
        Seq.add(3);
        Seq.add(4);
        Seq.add(5);
        Seq.add(6);
        Seq.insert(7, 2);
        assertTrue(Seq.myValues[0] == 3);
        assertTrue(Seq.myValues[1] == 4);
        assertTrue(Seq.myValues[2] == 7);
        assertTrue(Seq.myValues[3] == 5);
        assertTrue(Seq.myValues[4] == 6);
        assertTrue(Seq.myCount == 5);
    }
	
	public void testRemove(){
		IntSequence Seq = new IntSequence(6);
		Seq.add(0);
        Seq.add(1);
        Seq.add(2);
        Seq.add(3);
        Seq.add(4);
        Seq.add(5);
        int three = Seq.remove(3);
        assertTrue(three == 3);
        assertTrue(Seq.myCount == 5);
        assertTrue(Seq.myValues[3] == 4);
	}
	
	public void testContains() {
		IntSequence Seq = new IntSequence(5);
        Seq.add(3);
        Seq.add(4);
        Seq.add(5);
        Seq.add(6);
        assertTrue(Seq.contains(3));
        assertTrue(Seq.contains(4));
        assertTrue(Seq.contains(5));
        assertFalse(Seq.contains(10));
        assertFalse(Seq.contains(0));
	}
}

import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	public void testElementAt() {
		IntSequence seq1 = new IntSequence(50);
		for(int i = 0; i < 10; i++) {
			seq1.add(i);
		}
		assertEquals(8, seq1.elementAt(8));
		assertEquals(0, seq1.elementAt(0));
	}
	
	public void testInsert() {
		IntSequence seq1 = new IntSequence(50);
		for(int i = 1; i < 10; i++) {
			seq1.add(i);
		}
		seq1.insert(0, 0);

		assertEquals(0, seq1.elementAt(0));
		seq1.insert(0, 5);
		
		assertEquals(0, seq1.elementAt(5));
		assertEquals(5, seq1.elementAt(6));
		

	}
	public void testAdd() {
		IntSequence seq1 = new IntSequence(50);
		seq1.add(3);
		seq1.add(4);
		assertEquals(4, seq1.elementAt(1));
	}
	
	public void testIsEmpty(){ 
		IntSequence seq1 = new IntSequence(50);
		assertEquals(true, seq1.isEmpty());
		seq1.add(4);
		assertEquals(false, seq1.isEmpty());
		seq1.add(5);
		assertEquals(false, seq1.isEmpty());
	}
	
	public void testSize(){
		IntSequence seq1 = new IntSequence(50);
		assertEquals(0, seq1.size());
		for(int i = 1; i < 10; i++) {
			seq1.add(i);
		}
		assertEquals(9, seq1.size());		
	}
	
	public void testDelete(){
		IntSequence seq1 = new IntSequence(50);
		for(int i = 0; i < 10; i++) {
			seq1.add(i);
		}
		
		seq1.delete(2);
		assertEquals(3, seq1.elementAt(2));
		assertEquals(9, seq1.size());
	
	}
	
	public void testContains() {
		IntSequence seq1 = new IntSequence(50);
		assertEquals(false, seq1.contains(0));
		for(int i = 0; i < 10; i++) {
			seq1.add(i);
		}
		assertEquals(true, seq1.contains(0));
		assertEquals(false, seq1.contains(11));
		assertEquals(true, seq1.contains(5));
		seq1.delete(2);
		assertEquals(false, seq1.contains(2));
		seq1.insert(2, 2);
		assertEquals(true, seq1.contains(2));
	}
	
	public void testToString() {
		IntSequence seq1 = new IntSequence(50);
		for(int i = 0; i < 5; i++) {
			seq1.add(i);
		}
		String tester = "0 1 2 3 4 ";
		assertEquals(true, tester.equals(seq1.toString()));
	}
}

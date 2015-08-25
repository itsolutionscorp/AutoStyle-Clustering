import static org.junit.Assert.*;


import org.junit.Test;
import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence seq = new IntSequence(5); 
		assertTrue (seq.getCapacity() == 5); 
		assertTrue (seq.size() == 0); 
	}
	
	public void testIsEmpty() {
		IntSequence seq = new IntSequence(5); 
		assertTrue (seq.isEmpty() == true); 
	
		seq.add(1); 
		assertTrue (seq.isEmpty() == false); 
	}
	
	public void testElementAt() {
		IntSequence seq = new IntSequence(5); 
		seq.add(0);
		seq.add(1);
		seq.add(2); 
		seq.add(3);
		assertTrue (seq.elementAt(0) == 0); 
		assertTrue (seq.elementAt(3) == 3);
		assertTrue (seq.elementAt(4) == -1); 
	}
	
	public void testToString() {
		IntSequence seq = new IntSequence(5); 
		seq.add(0);
		seq.add(1);
		seq.add(2); 
		seq.add(3);
		String s = seq.toString();
		assertTrue (s.equals("0 1 2 3"));
		
		IntSequence seq2 = new IntSequence(5);
		String s2 = seq2.toString();
		assertTrue (s2.equals(""));
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(5); 
		seq.add(0);
		assertTrue (seq.elementAt(0) == 0); 
		seq.add(1);
		assertTrue (seq.elementAt(1) == 1);
		seq.add(2); 
		assertTrue(seq.size() == 3);
		seq.add(3);
		assertTrue(seq.size() == 4);
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(5); 
		seq.add(0);
		seq.add(1);
		seq.add(2); 
		seq.add(3);
		seq.insert(10, 2);
		String s = seq.toString();
		assertTrue (s.equals("0 1 10 2 3"));
		assertTrue (seq.size() == 5); 
		assertTrue (seq.elementAt(2) == 10); 
		
		IntSequence seq2 = new IntSequence(5); 
		seq2.add(0);
		seq2.add(1);
		seq2.add(2); 
		seq2.add(3);
		
		seq2.insert(10,0); 
		String s2 = seq2.toString();
		assertTrue (s2.equals("10 0 1 2 3"));
		assertTrue (seq2.size() == 5); 
		assertTrue (seq2.elementAt(0) == 10); 
		
		IntSequence seq3 = new IntSequence(5); 
		seq3.add(0);
		seq3.add(1);
		seq3.add(2); 
		seq3.add(3);
		
		seq3.insert(10,4); 
		String s3 = seq3.toString();
		assertTrue (s3.equals("0 1 2 3 10"));
		assertTrue (seq3.size() == 5); 
		assertTrue (seq3.elementAt(4) == 10); 
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(5); 
		seq.add(0);
		seq.add(1);
		seq.add(2); 
		seq.add(3);
		
		seq.remove(2);
		String s = seq.toString();
		assertTrue (s.equals("0 1 3"));
		
		IntSequence seq2 = new IntSequence(5); 
		seq2.add(0);
		seq2.add(1);
		seq2.add(2); 
		seq2.add(3);
		
		seq2.remove(0);
		String s2 = seq2.toString();
		assertTrue (s2.equals("1 2 3"));
		
		IntSequence seq3 = new IntSequence(5); 
		seq3.add(0);
		seq3.add(1);
		seq3.add(2); 
		seq3.add(3);
		
		seq3.remove(3);
		String s3 = seq3.toString();
		assertTrue (s3.equals("0 1 2"));
	}
	
	public void testContains() {
		IntSequence seq = new IntSequence(5); 
		seq.add(0);
		seq.add(1);
		seq.add(2); 
		seq.add(3);
		
		assertTrue (seq.contains(0));
		assertTrue (seq.contains(1));
		assertTrue (seq.contains(3)); 
		assertFalse (seq.contains(5));
	}

}

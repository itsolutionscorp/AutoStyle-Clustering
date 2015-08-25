import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor(){
		IntSequence seq = new IntSequence(2);
		assertTrue(seq.isEmpty());
		seq.add(5);
		assertTrue(seq.myCount == 1);
		assertTrue(seq.size()==1);
	}
	public void testadd(){
		IntSequence seq = new IntSequence(2);
		seq.add(5);
		assertTrue(seq.myCount == 1);
		assertTrue(seq.elementAt(0) == 5);
	}
	public void testString(){
		IntSequence seq = new IntSequence(2);
		seq.add(5);
		seq.add(3);
		String result = seq.toString();
		System.out.println(result);
		assertTrue(result.equals("5 3"));
	}
	public void testRemove(){
		IntSequence seq = new IntSequence(3);
		seq.add(5);
		seq.add(3);
		seq.add(4);
		assertTrue(seq.remove(0) == 5);
		assertTrue(seq.elementAt(0) == 3);
		assertTrue(seq.toString().equals("3 4"));
		assertTrue(seq.size() == 2);
	}
	public void testInsert(){
		IntSequence seq = new IntSequence(3);
		seq.add(5);
		seq.add(3);
		seq.insert(4, 1);
		assertTrue(seq.elementAt(1)==4);
		assertTrue(seq.elementAt(2)==3);
	}
	public void testContains(){
		IntSequence seq = new IntSequence(3);
		seq.add(5);
		seq.add(3);
		assertTrue(seq.contains(3));
		assertFalse(seq.contains(6));
	}
	
}

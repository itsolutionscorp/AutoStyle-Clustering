import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	
	public void testtoString()
	{
		IntSequence sequence1 = new IntSequence(5);
		sequence1.add(1);
		sequence1.add(2);
		sequence1.add(3);
		assertEquals(sequence1.toString(), "1 2 3");
		IntSequence sequence2 = new IntSequence(5);
		sequence2.add(0);
		sequence2.add(0);
		sequence2.add(0);
		assertEquals(sequence2.toString(), "0 0 0");
		IntSequence sequence3 = new IntSequence(5);
		sequence3.add(0);
		sequence3.add(1);
		sequence3.add(0);
		assertEquals(sequence3.toString(), "0 1 0");
	}
	
	public void testAdd()
	{
		IntSequence sequence2 = new IntSequence(3);
		sequence2.add(1);
		assertEquals(sequence2.elementAt(0), 1);
	}
	
	public void testisEmpty()
	{
		IntSequence sequence3 = new IntSequence(2);
		assertTrue(sequence3.isEmpty());
	}
	
	public void testSize()
	{
		IntSequence sequence4 = new IntSequence(2);
		sequence4.add(45);
		assertTrue(sequence4.size() == 1);
		IntSequence sequence2 = new IntSequence(5);
		sequence2.add(0);
		sequence2.add(0);
		sequence2.add(0);
		assertTrue(sequence2.size() == 3);
	}
	
	public void testElementAt()
	{
		IntSequence sequence5 = new IntSequence(2);
		sequence5.add(22);
		sequence5.add(44);
		assertTrue(sequence5.elementAt(1) == 44);
	}
	public void testRemove()
	{
		IntSequence sequence5 = new IntSequence(2);
		sequence5.add(22);
		sequence5.add(44);
		assertTrue(sequence5.remove(1) == 44);
		assertEquals(sequence5.toString(), "22");
		IntSequence sequence6 = new IntSequence(2);
		sequence6.add(0);
		assertTrue(sequence6.remove(0) == 0);
		assertTrue(sequence6.isEmpty());
	}
	public void testInsert()
	{
		IntSequence sequence1 = new IntSequence(5);
		sequence1.add(1);
		sequence1.add(2);
		sequence1.add(3);
		sequence1.insert(100, 1);
		assertEquals(sequence1.toString(), "1 100 2 3");
		IntSequence sequence2 = new IntSequence(5);
		sequence2.add(1);
		sequence2.add(2);
		sequence2.add(3);
		sequence2.insert(100, 0);
		assertEquals(sequence2.toString(), "100 1 2 3");
		IntSequence sequence3 = new IntSequence(5);
		sequence3.add(1);
		sequence3.add(0);
		sequence3.add(3);
		sequence3.insert(100, 3);
		assertEquals(sequence3.toString(), "1 0 3 100");

	}
	public void testContains()
	{
		IntSequence sequence1 = new IntSequence(5);
		sequence1.add(1);
		sequence1.add(2);
		sequence1.add(3);
		assertTrue(sequence1.contains(1));
		IntSequence sequence2 = new IntSequence(5);
		sequence2.add(1);
		sequence2.add(2);
		sequence2.add(3);
		assertTrue(sequence1.contains(0) == false);
}
}

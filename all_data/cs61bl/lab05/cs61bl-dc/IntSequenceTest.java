import junit.framework.TestCase;


public class IntSequenceTest extends TestCase 
{
	IntSequence sequence = new IntSequence(20);
	public void testConstructor()
	{
		assertTrue(sequence.myCount == 0);
		assertTrue(sequence.myValues.length == 20);
	}
	public void testSize()
	{
		assertTrue(sequence.isEmpty());
		assertTrue(sequence.size() == 0);
		sequence.add(0);
		assertFalse(sequence.isEmpty());
		assertTrue(sequence.size() == 1);
	}
	public void testAdd()
	{
		assertEquals(sequence.toString(), "");
		sequence.add(1);
		assertEquals(sequence.toString(), "1");
		assertTrue(sequence.size() == 1);
	}
	public void testElementAt()
	{
		for (int i = 0; i <= 10; i++)
			sequence.add(i);
		assertTrue(sequence.elementAt(3) == 3);
	}
	public void testRemove()
	{
		for (int i = 0; i < 5; i++)
			sequence.add(i);
		sequence.remove(0);
		assertEquals(sequence.toString(), "1 2 3 4");
		sequence.remove(2);
		assertEquals(sequence.toString(), "1 2 4");
		sequence.remove(2);
		assertEquals(sequence.toString(), "1 2");
	}
	public void testInsert()
	{
		for (int i = 0; i < 5; i++)
			sequence.add(i);
		sequence.insert(0, 0);
		assertEquals(sequence.toString(), "0 0 1 2 3 4");
		sequence.insert(6, 0);
		assertEquals(sequence.toString(), "0 0 1 2 3 4 0");
		sequence.insert(2, 0);
		assertEquals(sequence.toString(), "0 0 0 1 2 3 4 0");
	}
	public void testContains()
	{
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		assertTrue(sequence.contains(3));
		assertFalse(sequence.contains(6));
		assertFalse(sequence.contains(0));
	}
}

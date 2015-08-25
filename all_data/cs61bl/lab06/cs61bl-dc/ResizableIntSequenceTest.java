import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase 
{
	ResizableIntSequence sequence = new ResizableIntSequence(5);
	
	public void testAdd()
	{
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		assertEquals(sequence.toString(), "1 2 3 4 5");
		sequence.add(6);
		assertEquals(sequence.toString(), "1 2 3 4 5 6");
	}
	public void testInsert()
	{
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		assertEquals(sequence.toString(), "1 2 3 4 5");
		sequence.insert(0, 0);
		assertEquals(sequence.toString(), "0 1 2 3 4 5");
		sequence.insert(6, 6);
		assertEquals(sequence.toString(), "0 1 2 3 4 5 6");
		sequence.insert(3, 0);
		assertEquals(sequence.toString(), "0 1 2 0 3 4 5 6");
	}
	public void testRemove()
	{
		sequence = new ResizableIntSequence(40);
		for (int i = 1; i <= 11; i ++)
			sequence.add(i);
		sequence.remove(10);
		assertEquals(sequence.toString(), "1 2 3 4 5 6 7 8 9 10");
		assertTrue(sequence.myValues.length == 40);
		sequence.remove(0);
		assertEquals(sequence.toString(), "2 3 4 5 6 7 8 9 10");
		assertTrue(sequence.myValues.length == 20);
	}
}

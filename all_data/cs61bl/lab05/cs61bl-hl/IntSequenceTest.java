import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testIntSequence(){
		IntSequence sequence = new IntSequence(5);
		assertEquals(0, sequence.myCount);
		assertEquals(5, sequence.myValues.length);
		assertTrue(sequence.isEmpty());
	}
	
	public void testAdd(){
		IntSequence sequence = new IntSequence(5);
		sequence.add(3);
		sequence.add(4);
		assertEquals(sequence.myValues[0], 3);
		assertEquals(sequence.myValues[1], 4);
		assertEquals(sequence.myValues[2], 0);
		assertEquals(sequence.myValues[3], 0);
		assertEquals(sequence.myValues[4], 0);
		assertEquals(sequence.myCount, 2);
	}
	
	public void testInsert(){
		IntSequence sequence = new IntSequence(10);
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		sequence.insert(9, 2);
		int[] expected = {1, 2, 9, 3, 4, 5, 0, 0, 0, 0};
		for (int i = 0; i < expected.length; i++){
			assertEquals(sequence.myValues[i], expected[i]);			
		}
		
		sequence.insert(10, 0);
		int[] expected2 = {10, 1, 2, 9, 3, 4, 5, 0, 0, 0};
		for (int i = 0; i < expected2.length; i++){
			assertEquals(sequence.myValues[i], expected2[i]);			
		}
		
		sequence.insert(10, 6);
		int[] expected3 = {10, 1, 2, 9, 3, 4, 10, 5, 0, 0};
		for (int i = 0; i < expected3.length; i++){
			assertEquals(sequence.myValues[i], expected3[i]);			
		}
		
		sequence.insert(11, 8);
		int[] expected4 = {10, 1, 2, 9, 3, 4, 10, 5, 11, 0};
		for (int i = 0; i < expected4.length; i++){
			assertEquals(sequence.myValues[i], expected4[i]);
		}
	}
	
	public void testSize(){
		IntSequence sequence = new IntSequence(10);
		assertEquals(sequence.size(), 0);
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		assertFalse(sequence.isEmpty());
		assertEquals(sequence.size(), 5);
	}
	
	public void testElementAt(){
		IntSequence sequence = new IntSequence(10);
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		for (int i = 0; i < 5; i++)
			assertEquals(sequence.elementAt(i), i+1);
	}
	
	public void testToString(){
		IntSequence sequence = new IntSequence(10);
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		assertEquals("1 2 3 4 5", sequence.toString());

		IntSequence sequence2 = new IntSequence(10);
		sequence2.add(0);
		assertEquals("0", sequence2.toString());
		
		IntSequence sequence3 = new IntSequence(10);
		assertEquals("", sequence3.toString());
	}
	
	public void testRemove(){
		IntSequence sequence = new IntSequence(10);
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		sequence.remove(3);
		int[] expected = {1,2,3,5,0,0,0,0,0,0};
		for(int i=0;i<10;i++){
			assertEquals(expected[i],sequence.myValues[i]);
		}
		sequence.remove(0);
		expected = new int[]{2,3,5,0,0,0,0,0,0,0};
		for(int i=0;i<10;i++){
			assertEquals(expected[i],sequence.myValues[i]);
		}
		sequence.remove(2);
		expected = new int[]{2,3,0,0,0,0,0,0,0,0};
		for(int i=0;i<10;i++){
			assertEquals(expected[i],sequence.myValues[i]);
		}
	}
	public void testContains(){
		IntSequence sequence = new IntSequence(10);
		for (int i = 1; i <= 5; i++)
			sequence.add(i);
		assertTrue(sequence.contains(3));
		assertFalse(sequence.contains(6));
		IntSequence sequence2 = new IntSequence(10);
		assertFalse(sequence2.contains(0));
	}
	
}

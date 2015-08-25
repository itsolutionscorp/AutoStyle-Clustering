import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd(){
		ResizableIntSequence sequence = new ResizableIntSequence(4);
		for(int i=1;i<=5;i++){
			sequence.add(i);
		}
		assertEquals(sequence.myCount, 5);
		int[] test = new int[]{1, 2, 3, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int i=0;i< 5;i++){
			assertEquals(sequence.myValues[i], test[i]);
		}
	}
	
	public void testInsert(){
		ResizableIntSequence sequence = new ResizableIntSequence(4);
		for(int i=1;i<=5;i++){
			sequence.add(i);
		}
		sequence.insert(6, 7);
		assertEquals(sequence.myCount, 8);
		assertEquals(sequence.myValues.length, 14);
		
		int[] test = new int[]{1, 2, 3, 4, 5, 0, 0, 6, 0, 0, 0, 0, 0, 0};
		for(int i=0;i < sequence.myValues.length;i++){
			assertEquals(sequence.myValues[i], test[i]);
		}
		
		sequence = new ResizableIntSequence(8);
		for(int i=1;i<=4;i++){
			sequence.add(i);
		}
		sequence.insert(3, 6);
		assertEquals(sequence.myCount, 7);
		assertEquals(sequence.myValues.length, 8);
		
		test = new int[]{1, 2, 3, 4, 0, 0, 3, 0};
		for(int i=0;i < 8;i++){
			assertEquals(sequence.myValues[i], test[i]);
		}
	}
	
	public void testRemove(){
		ResizableIntSequence sequence = new ResizableIntSequence(20);
		for(int i=1;i<=5;i++){
			sequence.add(i);
		}
		sequence.remove(3);
		int[] test = {1, 2, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int i=0;i<12;i++){
			assertEquals(sequence.myValues[i],test[i]);
		}
		assertEquals(sequence.myValues.length, 12);
	}
}

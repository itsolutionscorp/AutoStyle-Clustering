import java.util.Arrays;
import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	ResizableIntSequence sequence = new ResizableIntSequence(10);
	public void testAdd(){
		sequence.add(1);
		sequence.add(2);
		sequence.add(3);
		sequence.add(4);
		sequence.add(5);
		sequence.add(6);
		assertTrue(sequence.myCount == 6);
		assertTrue(sequence.myValues[5] == 6);
		sequence.add(7);
		sequence.add(8);
		sequence.add(9);
		sequence.add(10);
		System.out.println(sequence);
		System.out.println(sequence.myCount);
		System.out.println(sequence.myValues.length);
		sequence.add(11);
		System.out.println(sequence);
		assertTrue(sequence.myCount == 11);
		assertTrue(sequence.myValues[10] == 11);	
	}
	
	public void testInsert(){
		sequence.add(1);
		sequence.add(2);
		sequence.add(3);
		sequence.add(4);
		sequence.add(5);
		sequence.insert(8, 2);
		assertTrue(sequence.myValues[2] == 8);
		assertTrue(sequence.myCount == 6);
		assertTrue(sequence.myValues[5] == 5);
		sequence.insert(8, 2);
		sequence.insert(8, 2);
		sequence.insert(8, 2);
		sequence.insert(8, 2);
		System.out.println(sequence);
		System.out.println(sequence.myCount);
		System.out.println(sequence.myValues.length);
		sequence.insert(5, 2);
		System.out.println(sequence);
		assertTrue(sequence.myCount == 11);
		assertTrue(sequence.myValues[10] == 5);
		assertTrue(sequence.myValues[2] == 5);
	}
	
	public void testRemove(){
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);
        sequence.add(4);
        sequence.add(5);
        sequence.remove(2);
        assertTrue(sequence.myValues.length == 4);
        System.out.println(Arrays.toString(sequence.myValues));
        sequence.add(6);
        sequence.add(7);
        sequence.add(8);
        sequence.add(9);
        sequence.remove(4);
        assertTrue(sequence.myValues.length == 7);
    }
}

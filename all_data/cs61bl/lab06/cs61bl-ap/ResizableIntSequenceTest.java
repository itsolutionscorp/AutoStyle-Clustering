import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
 
	public void testAdd(){
		ResizableIntSequence in1 = new ResizableIntSequence(7); 
		in1.add(3);
		in1.add(56);
		in1.add(34);
		in1.add(5);
		assertTrue(4 == in1.myCount);
		assertTrue(in1.myValues[2] == 34);

		ResizableIntSequence in2 = new ResizableIntSequence(3); 
		in2.add(3);
		in2.add(56);
		in2.add(34);
		in2.add(5);
		assertTrue(4 == in2.myCount);
		assertTrue(in1.myValues[3] == 5);
	}
	
	public void testInsert() {
		//test case where there is space inside the array
		ResizableIntSequence in1 = new ResizableIntSequence(7); 
		in1.add(3);
		in1.add(56);
		in1.add(34);
		in1.add(5);
		in1.insert(23, 2);
		ResizableIntSequence in2 = new ResizableIntSequence(7);
		in2.add(3);
		in2.add(56);
		in2.add(23);
		in2.add(34);
		in2.add(5);
		assertEquals(in2.toString(), in1.toString());
		assertTrue(in2.myCount == in1.myCount);
		// test case where array is full
		ResizableIntSequence in3 = new ResizableIntSequence(4);
		in3.add(3);
		in3.add(56);
		in3.add(34);
		in3.add(5);
		in3.insert(23, 2);
		ResizableIntSequence in4 = new ResizableIntSequence(4);
		in4.add(3);
		in4.add(56);
		in4.add(23);
		in4.add(34);
		in4.add(5);
		assertEquals(in2.toString(), in1.toString());
		assertTrue(in2.myCount == in1.myCount);
	}
	
	public void testRemove() {
		//test case where there is space inside the array
		ResizableIntSequence in1 = new ResizableIntSequence(7); 
		in1.add(3);
		in1.add(56);
		in1.add(34);
		in1.add(5);
		in1.remove(1);
		ResizableIntSequence in2 = new ResizableIntSequence(7);
		in2.add(3);
		in2.add(34);
		in2.add(5);
		assertEquals("3 34 5", in1.toString());
		assertTrue(in2.myCount == in1.myCount);
		
		// test case where array is full
		ResizableIntSequence in3 = new ResizableIntSequence(4);
		in3.add(3);
		in3.add(56);
		in3.add(34);
		in3.add(5);
		in3.remove(1);
		ResizableIntSequence in4 = new ResizableIntSequence(4);
		in4.add(3);
		in4.add(34);
		in4.add(5);
		assertEquals(in4.toString(), in3.toString());
		assertTrue(in4.myCount == in3.myCount);
		
		// test case 3 with empty thing
		ResizableIntSequence in5 = new ResizableIntSequence(4);
		// in5.remove(0);
		// the above method will give an error as there is nothing to remove
	}	
}

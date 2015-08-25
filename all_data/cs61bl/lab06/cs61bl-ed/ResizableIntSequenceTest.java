import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testConstructor(){
		ResizableIntSequence test = new ResizableIntSequence(5);
		assertTrue (test.myValues.length  == 5);
	}
	public void testadd(){
		ResizableIntSequence test = new ResizableIntSequence(2);
		test.add(4);
		assertTrue (test.myValues.length  == 2);
		test.add(6);
		assertTrue (test.myValues.length  == 2);
		assertEquals("6 4",test.toString());
		test.add(5);
		assertTrue (test.myValues.length  == 3);
		assertEquals("5 6 4",test.toString());
		
	
		
	}
	public void testinsert(){
		ResizableIntSequence test1 = new ResizableIntSequence(4);
		test1.add(4);
		test1.add(6);
		test1.add(4);
		test1.insert(3,1);	
		assertTrue (test1.myValues.length  == 4);
		assertEquals("4 3 6 4",test1.toString());
		test1.insert(3,2);
		assertTrue (test1.myValues.length  == 5);
		assertEquals("4 3 3 6 4",test1.toString());
		
		
		
	}
	public void testremove(){
		ResizableIntSequence test1 = new ResizableIntSequence(6);
		test1.add(4);
		test1.add(6);
		test1.add(4);
		test1.insert(3,1);	
		assertTrue (test1.myValues.length  == 6);
		assertEquals("4 3 6 4",test1.toString());
		int rm_ele = test1.remove(2);
		assertTrue (rm_ele  == 6);
		assertEquals("4 3 4",test1.toString());
		assertEquals(test1.myCount,test1.myValues.length);
	
}
}

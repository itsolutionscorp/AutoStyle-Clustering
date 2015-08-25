import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testadd(){
		ResizableIntSequence test = new ResizableIntSequence(3);
		test.add(1);
		test.add(2);
		
		test.add(3);
		assertEquals(test.toString(),"3 2 1");
		assertTrue(test.myCount == 3);
		
		test.add(4);
		assertEquals(test.toString(),"4 3 2 1");
		assertTrue(test.myCount == 4);}
	public void testRemove(){
		ResizableIntSequence test2 = new ResizableIntSequence(3);
		test2.add(1);
		test2.add(2);
		test2.add(3);
		assertEquals(test2.toString(),"3 2 1");
		assertTrue(test2.myCount==3);
		test2.remove(1);
		assertEquals(test2.toString(),"3 1");
		assertTrue(test2.myCount==2);
	}
		
	public void testInsert(){
		ResizableIntSequence test1 = new ResizableIntSequence(3);
		test1.add(1);
		test1.add(2);
		
		test1.insert(3,0);
		assertEquals(test1.toString(),"3 2 1");
		assertTrue(test1.myCount == 3);
		test1.insert(250, 1);
		assertEquals(test1.toString(),"3 250 2 1");
		assertTrue(test1.myCount ==4);
		test1.insert(12, 3);
		assertEquals(test1.toString(),"3 250 2 12 1");
		assertTrue(test1.myCount ==5);
		
	}
		

	

}

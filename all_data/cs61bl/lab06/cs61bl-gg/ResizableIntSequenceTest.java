import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd(){
		ResizableIntSequence test = new ResizableIntSequence(5);
		test.add(5);
		assertTrue(test.myCount==1);
		test.add(6);
		test.add(7);
		assertTrue(test.myCount==3);
		test.add(99);
		test.add(88);
		test.add(0);
		assertTrue(test.myCount==6);
		test.add(10);
		assertTrue(test.myCount==7);
	}

	public void testInsert(){
		ResizableIntSequence test = new ResizableIntSequence(10);
		test.add(17);
		test.add(43);
		test.add(9);
		test.add(10);
		test.add(25);
		test.insert(64,2);
		assertTrue(test.size()==6);
		assertEquals("17 43 64 9 10 25",test.toString());
		test.insert(1,0);
		assertTrue(test.size()==7);
		assertEquals("1 17 43 64 9 10 25",test.toString());
		test.insert(11,7);
		assertTrue(test.size()==8);
		assertEquals("1 17 43 64 9 10 25 11",test.toString());
		test.insert(34,7);
		assertTrue(test.size()==9);
		assertEquals("1 17 43 64 9 10 25 34 11",test.toString());
		test.insert(53,7);
		assertTrue(test.size()==10);
		assertEquals("1 17 43 64 9 10 25 53 34 11",test.toString());
		test.insert(2,9);
		assertTrue(test.size()==11);
		assertEquals("1 17 43 64 9 10 25 53 34 2 11",test.toString());
		test.insert(8,0);
		assertTrue(test.size()==12);
		assertEquals("8 1 17 43 64 9 10 25 53 34 2 11",test.toString());
	}
	
	public void testRemove(){
		ResizableIntSequence test = new ResizableIntSequence(10);
		test.add(17);
		test.add(43);
		test.add(9);
		test.add(10);
		test.add(25);
		test.add(67);
		test.add(99);
		assertTrue(test.remove(2)==9);
		assertTrue(test.size()==6);
		assertEquals("17 43 10 25 67 99",test.toString());
		assertTrue(test.remove(0)==17);
		assertTrue(test.size()==5);
		assertEquals("43 10 25 67 99",test.toString());
		assertTrue(test.remove(4)==99);
		assertTrue(test.size()==4);
		assertEquals("43 10 25 67",test.toString());
		assertTrue(test.remove(3)==67);
		assertTrue(test.size()==3);
		assertEquals("43 10 25",test.toString());
		assertTrue(test.remove(0)==43);
		assertTrue(test.size()==2);
		assertTrue(test.myValues.length==5);
		assertEquals("10 25",test.toString());
	}

}

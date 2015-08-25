import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd(){
		ResizableIntSequence test=new ResizableIntSequence(0);
		for(int i=0; i<60; i++){
			test.add(i);
		}
		assertEquals(test.toString(),"0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59");
		assertEquals(test.myCount,60);
		test.add(5);
		assertTrue(test.myCount == 61);
		assertTrue(test.myValues.length==70);
	}
	
	public void testElementAdd(){
		ResizableIntSequence test=new ResizableIntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		assertEquals(test.elementAt(0), 1);
		assertEquals(test.elementAt(1), 2);
		assertEquals(test.elementAt(2), 3);
		assertEquals(test.size(),3);
//		test.elementAt(3); Does successfully exit, and prints it out
		
		
	}
	public void testRemove(){
		ResizableIntSequence test1=new ResizableIntSequence(0);
		for(int i=0; i<200; i++){
			test1.add(i);
		}
		for(int i=0; i<200; i++){
			test1.remove(0);
		}
		assertTrue(test1.isEmpty());
		assertEquals(test1.myValues.length,100);
		assertEquals(test1.myCount,0);
		IntSequence test=new IntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		test.remove(0);
		assertEquals(test.toString(), "2 3 4 5 6");
		test.remove(2);
		assertEquals(test.toString(), "2 3 5 6");
		test.remove(3);
		assertEquals(test.toString(), "2 3 5");
		test.remove(0);
		test.remove(0);
		test.remove(0);
		assertTrue(test.isEmpty());
	}
	public void testInsert(){
		ResizableIntSequence test1=new ResizableIntSequence(0);
		for(int i=0; i<60; i++){
			test1.add(i);
		}
		assertEquals(test1.toString(),"0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59");
		assertEquals(test1.myCount,60);
		test1.insert(60,60);
		assertTrue(test1.myCount == 61);
		assertTrue(test1.myValues.length==70);
		assertEquals(test1.toString(),"0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60");
		ResizableIntSequence test=new ResizableIntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.insert(0, 0);
		assertEquals(test.toString(),"0 1 2 3");
		test.insert(9, 2);
		assertEquals(test.toString(),"0 1 9 2 3");
		test.insert(5, 5);
		assertEquals(test.toString(),"0 1 9 2 3 5");
	}
	
	public void testContains() {
		ResizableIntSequence test=new ResizableIntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.insert(0, 0);
		assertTrue(test.contains(1));
		assertFalse(test.contains(8));
	}

}

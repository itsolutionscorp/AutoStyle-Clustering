import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void testadd() {
		IntSequence ex1 = new IntSequence(5);
		int[] testArray = new int[5];
		testArray[0] = 1;
		ex1.add(1);
		assertTrue(ex1.myValues[0] == testArray[0]);
		assertTrue(ex1.myValues[1] == testArray[1]);
		assertTrue(ex1.myValues[2] == testArray[2]);
		assertTrue(ex1.myValues[3] == testArray[3]);
		assertTrue(ex1.myValues[4] == testArray[4]);
		assertTrue(ex1.myCount == 1);
	}
	
	@Test
	public void testelementAt(){
		IntSequence ex2 = new IntSequence(5);
		ex2.add(1);
		assertTrue(ex2.elementAt(0)==1);
		assertTrue(ex2.myValues[0]==1);
		ex2.add(0);
		assertTrue(ex2.myValues[1]==0);
		ex2.add(3);
		ex2.add(4);
		ex2.add(5);
		assertTrue(ex2.myValues[4]==5);
	}
	
	@Test
	public void testisEmpty(){
		IntSequence ex3 = new IntSequence(5);
		assert(ex3.isEmpty()==true);
		ex3.add(0);
		assert(ex3.isEmpty()==false);
	}
	
	@Test
	public void testsize(){
		IntSequence ex4 = new IntSequence(5);
		assert(ex4.size()==0);
		ex4.add(1);
		assert(ex4.size()==1);
		ex4.add(2);
		ex4.add(3);
		ex4.add(4);
		ex4.add(5);
		assert(ex4.size()==5);
//		ex4.add(6);
//		assert(ex4.size()==5);
		
	}
	
	@Test
	public void testtoString(){
		IntSequence ex5 = new IntSequence(5);
		assert(ex5.toString()=="");
		ex5.add(1);
		assert(ex5.toString()=="1");
		ex5.add(2);
		ex5.add(3);
		ex5.add(4);
		ex5.add(5);
		assert(ex5.toString()=="1 2 3 4 5");
	}
	
	@Test
	public void testRemove() {
		IntSequence ex6 = new IntSequence(5);
		ex6.add(1);
		ex6.add(2);
		ex6.add(3);
		ex6.add(4);
		ex6.add(5);
		ex6.remove(2);
		assertTrue(ex6.elementAt(0) == 1);
		assertTrue(ex6.elementAt(1) == 2);
		assertTrue(ex6.elementAt(2) == 4);
		assertTrue(ex6.elementAt(3) == 5);
		assertTrue(ex6.elementAt(4) == 0);
	}
	
	@Test
	public void testInsert() {
		IntSequence s = new IntSequence(5);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.add(5);
		s.insert(9,0);
		assertTrue(s.elementAt(0) == 9);
		assertTrue(s.elementAt(1) == 1);
		assertTrue(s.elementAt(2) == 2);
		assertTrue(s.elementAt(3) == 3);
		assertTrue(s.elementAt(4) == 4);
		assertTrue(s.myCount==5);
		
		IntSequence s2 = new IntSequence(5);
		s2.add(1);
		s2.add(2);
		s2.add(3);
		s2.insert(7,2);
		assertTrue(s2.elementAt(0) == 1);
		assertTrue(s2.elementAt(1) == 2);
		assertTrue(s2.elementAt(2) == 7);
		assertTrue(s2.elementAt(3) == 3);
		assertTrue(s2.elementAt(4) == 0);
		assertTrue(s2.myCount == 4);
		
		IntSequence s3 = new IntSequence(5);
		s3.add(1);
		s3.add(2);
		s3.add(3);
		s3.insert(7,3);
		assertTrue(s3.elementAt(0) == 1);
		assertTrue(s3.elementAt(1) == 2);
		assertTrue(s3.elementAt(2) == 3);
		assertTrue(s3.elementAt(3) == 7);
		assertTrue(s3.elementAt(4) == 0);
		assertTrue(s3.myCount == 4);
	}
	
	@Test
	public void testContains() {
		IntSequence ex7 = new IntSequence(5);
		ex7.add(1);
		ex7.add(2);
		ex7.add(3);
		ex7.add(4);
		ex7.add(5);
		assertTrue(ex7.contains(4));
		assertFalse(ex7.contains(9));
	}
	
}

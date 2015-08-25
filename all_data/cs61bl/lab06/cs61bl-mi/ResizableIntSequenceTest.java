import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testConstructor() {
		ResizableIntSequence s = new ResizableIntSequence(5);
		assertTrue(s.myCount == 0);
		assertTrue(s.isEmpty() == true);
		assertTrue(s.size() == 0);
		ResizableIntSequence s2 = new ResizableIntSequence(0);
		assertTrue(s2.size() == 0);
		assertTrue(s2.myCount == 0);
		assertTrue(s2.isEmpty() == true);
	}
	
	@Test
	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.add(-4);
		s.add(0);
		s.add(17);
		assertTrue(s.toString().equals("-4 0 17"));
		assertTrue(s.size()==3);
		s.add(4);
		assertTrue(s.toString().equals("-4 0 17 4"));
		assertTrue(s.size()==4);
		s.add(0);
		assertTrue(s.toString().equals("-4 0 17 4 0"));
		assertTrue(s.size()==5);
	}
	
	@Test
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.add(-4);
		s.add(0);
		s.add(17);
		s.insert(3, 0);
		assertTrue(s.toString().equals("3 -4 0 17"));
		assertTrue(s.size()==4);
		s.insert(5,4);
		assertTrue(s.toString().equals("3 -4 0 17 5"));
		assertTrue(s.size()==5);
		s.insert(1,2);
		assertTrue(s.toString().equals("3 -4 1 0 17 5"));
		assertTrue(s.size()==6);
		s.insert(0,6);
		assertTrue(s.toString().equals("3 -4 1 0 17 5 0"));
		assertTrue(s.size()==7);
	}
	
	@Test
	public void testRemove(){
		ResizableIntSequence s1 = new ResizableIntSequence(22);
		ResizableIntSequence s2 = new ResizableIntSequence(10);
		ResizableIntSequence s3 = new ResizableIntSequence(5);
		s1.add(1);
		s1.remove();
		assertTrue(s1.myValues.length==11);
		s1.remove();
		assertTrue(s1.myValues.length==5); // for odd
		s2.remove();
		assertTrue(s2.myValues.length==10);
		s3.remove();
		assertTrue(s3.myValues.length==5); //nothing in IntSequence
	}
}


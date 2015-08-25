import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testConstructor(){
		ResizableIntSequence s = new ResizableIntSequence(5);
		assertTrue(s.myCount==0);
		assertTrue(s.myValues.length==5);
	}
	
	public void testIsEmpty(){
		ResizableIntSequence s = new ResizableIntSequence(20);
		assertTrue(s.isEmpty());
		s.add(2);
		assertTrue(!s.isEmpty());
	}
	
	public void testSize(){
		ResizableIntSequence s = new ResizableIntSequence(3);
		assertTrue(s.size()==0);
		s.add(5);
		assertTrue(s.size()==1);
		s.add(0);
		assertTrue(s.size()==2);
		s.add(7);
		assertTrue(s.size()==3);
	}
	
	//also tests add
	public void testElementAt(){
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.add(5);
		assertTrue(s.elementAt(0)==5);
		s.add(6);
		assertTrue(s.elementAt(1)==6);
		s.add(7);
		assertTrue(s.elementAt(2)==7);
		// Resizing works:
		s.add(9);
		assertTrue(s.elementAt(3) == 9);
		
	}

	public void testToString(){
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.add(1);
		assertEquals(s.toString(), "1");
		s.add(2);
		assertEquals(s.toString(),"1 2");
		s.add(3);
		assertEquals(s.toString(),"1 2 3");
	}
	
	public void testRemove(){
		ResizableIntSequence s = new ResizableIntSequence(5);
		s.add(4);
		s.add(5);
		s.add(8);
		s.add(6);
		s.add(7);
		s.remove(2);
		assertEquals(s.toString(), "4 5 6 7");
		assertEquals(s.myCount, 4);
		
		s.remove(0);
		assertEquals(s.toString(), "5 6 7");
		assertEquals(s.myCount, 3);
		s.add(8);
		s.add(9);
		System.out.println(s.toString());
		assertEquals(s.toString(), "5 6 7 8 9");
		s.remove(4);
		assertEquals(s.toString(), "5 6 7 8");
		
		
	}
	
	public void testInsert(){
		ResizableIntSequence s = new ResizableIntSequence(5);
		s.add(4);
		s.add(6);
		s.add(7);
		s.add(8);
		s.insert(5, 1);
		assertTrue(s.myValues[0]==4);
		assertTrue(s.myValues[1]==5);
		assertTrue(s.myValues[2]==6);
		assertTrue(s.myValues[3]==7);
		assertTrue(s.myValues[4]==8);
		assertTrue(s.myCount==5);
		
		ResizableIntSequence s1 = new ResizableIntSequence(5);
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.insert(0,0);
		assertTrue(s1.myValues[0]==0);
		assertTrue(s1.myValues[1]==1);
		assertTrue(s1.myValues[2]==2);
		assertTrue(s1.myValues[3]==3);
		
		ResizableIntSequence s2 = new ResizableIntSequence(5);
		s2.add(1);
		s2.add(2);
		s2.add(3);
		s2.insert(4, 3);
		assertTrue(s2.myValues[0]==1);
		assertTrue(s2.myValues[1]==2);
		assertTrue(s2.myValues[2]==3);
		assertTrue(s2.myValues[3]==4);
	}
	


}

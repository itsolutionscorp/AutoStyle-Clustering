import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testAdd(){
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		assertTrue(mySeq.myValues[0]==1);
		assertTrue(mySeq.myValues[1]==2);
		assertTrue(mySeq.myValues[2]==3);
		assertTrue(mySeq.myValues[3]==0);
		assertTrue(mySeq.myCount==3);

	}	
	
	public void testInsert(){
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		mySeq.insert(7, 1);
		assertTrue(mySeq.myValues[1]==7);
		assertTrue(mySeq.myValues[2]==2);
		assertTrue(mySeq.myCount==3);
			
	}
	
	public void testRemove(){
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		mySeq.remove(mySeq.myValues, 1);
		assertTrue(mySeq.myValues[1]==3);
		assertTrue(mySeq.myValues[2]==0);
		assertTrue(mySeq.myCount==2);
		
	}
		
	public void testIsEmpty(){
		IntSequence mySeq = new IntSequence(10);
		assertTrue(mySeq.isEmpty()==true);
		mySeq.add(1);
		assertTrue(mySeq.isEmpty()==false);
		mySeq.remove(mySeq.myValues, 0);
		assertTrue(mySeq.isEmpty()==true);
		
	}
	
	public void testSize(){
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		assertTrue(mySeq.myCount==3);
		mySeq.add(4);
		mySeq.add(5);
		mySeq.add(6);
		assertTrue(mySeq.myCount==6);
		mySeq.add(7);
		mySeq.add(8);
		mySeq.add(9);
		assertTrue(mySeq.myCount==9);
		
	}
	
	public void testElementAt(){
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		assertTrue(mySeq.elementAt(0)==1);
		assertTrue(mySeq.elementAt(1)==2);
		assertTrue(mySeq.elementAt(2)==3);
		
	}
	
	public void testContains(){
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		assertTrue(mySeq.contains(1)==true);
		assertTrue(mySeq.contains(5)==false);
		assertTrue(mySeq.contains(3)==true);
		
	}
}

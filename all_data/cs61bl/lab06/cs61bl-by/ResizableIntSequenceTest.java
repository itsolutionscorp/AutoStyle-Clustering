import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testAdd(){
		ResizableIntSequence a = new ResizableIntSequence(2);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		assertTrue(a.myCount == 4);
		assertTrue(a.myValues[0] == 5);
		assertTrue(a.myValues[1] == 6);
		assertTrue(a.myValues[2] == 7);
		assertTrue(a.myValues[3] == 8);
	}
	
	public void testinsert(){
		ResizableIntSequence a = new ResizableIntSequence(2);
		a.insert(5, 0);
		a.insert(7, 1);
		a.insert(8, 2);
		a.insert(6, 1);
		assertTrue(a.myCount == 4);
		assertTrue(a.myValues[0] == 5);
		assertTrue(a.myValues[1] == 6);
		assertTrue(a.myValues[2] == 7);
		assertTrue(a.myValues[3] == 8);
	}
	
	public void testremove(){
		ResizableIntSequence a = new ResizableIntSequence(100);
		for (int k = 0;k<20; k++){
			a.add(1);
		}
		assertTrue(a.myValues.length == 100);
		a.insert(5, 10);
		assertTrue(a.myCount == 21);
		assertTrue(a.remove(10) == 5);
		assertTrue(a.myValues.length == 50);
		assertTrue(a.myCount == 20);
	}
}

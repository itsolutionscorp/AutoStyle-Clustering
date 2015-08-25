import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testAdd(){
		ResizableIntSequence i = new ResizableIntSequence(2);
		assertTrue(i.myValues.length == 2);
		i.add(1);
		i.add(2);
		i.add(3);
		assertTrue(i.myValues.length == 3);
		assertTrue(i.size() == 3);
	}
	
	public void testInsert(){
		ResizableIntSequence i = new ResizableIntSequence(0);
		assertTrue(i.size() == 0);
		i.add(1);
		i.add(3);
		i.insert(2, 1);
		assertTrue(i.toString().equals("1 2 3"));
		assertTrue(i.size() == 3);
	}
	
	public void testRemove(){
		ResizableIntSequence i = new ResizableIntSequence(100);
		i.add(1);
		i.add(2);
		i.add(3);
		i.remove(2);
		assertTrue(i.toString().equals("1 2"));
		assertTrue(i.myValues.length <= 22);

	}
	

}

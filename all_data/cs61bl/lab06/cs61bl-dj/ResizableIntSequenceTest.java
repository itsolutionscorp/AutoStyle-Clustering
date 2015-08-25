
import junit.framework.TestCase;
//need tests
public class ResizableIntSequenceTest extends TestCase{

		
	public void testAdd() {
		ResizableIntSequence x = new ResizableIntSequence(4);
		x.add(1);
		x.add(2);
		x.add(3);
		x.add(4);
		assertTrue(x.getMyValues().length == 4);
		assertTrue(x.getMyCount()== 4);
		x.add(5);
		assertTrue(x.getMyValues().length == 5);
		assertTrue(x.getMyCount()== 5);	
	}

	
	public void testInsert() {
		ResizableIntSequence x = new ResizableIntSequence(4);
		x.add(1);
		x.add(2);
		x.add(3);
		x.add(4);
		assertTrue(x.getMyValues().length == 4);
		assertTrue(x.getMyCount()== 4);
		x.add(5);
		assertTrue(x.getMyValues().length == 5);
		assertTrue(x.getMyCount()== 5);	
		x.insert(8, 3);
		assertTrue(x.getMyValues().length == 6);
		assertTrue(x.getMyCount()== 6);
		assertTrue(x.getMyValues()[3] ==8);
		assertTrue(x.getMyValues()[4] ==4);
		x.insert(1, 6);
		assertTrue(x.getMyValues()[6] == 1);
		x.insert(7, 0);
		assertTrue(x.getMyValues()[0] == 7);
		assertTrue(x.getMyCount()== 8);
		assertTrue(x.getMyValues().length == 8);
	}



	public void testRemove() {
		ResizableIntSequence x = new ResizableIntSequence(3);
		x.add(1);
		x.add(2);
		x.add(3);
		assertTrue(x.getMyValues().length == 3);
		assertTrue(x.getMyCount()== 3);
		x.remove(0);
		assertTrue(x.getMyValues().length == 2);
		assertTrue(x.getMyCount()== 2);
		assertTrue(x.getMyValues()[0]== 2);
		x.remove(0);
		x.remove(0);
		assertTrue(x.getMyValues().length == 0);
		assertTrue(x.getMyCount()== 0);
		
		
	}
}

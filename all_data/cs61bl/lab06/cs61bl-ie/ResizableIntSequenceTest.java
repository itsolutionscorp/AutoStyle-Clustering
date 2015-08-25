import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void test() {
		ResizableIntSequence y = new ResizableIntSequence(3);
		y.add(1); y.add(2); y.add(3);
		assertEquals(y.toString(), "1 2 3");
		y.add(4);
		assertEquals(y.toString(), "1 2 3 4");
		y.add(1); y.add(2); y.add(3); y.add(4);
		assertEquals(y.toString(), "1 2 3 4 1 2 3 4");
		y.remove(7); y.remove(6); y.remove(5); 
		y.remove(4); y.remove(3); y.remove(2); 
		assertEquals(y.toString(), "1 2");
		y.remove(1);
		assertEquals(y.toString(), "1");
		System.out.println(y.myVals[1]);//checks that the array has shrunk
		
	}

}

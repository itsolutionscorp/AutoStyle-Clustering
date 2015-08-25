import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void Test_Remove() {
		ResizableIntSequence s = new ResizableIntSequence(7);
		s.add (3);
		s.add (-7);
		s.add (42);
		s.add (-11);
		s.add (0);
		s.add (6);
		s.add (9);
	
		s.remove(2);

		assertTrue(s.myCount == 6);
		assertTrue(s.myValues.length == 6);
		assertTrue(s.myValues[0] == 3);
		assertTrue(s.myValues[1] == -7);
		assertTrue(s.myValues[2] == -11);
		assertTrue(s.myValues[3] == 0);
		assertTrue(s.myValues[4] == 6);
		assertTrue(s.myValues[5] == 9);
		
		s.remove(0);

		assertTrue(s.myCount == 5);
		assertTrue(s.myValues.length == 5);
		assertTrue(s.myValues[0] == -7);
		assertTrue(s.myValues[1] == -11);
		assertTrue(s.myValues[2] == 0);
		assertTrue(s.myValues[3] == 6);
		assertTrue(s.myValues[4] == 9);
		
		s.remove(4);

		assertTrue(s.myCount == 4);
		assertTrue(s.myValues.length == 4);
		assertTrue(s.myValues[0] == -7);
		assertTrue(s.myValues[1] == -11);
		assertTrue(s.myValues[2] == 0);
		assertTrue(s.myValues[3] == 6);
	}
	
	public void Test_Insert() {
		ResizableIntSequence s = new ResizableIntSequence(7);
		s.add (3);
		s.add (-7);
		s.add (42);
		s.add (-11);
		s.add (0);
		s.add (6);
		s.add (9);
		s.add (7);
	
		s.insert(2, 2);

		assertTrue(s.myCount == 9);
		assertTrue(s.myValues[0] == 3);
		assertTrue(s.myValues[1] == -7);
		assertTrue(s.myValues[2] == 2);
		assertTrue(s.myValues[3] == 42);
		assertTrue(s.myValues[4] == -11);
		assertTrue(s.myValues[5] == 0);
		assertTrue(s.myValues[6] == 6);
		assertTrue(s.myValues[7] == 9);
		assertTrue(s.myValues[8] == 7);
		
		s. insert(3, 4);
		
		assertTrue(s.myCount == 10);
		assertTrue(s.myValues[0] == 3);
		assertTrue(s.myValues[1] == -7);
		assertTrue(s.myValues[2] == 2);
		assertTrue(s.myValues[3] == 4);
		assertTrue(s.myValues[4] == 42);
		assertTrue(s.myValues[5] == -11);
		assertTrue(s.myValues[6] == 0);
		assertTrue(s.myValues[7] == 6);
		assertTrue(s.myValues[8] == 9);
		assertTrue(s.myValues[9] == 7);
		
		s. insert(0, 0);
		
		assertTrue(s.myCount == 11);
		assertTrue(s.myValues[0] == 0);
		assertTrue(s.myValues[1] == 3);
		assertTrue(s.myValues[2] == -7);
		assertTrue(s.myValues[3] == 2);
		assertTrue(s.myValues[4] == 4);
		assertTrue(s.myValues[5] == 42);
		assertTrue(s.myValues[6] == -11);
		assertTrue(s.myValues[7] == 0);
		assertTrue(s.myValues[8] == 6);
		assertTrue(s.myValues[9] == 9);
		assertTrue(s.myValues[10] == 7);
	}
	

}

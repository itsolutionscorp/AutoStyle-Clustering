import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void Test_Remove() {
		IntSequence s = new IntSequence(10);
		s.add (3);
		s.add (-7);
		s.add (42);
		s.add (-11);
		s.add (0);
		s.add (6);
		s.add (9);
	
		s.remove(2);

		assertTrue(s.myCount == 6);
		assertTrue(s.myValues[0] == 3);
		assertTrue(s.myValues[1] == -7);
		assertTrue(s.myValues[2] == -11);
		assertTrue(s.myValues[3] == 0);
		assertTrue(s.myValues[4] == 6);
		assertTrue(s.myValues[5] == 9);
		
		s.remove(0);

		assertTrue(s.myCount == 5);
		assertTrue(s.myValues[0] == -7);
		assertTrue(s.myValues[1] == -11);
		assertTrue(s.myValues[2] == 0);
		assertTrue(s.myValues[3] == 6);
		assertTrue(s.myValues[4] == 9);
		
		s.remove(4);

		assertTrue(s.myCount == 4);
		assertTrue(s.myValues[0] == -7);
		assertTrue(s.myValues[1] == -11);
		assertTrue(s.myValues[2] == 0);
		assertTrue(s.myValues[3] == 6);
	}
	
	public void Test_Insert() {
		IntSequence s = new IntSequence(10);
		s.add (3);
		s.add (-7);
		s.add (42);
		s.add (-11);
		s.add (0);
		s.add (6);
		s.add (9);
	
		s.insert(2, 2);

		assertTrue(s.myCount == 8);
		assertTrue(s.myValues[0] == 3);
		assertTrue(s.myValues[1] == -7);
		assertTrue(s.myValues[2] == 2);
		assertTrue(s.myValues[3] == 42);
		assertTrue(s.myValues[4] == -11);
		assertTrue(s.myValues[5] == 0);
		assertTrue(s.myValues[6] == 6);
		assertTrue(s.myValues[7] == 9);
		
		s. insert(3, 4);
		
		assertTrue(s.myCount == 9);
		assertTrue(s.myValues[0] == 3);
		assertTrue(s.myValues[1] == -7);
		assertTrue(s.myValues[2] == 2);
		assertTrue(s.myValues[3] == 4);
		assertTrue(s.myValues[4] == 42);
		assertTrue(s.myValues[5] == -11);
		assertTrue(s.myValues[6] == 0);
		assertTrue(s.myValues[7] == 6);
		assertTrue(s.myValues[8] == 9);
		
		s. insert(0, 0);
		
		assertTrue(s.myCount == 10);
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
	}
	
	public void Test_Contains() {
		IntSequence s = new IntSequence(10);
		s.add (3);
		s.add (-7);
		s.add (42);
		s.add (-11);
		s.add (0);
		s.add (6);
		s.add (9);
		
		assertTrue(s.contains(42));
		assertFalse(s.contains(1000));
		
		IntSequence s1 = new IntSequence(10);
		
		assertFalse(s1.contains(0));
		
		IntSequence s2 = new IntSequence(2);
		s2.add (3);
		s2.add (-7);
		s2.remove(0);
		s2.remove(0);
		assertFalse(s2.contains(3));
		assertFalse(s2.contains(-7));
	}

}

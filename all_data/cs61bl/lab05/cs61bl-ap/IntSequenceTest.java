import junit.framework.TestCase;
import java.util.Arrays;


public class IntSequenceTest extends TestCase {
	public void testContain(){
		IntSequence s = new IntSequence(30);
		s.add(42);
		s.add(32);
		s.add(4);
		s.add(67);
		s.add(56);
		
		assertFalse(s.contains(100));
		assertTrue(s.contains(32));
		assertFalse(s.contains(-100));
		
		IntSequence s2 = new IntSequence(30);
		s2.add(0);
		s2.add(0);
		s2.add(0);
		s2.add(0);
		assertFalse(s2.contains(100));
		assertFalse(s2.contains(-6));
		assertFalse(s2.contains(-100));
		assertTrue(s2.contains(0)); // True because we added zeroes to the Set
	}
	
	public void testInsert(){
		IntSequence s = new IntSequence(30);
		s.add(5);
		s.add(3);
		s.add(4);
		s.add(6);
		IntSequence s2 = new IntSequence(30);
		s2.add(5);
		s2.add(3);
		s2.add(56);
		s2.add(4);
		s2.add(6);
		
		s.insert(56, 2);
		//assertTrue(s2.equals(s));
		assertEquals(s.toString(),s2.toString());
	}
	public void testRemove() {
		IntSequence s = new IntSequence(30);
		s.add(42);
		s.add(32);
		s.add(4);
		s.add(56);

		IntSequence s2 = new IntSequence(30);
		s2.add(42);
		s2.add(32);
		s2.add(56);
		
		s.remove(2);
		
		assertEquals(s.toString(),s2.toString());
		
	}
	
	public void testToString() {
		String answer = "42 -32 2 3";
		IntSequence s3 = new IntSequence(30);
		s3.myValues[0] = 42;
		s3.myValues[1] = -32;
		s3.myValues[2] = 2;
		s3.myValues[3] = 3;
		s3.myCount = 4;
		assertEquals(answer, s3.toString());
		
		String answer2 = "";
		IntSequence s2 = new IntSequence(30);
		s2.myValues[0] = 0;
		s2.myCount = 0;
		assertEquals(answer2, s2.toString());

		String answer3 = "5";
		IntSequence s = new IntSequence(30);
		s.myValues[0] = 5;
		s.myCount = 1;
		assertEquals(answer3, s.toString());
	}
}

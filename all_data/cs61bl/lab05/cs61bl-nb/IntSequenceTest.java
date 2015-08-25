
import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testRemove() {
		IntSequence s = new IntSequence(20);
		s.myValues[0] = 3;
		s.myValues[1] = -7;
		s.myValues[2] = 42;
		s.myValues[3] = -11;
		s.myValues[4] = 0;
		s.myValues[5] = 6;
		s.myCount = 6;
		s.remove(2);
		IntSequence s2 = new IntSequence(20);
		s2.myValues[0] = 3;
		s2.myValues[1] = -7;
		s2.myValues[2] = -11;
		s2.myValues[3] = 0;
		s2.myValues[4] = 6;
		s2.myCount = 5;
		check(s, s2);
		s.remove(0);
		s.remove(4);
		IntSequence s3 = new IntSequence(20);
		s3.myValues[0] = -7;
		s3.myValues[1] = -11;
		s3.myValues[2] = 0;
		s3.myValues[3] = 6;
		s3.myCount = 4;
		check(s, s3);
	}
	
	public void testInsert() {
		IntSequence s = new IntSequence(20);
		s.myValues[0] = 3;
		s.myValues[1] = -7;
		s.myValues[2] = 42;
		s.myValues[3] = -11;
		s.myValues[4] = 0;
		s.myValues[5] = 6;
		s.myCount = 6;
		s.insert(100, 3);
		IntSequence s2 = new IntSequence(20);
		s2.myValues[0] = 3;
		s2.myValues[1] = -7;
		s2.myValues[2] = 42;
		s2.myValues[3] = 100;
		s2.myValues[4] = -11;
		s2.myValues[5] = 0;
		s2.myValues[6] = 6;
		s2.myCount = 7;
		check(s, s2);
		s.insert(50, 0);
		s.insert(60, 8);
		IntSequence s3 = new IntSequence(20);
		s3.myValues[0] = 50;
		s3.myValues[1] = 3;
		s3.myValues[2] = -7;
		s3.myValues[3] = 42;
		s3.myValues[4] = 100;
		s3.myValues[5] = -11;
		s3.myValues[6] = 0;
		s3.myValues[7] = 6;
		s3.myValues[8] = 60;
		s3.myCount = 9;
		check(s, s3);
	}
	
	public void testContain() {
		IntSequence s = new IntSequence(20);
		s.myValues[0] = 3;
		s.myValues[1] = -7;
		s.myValues[2] = 42;
		s.myValues[3] = -11;
		s.myValues[4] = 0;
		s.myValues[5] = 6;
		s.myCount = 6;
		assertTrue(s.contains(42) == true );
		assertTrue(s.contains(50) == false);	
	}
	
	public void testIsEmpty() {
		IntSequence s = new IntSequence(20);
		assertTrue(s.isEmpty() == true);
		s.add(50);
		assertTrue(s.isEmpty() == false);
		s.myValues[0] = 3;
		s.myCount ++;
		assertTrue(s.isEmpty() == false);
	}
	
	public void testSize() {
		IntSequence s = new IntSequence(20);
		s.add(50);
		assertTrue(s.size() == 1);
	}
	
	public void testElementAt() {
		IntSequence s = new IntSequence(20);
		s.add(50);
		assertTrue(s.elementAt(0) == 50);
	}
	
	
	public void testToString(){
		IntSequence s = new IntSequence(20);
		s.myValues[0] = 3;
		s.myValues[1] = -7;
		s.myValues[2] = 42;
		s.myValues[3] = -11;
		s.myCount = 4;
		assertTrue(s.toString().equals("3 -7 42 -11"));
	}
	
	
	
	private void check (IntSequence s1, IntSequence s2) {
		for (int k = 0; k < s1.myCount; k++) {
			assertTrue(s1.myValues[k] == s2.myValues[k]);
		}
		assertTrue(s1.myCount == s2.myCount);
	}
}

import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd(){
		
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.myValues[0] = 3;
		s.myValues[1] = 4;
		s.myValues[2] = 5;
		s.myCount = 3;
		s.add(6);
		ResizableIntSequence s2 = new ResizableIntSequence(4);
		s2.myValues[0] = 3;
		s2.myValues[1] = 4;
		s2.myValues[2] = 5;
		s2.myValues[3] = 6;
		s2.myCount = 4;
		check(s, s2);
		assertTrue(s.myCount==s.myValues.length);
	
	}
	
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(6);
		s.myValues[0] = 3;
		s.myValues[1] = -7;
		s.myValues[2] = 42;
		s.myValues[3] = -11;
		s.myValues[4] = 0;
		s.myValues[5] = 6;
		s.myCount = 6;
		s.insert(100, 3);
		ResizableIntSequence s2 = new ResizableIntSequence(7);
		s2.myValues[0] = 3;
		s2.myValues[1] = -7;
		s2.myValues[2] = 42;
		s2.myValues[3] = 100;
		s2.myValues[4] = -11;
		s2.myValues[5] = 0;
		s2.myValues[6] = 6;
		s2.myCount = 7;
		check(s, s2);
		assertTrue(s.myCount==s.myValues.length);
		s.insert(50, 0);
		s.insert(60, 8);
		ResizableIntSequence s3 = new ResizableIntSequence(9);
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
		assertTrue(s.myCount==s.myValues.length);
	}
	
	public void testRemove() {
		ResizableIntSequence s = new ResizableIntSequence(6);
		s.myValues[0] = 3;
		s.myValues[1] = -7;
		s.myValues[2] = 42;
		s.myValues[3] = -11;
		s.myValues[4] = 0;
		s.myValues[5] = 6;
		s.myCount = 6;
		s.remove(2);
		ResizableIntSequence s2 = new ResizableIntSequence(6);
		s2.myValues[0] = 3;
		s2.myValues[1] = -7;
		s2.myValues[2] = -11;
		s2.myValues[3] = 0;
		s2.myValues[4] = 6;
		s2.myCount = 5;
		check(s, s2);
		s.remove(0);
		s.remove(3);
		IntSequence s3 = new IntSequence(4);
		s3.myValues[0] = -7;
		s3.myValues[1] = -11;
		s3.myValues[2] = 0;
		s3.myCount = 3;
		check(s, s3);
		assertTrue(s.myValues.length == 4);
	}
	
	
	private void check (IntSequence s1, IntSequence s2) {
		for (int k = 0; k < s1.myCount; k++) {
			assertTrue(s1.myValues[k] == s2.myValues[k]);
		}
		assertTrue(s1.myCount == s2.myCount);

	}
}
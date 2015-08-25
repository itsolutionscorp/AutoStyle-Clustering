import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testInsert() {
		ResizableIntSequence r = new ResizableIntSequence(2);
		r.insert(0);
		assertFalse (s.isEmpty());
		assertTrue (s.member(0));
		assertFalse (s.member(1));

		s.insert(0);                // should have no effect
		assertFalse (s.isEmpty());
		assertTrue (s.member(0));
		assertFalse (s.member(1));
	}

	
	
	private void check (int[] array1, int[] array2) {
		assertTrue (array1.length == array2.length);
		for (int k = 0; k < array1.length; k++) {
			assertTrue (array1[k] == array2[k]);
}

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class SequenceTest {

	@Test
	public void testBasic() {
		Sequence<String> seq = new Sequence<String>(10);
		seq.add("G");
		seq.add("k");

		Sequence<Integer> seq1 = new Sequence<Integer>(10);
		seq1.add(1);
		seq1.add(2);
		Iterator<Integer> seqIterator = seq1.iterator();
		assertTrue (seqIterator.next() == 1);
		assertTrue(seqIterator.hasNext());
		assertTrue(seqIterator.next() == 2);
		assertFalse(seqIterator.hasNext());
	}

}

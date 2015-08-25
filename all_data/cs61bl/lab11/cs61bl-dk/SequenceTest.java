import junit.framework.TestCase;


public class SequenceTest extends TestCase {
	public void testDelete() {
		Sequence seq = new Sequence();
		seq.addToSequence(1);
		seq.addToSequence(2);
		seq.addToSequence(3);
		System.out.println(seq);
		seq.delete(1);
		System.out.println(seq);
		seq.delete(0);
		System.out.println(seq);
		seq.delete(0);
		System.out.println(seq);
	}
}

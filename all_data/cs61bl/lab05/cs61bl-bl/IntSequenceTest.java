import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class IntSequenceTest {
	@Test
	public void testBasic() {
		IntSequence n = new IntSequence(10);
		assertTrue(n.isEmpty() == true);
		assertTrue(n.size() == 0);
		assertTrue(n.elementAt(0) == 0);
		assertFalse(n.contains(1));

		n.add(1);
		assertTrue(n.isEmpty() == false);
		assertTrue(n.size() == 1);
		assertTrue(n.elementAt(0) == 1);
		System.out.println("'" + n.toString() + "'");
	}

	@Test
	public void testRemove() {
		IntSequence d = new IntSequence(5);
		d.add(1);
		d.add(2);
		d.add(3);
		System.out.println("'" + d.toString() + "'");

		assertTrue(d.contains(2));
		d.remove(1);
		assertTrue(d.size() == 2);
		assertTrue(d.elementAt(1) == 3);
		assertFalse(d.contains(2));
		System.out.println("'" + d.toString() + "'");
	}

	@Test
	public void testInsert() {
		IntSequence i = new IntSequence(5);
		i.add(1);
		i.add(2);
		i.add(4);
		i.insert(3, 2);
		assertTrue(i.size() == 4);
		assertTrue(i.elementAt(2) == 3);
		assertTrue(i.elementAt(3) == 4);
		assertTrue(i.contains(4));
		System.out.println("'" + i.toString() + "'");
	}

	public static void main(String[] args) {
        jh61b.junit.textui.runClasses(IntSequenceTest.class);
    }
}
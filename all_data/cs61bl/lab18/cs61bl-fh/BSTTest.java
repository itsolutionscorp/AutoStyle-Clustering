import java.util.LinkedList;

import org.junit.Test;

public class BSTTest {

	@Test
	public void testConstructor() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 1; i <= 100; i++) {
			list.add(i);
		}
		BST t = new BST(list);

		// assertTrue(t.myRoot.myItem.equals(new Integer(5)));
		// assertTrue(t.myRoot.myLeft.myItem.equals(new Integer(3)));
		// assertTrue(t.myRoot.myRight.myItem.equals(new Integer(3)));
		t.print();
	}
}

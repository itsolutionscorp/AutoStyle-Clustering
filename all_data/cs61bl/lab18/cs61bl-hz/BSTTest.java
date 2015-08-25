import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class BSTTest {

	@Test
	public void testlinkedListToTree() {
		LinkedList l = new LinkedList();
		for(int i = 0; i < 19; i++) {
			l.add(i);
		}
		BST b = new BST(l);
		b.print();
	}

}

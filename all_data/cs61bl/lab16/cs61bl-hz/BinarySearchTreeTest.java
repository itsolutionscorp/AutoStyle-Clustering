import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void test() {
		BinarySearchTree b = new BinarySearchTree();
		b.add("c");
		b.add("a");
		b.add("b");
		b.add("e");
		b.add("d");
		b.print(); // prints correctly!!!
	}
	
	@Test
	public void testConstructor() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("A");
		a.add("B");
		a.add("C");
		a.add("D");
		a.add("E");
		a.add("F");
		ArrayList<String> b = new ArrayList<String>();
		b.add("B");
		b.add("A");
		b.add("E");
		b.add("D");
		b.add("F");
		b.add("C");
		BinaryTree c = new BinaryTree(a, b);
		assertTrue(c.myRoot.myItem.equals("A"));
		assertTrue(c.myRoot.myLeft.myItem.equals("B"));
		assertTrue(c.myRoot.myRight.myItem.equals("C"));
		assertTrue(c.myRoot.myRight.myLeft.myItem.equals("D"));
		assertTrue(c.myRoot.myRight.myLeft.myLeft.myItem.equals("E"));
		assertTrue(c.myRoot.myRight.myLeft.myRight.myItem.equals("F"));
	}

}

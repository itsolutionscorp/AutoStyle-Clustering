import junit.framework.TestCase;

import org.junit.Test;

public class BinaryTreeTest extends TestCase {

	@Test
	public void test() {
		BinaryTree t;
		t = new BinaryTree();
		assertEquals(0, t.height());
		t.fillSampleTree1();
		assertEquals(2, t.height());
		t.fillSampleTree2();
		assertEquals(4, t.height());
		t.fillSampleTree3();
		assertEquals(4, t.height());
	}

	@Test
	public void testBalanced() {
		BinaryTree t;
		t = new BinaryTree();
		assertTrue(t.isCompletelyBalanced());
		t.fillSampleTree1();
		assertTrue(t.isCompletelyBalanced());
		t.fillSampleTree2();
		assertFalse(t.isCompletelyBalanced());
		t.fillSampleTree3();
		assertFalse(t.isCompletelyBalanced());
		t.fillSampleTree4();
		t.isCompletelyBalanced();
	}

}

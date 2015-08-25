import org.junit.Test;

public class AVLTreeTest {

	// @Test
	// public void test() {
	// AVLTree t1 = new AVLTree(1);
	// System.out.println(t1);
	// assertTrue(t1.contains(1));
	// t1.insert(2);
	// System.out.println(t1);
	// assertTrue(t1.contains(2));
	//
	// // Should cause a rotation to occur.
	// t1.insert(3);
	// System.out.println(t1);
	// assertTrue(t1.contains(3));
	// System.out.println(t1);
	// System.out.println(t1.isAlmostBalanced());
	// t1.insert(4);
	// assertTrue(t1.contains(4));
	// assertTrue(!t1.contains(5));
	//
	// // Should cause another rotation.
	// t1.insert(5);
	// System.out.println(t1);
	// System.out.println(t1.isAlmostBalanced());
	//
	// AVLTree t2 = new AVLTree(2);
	// t2.insert(1);
	// t2.insert(4);
	// t2.insert(3);
	// t2.insert(5);
	//
	// // Should cause a rotation to occur.
	// t2.insert(6);
	// System.out.println(t2);
	// System.out.println(t2.isAlmostBalanced());
	// }
	//
	// @Test
	// public void testLeft() {
	// AVLTree t1 = new AVLTree(1);
	// t1.insert(3);
	// t1.insert(2);
	// System.out.println(t1);
	//
	// AVLTree t2 = new AVLTree(1);
	// t2.insert(2);
	// t2.insert(3);
	// System.out.println(t2);
	// }
	//
	// @Test
	// public void testLeft1() {
	// AVLTree t1 = new AVLTree(3);
	// t1.insert(3);
	// t1.insert(2);
	// System.out.println(t1);
	//
	// AVLTree t2 = new AVLTree(3);
	// t2.insert(2);
	// t2.insert(1);
	// System.out.println(t2);
	// }

	@Test
	public void testBig() {
		AVLTree t1 = new AVLTree(10);
		for (int i = 0; i < 100; i++) {
			t1.insert(i);
		}
		System.out.println(t1);
	}

}

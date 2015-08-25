import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class AVLTreeTest {

	@Test
	public void testInsert1Rotation() {
		AVLTree tree = new AVLTree(6);
		tree.insert(10);
		tree.insert(2);
		tree.insert(9);
		tree.insert(11);
		tree.insert(1);
		tree.insert(3);
		tree.insert(4);
		
		//requires rotation
		tree.insert(5);
		
		System.out.println(tree);
		assertTrue(tree.isAlmostBalanced());
		
		
	}
	
	@Test
	public void testInsert2Rotation() {
		AVLTree t2 = new AVLTree(2);
		t2.insert(1);
		t2.insert(4);
		t2.insert(3);
		t2.insert(5);
		
		//System.out.println(t2);

		// Should cause a rotation to occur.
		t2.insert(6);
		System.out.println(t2);
		assertTrue(t2.isAlmostBalanced());;
		
		
	}

}


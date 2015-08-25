import junit.framework.TestCase;


public class BinarySearchTreeTest extends TestCase {
	
	
	public void testComparable() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.add(5);
		b.add(0);
		b.add(7);
		b.add(6);
		b.add(8);
		b.add(9);
		b.add(-1);
		b.add(2);
		b.add(1);
		b.add(4);
		
		b.remove(b.myRoot, 6);
		assertTrue(b.contains(6) == false);
		assertTrue(b.contains(8) == true);
		b.remove(b.myRoot, 8);
		assertTrue(b.contains(8) == false);
		
		
		//assertEquals(b.myRoot.mySize,10);
		//assertEquals(b.myRoot.myLeft.mySize,5);
		//assertEquals(b.myRoot.myRight.mySize,4);
		
		//assertEquals(b.kthLargest(0),9);
		//assertEquals(b.kthLargest(2),7);
		//assertEquals(b.kthLargest(3),6);
		//assertEquals(b.kthLargest(3),6);
		//assertEquals(b.kthLargest(4),5);
		//assertEquals(b.kthLargest(8),-1);
		
		
	}
}

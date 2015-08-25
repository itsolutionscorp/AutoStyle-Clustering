import junit.framework.TestCase;


public class BinaryTreeTest extends TestCase {
	public void  testHeight(){
		BinaryTree empty = new BinaryTree();
		assertTrue(empty.height()==0);
		assertTrue(empty.isCompletelyBalanced());
		
		 
		BinaryTree test1 = new BinaryTree();
		test1.fillSampleTree1();
		assertTrue(test1.height()==2);
		assertTrue(test1.isCompletelyBalanced());
		test1.fillSampleTree2();
		assertFalse(test1.isCompletelyBalanced());
		assertTrue(test1.height()==4);
		test1.fillSampleTree3();
		assertFalse(test1.isCompletelyBalanced());
		assertTrue(test1.height()==4);
		test1.fillSampleTreeBalance();
		assertTrue(test1.isCompletelyBalanced());
		assertTrue(test1.height()==4);
		test1.fillSampleTreeHeight3();
		assertTrue(test1.isCompletelyBalanced());
		assertTrue(test1.height()==3);
	}
	

}

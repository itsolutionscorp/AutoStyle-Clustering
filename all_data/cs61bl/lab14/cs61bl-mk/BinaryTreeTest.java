
import junit.framework.TestCase;


public class BinaryTreeTest extends TestCase {

	public void testHeight(){
		BinaryTree b = new BinaryTree();
		assertTrue(b.height() == 0);
		
		b.fillSampleTree4();
		assertTrue(b.height() == 3);
		
		b.fillSampleTree1();
		assertTrue(b.height() == 2);
		
		b.fillSampleTree2();
		assertTrue(b.height() == 4);
		
		b.fillSampleTree3();
		assertTrue(b.height() == 4);
		
	}
	
	public void testBalanced(){
		BinaryTree b = new BinaryTree();
		assertTrue(b.isCompletelyBalanced());
		
		b.fillSampleTree4();
		assertTrue(b.isCompletelyBalanced());
		
		b.fillSampleTree1();
		assertTrue(b.isCompletelyBalanced());
		
		b.fillSampleTree2();
		assertFalse(b.isCompletelyBalanced());
		
		b.fillSampleTree3();
		assertFalse(b.isCompletelyBalanced());
		
	}
	
}

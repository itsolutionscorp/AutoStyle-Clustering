
import junit.framework.TestCase;


public class BinaryTreeTest extends TestCase {
	public void testHeight() {
		BinaryTree t = new BinaryTree();
       
        t.fillSampleTree1();
        assertEquals(2, t.height());
        t.fillSampleTree2();
        assertEquals(4, t.height());
        t.fillSampleTree3();
        assertEquals(4, t.height());
    
	}
	
	
	public void testIsCompletelyBalanced() {
		BinaryTree t = new BinaryTree();
       
        t.fillSampleTree1();
        assertEquals(true, t.isCompletelyBalanced());
        t.fillSampleTree2();
        assertEquals(false, t.isCompletelyBalanced());
        t.fillSampleTree3();
        assertEquals(false, t.isCompletelyBalanced());
        t.fillSampleTree4();
        assertEquals(true, t.isCompletelyBalanced());
        
      
    
	}
}

import java.util.ArrayList;
import java.util.Collections;

public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }

    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }

    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    public void fillSampleTree3(){
    	myRoot = new TreeNode("A", 
    					new TreeNode("B"),
    					new TreeNode("C",
    							new TreeNode("D", 
    									new TreeNode("E"), 
    									new TreeNode("F")),
								null));
    }
    public void fillSampleTree4(){
    	myRoot  = new TreeNode("A", 
				new TreeNode("B",
						new TreeNode("C", new TreeNode("D"), new TreeNode("E")),
						new TreeNode("J", new TreeNode("K"), new TreeNode("L"))),
				new TreeNode("m",
						new TreeNode("n", new TreeNode("O"), new TreeNode("P")),
						new TreeNode("Q", new TreeNode("R"), new TreeNode("S"))));
    }
    
    public void fillSampleTree5(){
    	myRoot =  new TreeNode("m",
				new TreeNode("n", new TreeNode("O"), new TreeNode("P")),
				new TreeNode("Q", new TreeNode("R"), new TreeNode("S")));
    }
    
    public void fillSampleTree6(){
    	myRoot = new TreeNode("B");
    }

    
    public int height(){
    	if (myRoot != null) {
    		return myRoot.height();
    	}
    	return 0;
    }
    
    public boolean isCompletelyBalanced(){
    	if(myRoot!=null)
    		return myRoot.isCompletelyBalanced();
    	return true;
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        print(t, "the empty tree");
        t.fillSampleTree1();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 1");
        t.fillSampleTree2();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 2");
        t.fillSampleTree3();
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 3");
        
        
        
        
        
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    private static class TreeNode {

        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;

        public TreeNode(Object obj) {
            myItem = obj;
            myLeft = myRight = null;
        }

        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            myItem = obj;
            myLeft = left;
            myRight = right;
        }

        private void printPreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }

        private void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
        }
        
        private int height(){
        	if (myRight == null && myLeft != null)
        		return 1 + myLeft.height();
        	else if (myRight != null && myLeft == null) 
        		return 1 + myRight.height();
        	else if (myRight == null && myLeft == null)
        		return 1;
        	else
        		return 1 + Math.max(myRight.height(), myLeft.height());
        }
        private boolean isCompletelyBalanced(){
        	if(myLeft==null && myRight==null){
        		return true;
        	}else if(myLeft!=null && myRight==null){
        		return false;
        	}else if(myLeft==null && myRight!=null){
        		return false;
        	}else{
        		return myLeft.height() == myRight.height() && myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        	}
        }
    }
}

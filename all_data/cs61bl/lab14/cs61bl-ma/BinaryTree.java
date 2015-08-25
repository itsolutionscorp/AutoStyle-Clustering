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
    
    public void fillSampleTree3() {
    	myRoot = new TreeNode("a", 
    			new TreeNode("b"), new TreeNode("c",
    					new TreeNode("d", 
    							new TreeNode("e"), new TreeNode("f")), null));	
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", 
    			new TreeNode("b"), new TreeNode("c",
    					new TreeNode("d", 
    							new TreeNode("e"), new TreeNode("f", new TreeNode ("g", null, new TreeNode ("lol")),null)), null));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("e", new TreeNode("g"), new TreeNode("h")), new TreeNode("f"
    			, new TreeNode("i"), new TreeNode("j"))), new TreeNode("c"));
    }
    
    public void fillSampleTree6() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("e", new TreeNode("g"), new TreeNode("h")), new TreeNode("f"
    			, new TreeNode("i"), new TreeNode("j"))), new TreeNode("c", new TreeNode("1", new TreeNode("3"), new TreeNode("4"))
    			, new TreeNode("2", new TreeNode("5"), new TreeNode("6"))));
    }
    
    public int height() {
    	if (myRoot != null) {
    		return myRoot.height(); 
    	}
    	return 0; 
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot != null) {
    		return myRoot.isCompletelyBalanced();
    	}
    	return true;
    }

    public static void main(String[] args) {
        BinaryTree t;
        
        t = new BinaryTree();
        System.out.println(t.isCompletelyBalanced());
        print(t, "the empty tree");
        
        t.fillSampleTree1();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 1");
        
        t.fillSampleTree2();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 2");
        
        t.fillSampleTree3();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 3");
        
        t.fillSampleTree4();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 4");
        
        t.fillSampleTree5();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 5");
        
        t.fillSampleTree6();
        System.out.println(t.isCompletelyBalanced());
        print(t, "sample tree 6"); 
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
        
        private int height() {
        	if (myLeft == null && myRight == null) {
        		return 1; 
        	} else {
        		int left = 0; 
        		int right = 0; 
        		if (myLeft != null) {
        			left = myLeft.height();
        		}
        		if (myRight != null) {
        			right = myRight.height(); 
        		}
        		return Math.max(left, right) + 1; 
        	}
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true; 
        	} 
        	if (myLeft == null) {
        		return false;
        	}
        	if (myRight == null) {
        		return false;
        	}
        	else {
        		int leftHeight = myLeft.height();
        		int rightHeight = myRight.height(); 
        		boolean left = myLeft.isCompletelyBalanced();
        		boolean right = myRight.isCompletelyBalanced(); 
        		return ((left && right) && (leftHeight == rightHeight));
        	}
        }
    }
}

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
    
    // Returns the height of the tree
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	} else {
    		return myRoot.height();
    	}
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	} else {
    		return myRoot.isCompletelyBalanced();
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
    	TreeNode def = new TreeNode("d", new TreeNode("e"), new TreeNode("f"));
    	TreeNode cdef = new TreeNode("c", def, null);
    	TreeNode abcdef = new TreeNode("a", new TreeNode("b"), cdef);
    	myRoot = abcdef;
    
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c"), new TreeNode("d")), new TreeNode("e", new TreeNode("f"), new TreeNode("g")));
    }
    
    public void fillSampleTree5() {
    	myRoot = new TreeNode("a");
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");
        t.fillSampleTree3();
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
        
        private int height() {
    	    if (myLeft == null && myRight == null) {
    	    	return 1;
    	    } else {
    	        int bestSoFar = 0;
    	        if (myLeft != null) {
    	        	bestSoFar = Math.max(1 + myLeft.height(), bestSoFar);
    	        }
    	        if (myRight != null) {
    	        	bestSoFar = Math.max(1 + myRight.height(), bestSoFar);
    	        }
    	        return bestSoFar;
    	    }
    	}
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if (myLeft == null || myRight == null) {
        		return false;
			} else {
				boolean balanced = true;
				if (!myLeft.isCompletelyBalanced()
						|| !myRight.isCompletelyBalanced()) {
					balanced = false;
				}
				return balanced;
			}
        }
    }
}

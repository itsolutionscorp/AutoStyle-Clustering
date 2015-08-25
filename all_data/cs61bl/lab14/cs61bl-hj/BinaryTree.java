//import AmoebaFamily.Amoeba;

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
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
    			 new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree4() {
    	myRoot = new TreeNode("1", new TreeNode("2", new TreeNode("4"), new TreeNode("4")),
    			new TreeNode("2", new TreeNode("4"), new TreeNode("4")));
    }
    
    public int height() {
    	int height = 0;
    	if (myRoot != null) {
    		height = myRoot.height();
    	}
    	return height;
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	}
    	return myRoot.isCompletelyBalanced();
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");
        
        BinaryTree t1;
        t1 = new BinaryTree();
        t1.fillSampleTree3();
        int height = t1.height();
        System.out.println(height);
    	
        BinaryTree t2;
        t2 = new BinaryTree();
        System.out.println(t2.isCompletelyBalanced());
        t2.fillSampleTree1();
        System.out.println(t2.isCompletelyBalanced());
        t2.fillSampleTree2();
        System.out.println(t2.isCompletelyBalanced());
        t2.fillSampleTree3();
        System.out.println(t2.isCompletelyBalanced());
        t2.fillSampleTree4();
        System.out.println(t2.isCompletelyBalanced());
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
        	int bestSoFar = 1;
            if (myLeft == null && myRight == null) {
            	return 1;
            } else {
                if (myLeft != null) {
                	int current = 1;
                	current += myLeft.height();
                	bestSoFar = Math.max(current, bestSoFar);
                }
                if (myRight != null) {
                	int current = 1;
                	current += myRight.height();
                	bestSoFar = Math.max(current, bestSoFar);
                }
            }
            return bestSoFar;
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) {
        		return true;
        	} else if ((myLeft == null && myRight != null) || (myLeft != null && myRight == null)) {
        		return false;
        	}
        	return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
        }
    }
}

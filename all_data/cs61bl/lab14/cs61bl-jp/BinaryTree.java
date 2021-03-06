public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public int height() {
    	if (myRoot == null) {
    		return 0;
    	} else if (myRoot.myLeft == null && myRoot.myRight == null) {
    		return 1;
    	} else if (myRoot.myLeft == null) {
    		BinaryTree right = new BinaryTree(myRoot.myRight);
    		return right.height() + 1;
    	} else if (myRoot.myRight == null) {
    		BinaryTree left = new BinaryTree(myRoot.myLeft);
    		return left.height() + 1;
    	} else {
    		BinaryTree left = new BinaryTree(myRoot.myLeft);
    		BinaryTree right = new BinaryTree(myRoot.myRight);
    		return left.height() + right.height();
    	}
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	} else if (myRoot.myLeft == null && myRoot.myRight == null) {
    		return true;
    	} else {
    		if (myRoot.myLeft != null && myRoot.myRight == null) {
    			return false;
    		} else if (myRoot.myLeft == null && myRoot.myRight != null) {
    			return false;
    		} else {
        		BinaryTree left = new BinaryTree(myRoot.myLeft);
        		BinaryTree right = new BinaryTree(myRoot.myRight);
        		if (left.height() == right.height()) {
        			return true;
        		} else {
        			return false;
        		}
    		}
    	}
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
    	myRoot = new TreeNode("a", new TreeNode("b", null, null), new TreeNode("c", 
    			new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
 

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced());
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
    }
}

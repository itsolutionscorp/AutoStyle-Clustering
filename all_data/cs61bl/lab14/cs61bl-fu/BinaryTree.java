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
    
    public void fillSampleTree15() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c",
    			new TreeNode("d", null, null), new TreeNode("e", null, null)),
    			new TreeNode("f", new TreeNode("g", null, null), new TreeNode("h", null, null))),
    			new TreeNode("i", new TreeNode("j", new TreeNode("k", null, null), new TreeNode("l", null, null)),
    			new TreeNode("m", new TreeNode("n", null, null), new TreeNode("o", null, null))));
    	
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced() + " should be false");
        BinaryTree t1 = new BinaryTree();
        System.out.println(t1.isCompletelyBalanced() + " should be true");
        t.fillSampleTree15();
        System.out.println(t.isCompletelyBalanced() + " should be true");
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
    
    public int height () {
    	if (myRoot == null) return 0;
    	return myRoot.height();
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) return true;
    	else return myRoot.isCompletelyBalanced();
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
        
        private int height () {
        	if (myLeft == null && myRight == null) return 1;
        	int left = 0;
        	int right = 0;
        	if (myLeft != null) left = myLeft.height();
        	if (myRight != null) right = myRight.height();
        	return 1 + Math.max(left,right);
        }
        
        private boolean isCompletelyBalanced() {
        	if (myLeft == null && myRight == null) return true;
        	else if (myLeft == null || myRight == null) return false;
        	else return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        }
    }
}

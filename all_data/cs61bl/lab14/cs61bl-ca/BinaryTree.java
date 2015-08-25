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
        }
    	if  (myRoot.myLeft == null && myRoot.myRight == null) {
    		return 1;
    	}
    	if (myRoot.myLeft != null && myRoot.myRight == null) {
    		return 1 + myRoot.myLeft.heightOfBinaryTree(myRoot.myLeft);
    	}
    	if (myRoot.myLeft == null && myRoot.myRight != null) {
    		return 1 + myRoot.myRight.heightOfBinaryTree(myRoot.myRight);
    	}
    	
        else {
            return 1 +Math.max(myRoot.myLeft.heightOfBinaryTree(myRoot.myLeft), myRoot.myRight.heightOfBinaryTree(myRoot.myRight));
        }
    	
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) return true;
    	if (myRoot.myLeft == null && myRoot.myRight == null) return true;
    	if (myRoot.myLeft == null || myRoot.myRight == null) return false;
    	if (myRoot.myLeft.heightOfBinaryTree(myRoot.myLeft) == myRoot.myRight.heightOfBinaryTree(myRoot.myRight)) {
    		return true;
    	} else {
    		return false;
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
    
    public void fillSampleTree0() {
    	myRoot = new TreeNode("a", null, null);
    }
    

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    
    public void fillSampleTree3() {
    	myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    public void fillSampleTree4() {
    	myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null), 
    							   new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    
    public void fillSampleTree5() {
        myRoot = new TreeNode("a", new TreeNode("b"), null);
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
        System.out.println("height : " + t.height());
        System.out.println("isbalanced : " + t.isCompletelyBalanced());
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
        
        public int heightOfBinaryTree(TreeNode tree) {
            if (tree == null) {
                return 0;
            }
            else {
                return 1 +
                Math.max(heightOfBinaryTree(tree.myLeft),
                    heightOfBinaryTree(tree.myRight));
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

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
    	myRoot = new TreeNode("a",new TreeNode("b"), new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    	}
    
    public int height(){
    	if(myRoot !=null){
			return myRoot.height();
		}
		return 0;
    }
    
    public boolean isCompletelyBalanced(){
    	if(myRoot !=null){
    		return myRoot.isCompletelyBalance();
    	}
    	return true;
    }
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println("The height of fill empty is"+t.height());
        System.out.println("balanced tree? "+t.isCompletelyBalanced());
        
        
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println("The height of fill sample1 is"+t.height());
        System.out.println("balanced tree? "+t.isCompletelyBalanced());
        
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println("The height of fill sample2 is"+t.height());
        System.out.println("balanced tree? "+t.isCompletelyBalanced());
        
        t.fillSampleTree3();
        print(t,"sample tree 3");
        System.out.println("The height of fill sample3 is"+t.height());
        System.out.println("balanced tree? "+t.isCompletelyBalanced());
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

        public boolean isCompletelyBalance() {
			if(myRight ==null && myLeft ==null){
				return true;
			}else if(myRight !=null && myLeft !=null){
				if(myRight.height()==myLeft.height()){
					return true;
				}
			}
			return false;
		}

		public int height() {
			int bestSoFar = 0;
		    if (myRight ==null && myLeft ==null) {
		    	bestSoFar ++;
		    }else if(myRight ==null && myLeft !=null){
		    	bestSoFar = bestSoFar + myLeft.height()+1;
		    }else if(myRight !=null && myRight ==null){
		    	bestSoFar = bestSoFar+ myRight.height()+1;
		    }else{
		    	bestSoFar = Math.max(myRight.height(), myLeft.height())+1;
		    }
		    return bestSoFar;
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

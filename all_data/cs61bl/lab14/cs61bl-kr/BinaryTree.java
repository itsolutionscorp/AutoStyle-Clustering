
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
    public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}
    
    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }
    
    public boolean isCompletelyBalanced() {
    	if (myRoot == null) {
    		return true;
    	} else if (myRoot != null) {
    		return myRoot.isCompletelyBalanced();
    	} return false;
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
    public void filEvenTree() {
    	myRoot = new TreeNode("n", 
    			new TreeNode("i", new TreeNode("c", new TreeNode("o"), new TreeNode("l")),
    			new TreeNode("e", new TreeNode("x"), new TreeNode("x"))),
    			new TreeNode("x", new TreeNode("n", new TreeNode("g"), new TreeNode("u")),
    	    	new TreeNode("y", new TreeNode("e"), new TreeNode("n"))));
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        t.filEvenTree();
        print(t, "fillEvenTree");
        System.out.println(t.isCompletelyBalanced());
        System.out.println(t.height());
//        t.fillSampleTree1();
//        print(t, "sample tree 1");
//        System.out.println(t.isCompletelyBalanced());      
//        t.height();
//        System.out.println(t.height());
//        t.fillSampleTree2();
//        System.out.println(t.isCompletelyBalanced());
//        print(t, "sample tree 2");
//        t.height();
//        System.out.println(t.height());
//        t.fillSampleTree3();
//        System.out.println(t.isCompletelyBalanced());
//        print(t, "sample tree 3");
//        t.height();
//        System.out.println(t.height());
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

        public boolean isCompletelyBalanced() {
			// TODO Auto-generated method stub
        	if (myLeft != null && myRight != null) {
        		if (myLeft.height() == myRight.height()) {
        			if (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced()) {
        				return true;
        			}
        		}
        	} else if (myLeft == null && myRight == null) {
        		return true;
        	} else {
        		return false;
        	}
			return false;
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
            myItem = obj;
            myLeft = left;
            myRight = right;
        }
        int bestSoFar = 1;
        private int height() {
            if (myLeft == null && myRight == null) {
            	return bestSoFar;
            }
            
            
            if (myLeft != null){
            bestSoFar = Math.max(myLeft.height()+1, bestSoFar);
            }
           
            if (myRight != null) {
            bestSoFar = Math.max(myRight.height()+1, bestSoFar);
            }
            
//            } else {
//            		if (myLeft != null) {
//                    bestSoFar =1+Math.max(myLeft.height(), bestSoFar);
//                    if(myRight != null) {
//                    bestSoFar = 1 +Math.max(myRight.height(), bestSoFar);
//                    }
//                    return bestSoFar;
//                }
//            	}
                
//                else if (myRight != null) {
//                    bestSoFar =1+Math.max(myRight.height(), bestSoFar);
//                    if (myLeft != null) {
//                    bestSoFar = Math.max(myLeft.height(), bestSoFar);
////                    bestSoFar--;
//                    }
//                return bestSoFar;
//                   
//                }                          
                
			return bestSoFar;
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

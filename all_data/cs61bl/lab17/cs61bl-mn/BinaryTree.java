import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

    public TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(List<T> preorder, List<T> inorder) {
    	if (preorder.size() == 0 && inorder.size() == 0 ) {
    		myRoot = null;
    		return;
    	}
    	if (inorder.size() == 1 && preorder.size() == 1) {
    		myRoot = new TreeNode(inorder.get(0));
    		return;
    	}
    	T root = preorder.get(0);
    	myRoot = new TreeNode(root);
    	int rootIndex = inorder.indexOf(root);
    	//inorder
    	List<T> leftInorder = inorder.subList(0, rootIndex);
    	List<T> rightInorder =  inorder.subList(rootIndex+1, inorder.size());
    	//preorder
    	List<T> leftPreorder = preorder.subList(1, leftInorder.size()+1);
    	List<T> rightPreorder = preorder.subList(1+leftPreorder.size(), preorder.size());

    	
    	BinaryTree<T> leftTree = new BinaryTree<T>(leftPreorder, leftInorder);
    	BinaryTree<T> rightTree = new BinaryTree<T>(rightPreorder, rightInorder);
    	
    	myRoot.myLeft = leftTree.myRoot;
    	myRoot.myRight = rightTree.myRoot;
    
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

    public static BinaryTree<String> fillSampleTree1() {
        BinaryTree<String> t = new BinaryTree<String>();
        t.myRoot = t.new TreeNode("a", t.new TreeNode("b"), t.new TreeNode("c"));
        return t;
    }

    public static BinaryTree<String> fillSampleTree2() {
        BinaryTree<String> t = new BinaryTree<String>();
        t.myRoot = t.new TreeNode("a", t.new TreeNode("b", t.new TreeNode("d", t.new TreeNode("e"), t.new TreeNode("f")), null), t.new TreeNode("c"));
        return t;
    }

    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");
        
        ArrayList<String> preorder = new ArrayList<String>();
        preorder.add("A");
        preorder.add("B");
        preorder.add("C");
        preorder.add("D");
        preorder.add("E");
        preorder.add("F");
        
        ArrayList<String> inorder = new ArrayList<String>();
        inorder.add("B");
        inorder.add("A");
        inorder.add("E");
        inorder.add("D"); 
        inorder.add("F");
        inorder.add("C");
        
        BinaryTree<String> tree = new BinaryTree<String> (preorder, inorder);
        tree.printInorder();
        tree.printPreorder();
        
        BinaryTree<Integer> i = new BinaryTree<Integer>();
        i.myRoot = i.new TreeNode(5, i.new TreeNode(3, i.new TreeNode(2, i.new TreeNode(1), null), i.new TreeNode(4)), i.new TreeNode(6));
        System.out.println(i.myRoot.kthLargest(6));
    }

    protected static void print(BinaryTree<?> t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    // Method for the BinaryTree class
    public Iterator<T> iterator(){
        return new InorderIterator();
    }

    // Inner class inside of the BinaryTree class.
    // Also, it uses java.util.Iterator and java.util.Stack.
    private class InorderIterator implements Iterator<T> {
        private Stack<TreeNode> nodeStack;
        private TreeNode currentNode;

        public InorderIterator() {
            nodeStack = new Stack<TreeNode>();
            currentNode = myRoot;
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        public T next() {
            TreeNode nextNode = null;

            // find leftmost node with no left child
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.myLeft;
            }

            // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                assert nextNode != null;    // since nodeStack was not empty before the pop
                currentNode = nextNode.myRight;
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.myItem; 
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public class TreeNode {

        public T myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        public int size;
        
        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
            size = 1;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
            size = size();
        }
        
        public int size() {
        	if (myLeft == null && myRight == null) {
        		return 1;
        	} else if (myLeft == null) {
        		return 1 + myRight.size();
        	} else if (myRight == null) {
        		return 1 + myLeft.size();
        	} else {
        		return 1 + myLeft.size() + myRight.size();
        	}
        }
        
        public Comparable kthLargest (int k) {
        	int myRightSize;
        	if (myRight == null) {
        		myRightSize = 0;	
        	} else {
        		myRightSize = myRight.size();
        	}
        	if (k>this.size()-1 || k<0) {
        		return null;
        	}
    		if (k==myRightSize) {
    			return (Comparable) myItem;   		
    		} else if (k<myRightSize) {
    			return myRight.kthLargest(k);
    		} else {
    			return myLeft.kthLargest(k-(myRightSize +1));
    		}
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
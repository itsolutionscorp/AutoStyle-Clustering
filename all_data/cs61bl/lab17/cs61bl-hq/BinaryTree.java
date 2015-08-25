import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;
	public int depth;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList<T> a, ArrayList<T> b) {
    	myRoot = listHelper(a, b, 0, a.size(), 0, b.size());
    }
    
    private TreeNode listHelper(ArrayList<T> a, ArrayList<T> b, int startPreorder, int endPreorder, int startInorder, int endInorder) {
    	if (startPreorder >= endPreorder || startInorder >= endInorder) {
    		return null;
    	}
    	TreeNode root = new TreeNode(a.get(startPreorder));
    	int count = startInorder;
    	for (; count < endInorder; count++) {
    		if (b.get(count) == a.get(startPreorder)) {
    			break;
    		}
    	}
    	root.myLeft = listHelper(a, b, startPreorder + 1, startPreorder + count + 1 - startInorder, startInorder, count);
    	root.myRight = listHelper(a, b, startPreorder + 1 + count - startInorder, endPreorder, count + 1, endInorder);
    	return root;
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
        
    	System.out.println();
    	
		ArrayList<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("B");
		list2.add("A");
		list2.add("E");
		list2.add("D");
		list2.add("F");
		list2.add("C");
		BinaryTree<String> n = new BinaryTree<String>(list, list2);
		print(n, "list constructed");
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

    protected class TreeNode {

        public T myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        public int nodeSize;
//        public TreeNode succParent;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
        }
        
        public int size() {
        	if (myItem != null) {
        		nodeSize = 1;
        	}
        	if (myLeft != null && myRight == null) {
        		nodeSize = 2;
        	}
        	if (myRight != null && myLeft == null) {
        		nodeSize = 2;
        	}
        	if (myLeft != null && myRight != null) {
        		nodeSize = 3;
        	}
        	return nodeSize;
        }
        
    	private int branchSize(TreeNode t, int subExtra) {
    		if (t == null) {
    			return 0;
    		} else {
    			return t.size() + branchSize(t.myLeft, 1) + branchSize(t.myRight, 1) - subExtra;
    		}
    	}
        
        public Comparable kthLargest (int k, TreeNode t) {
        	if (k == branchSize(t.myRight, 0)) {
    			return (Comparable) t.myItem;
    		} else if (k < branchSize(t.myRight, 0)) {
    			return kthLargest(k, t.myRight);
    		} else {
    			return kthLargest(k - branchSize(t.myRight, 0) - 1, t.myLeft);
    		}
        }
        
        public void printPreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }

        public void printInorder() {
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
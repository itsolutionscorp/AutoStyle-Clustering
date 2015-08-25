import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	public boolean contains (T key) {
		if (myRoot == null) {
			return false;
		} else if (myRoot.myItem.equals(key)) {
			return true;
		} else if (myRoot.myItem.compareTo(key) > 0) {
			return this.contains(myRoot.myLeft, key);
		} else {
			return this.contains(myRoot.myRight, key);
		}
	}
	
	public void add(T key) {
		if (!this.contains(key)) {
			super.add(key);
			}
		}
	
	public static void main (String[] args) {
		BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>();
		myTree.add(10);
		myTree.add(9);
		myTree.add(11);
		myTree.add(8);
		myTree.add(9);
		myTree.add(12);
		System.out.println(myTree.contains(9));
		System.out.println(myTree.contains(2));
		System.out.println("In Preorder:");
		myTree.printPreorder();
		System.out.println("In order:");
		myTree.printInorder();
		
	}
	public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

	    protected TreeNode myRoot;

	    public BinaryTree() {
	        myRoot = null;
	    }

	    public BinaryTree(TreeNode t) {
	        myRoot = t;
	    }
	   
	    
	    public Comparable kthLargest (int k){
	    	TreeNode pointer = myRoot;
	    	while (pointer.myLeft.size != k && pointer.myRight.size != k) {
	    		if (pointer.myLeft.size > k) {
	    			pointer = pointer.myLeft;
	    		} else {
	    			k -= pointer.myLeft.size;
	    			pointer = pointer.myRight;	
	    		}
	    	}
	    	if (pointer.myLeft.size == k){
	    		return (Comparable) pointer.myLeft;
	    	} else {
	    		return (Comparable) pointer.myRight;
	    	}
	    }
	    

	    public boolean contains(TreeNode t, T key) {
	    	if (t == null) {
				return false;
			} else if (t.myItem.equals(key)) {
				return true;
			} else if (key.compareTo(t.myItem) < 1) {
				return contains(t.myLeft, key);
			} else {
				return contains(t.myLeft, key);
			}
	    }
	    
	    public void add(T key) {
	        myRoot = add(myRoot, key);
	    }

	    private TreeNode add(TreeNode t, T key) {
	    	
	        if (t == null) {
	            return new TreeNode(key);
	        } else if (key.compareTo(t.myItem) < 0) {
	        	t.size++;
	            t.myLeft = add(t.myLeft, key);
	            return t;
	        } else {
	        	t.size++;
	            t.myRight = add(t.myRight, key);
	            return t;
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
	        public int size = 1;

	        public TreeNode(T item) {
	            myItem = item;
	            myLeft = myRight = null;
	        }

	        public TreeNode(T item, TreeNode left, TreeNode right) {
	            myItem = item;
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
}

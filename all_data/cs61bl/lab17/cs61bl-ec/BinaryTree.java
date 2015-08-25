import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList a1, ArrayList a2) {
        if (a1 == null || a2 == null) {
        	myRoot = null;
        } else if (a1.isEmpty() || a2.isEmpty()) {
        	myRoot = null;
        } else {
        	// create the tree!
        	for (Object key : a1) {
        		constructor_helper((T) key, a2, myRoot);
        	}
        }
        	
    }
    
    private boolean first = true;
    
    private void constructor_helper(T key, ArrayList a2, TreeNode t) {
    	if (t == null) {
    		if (first) {
    			myRoot = this.new TreeNode(key);
    			first = false;
    		}
    		else {
    			t = this.new TreeNode(key);
        		// using a2 to check
        		ArrayList<T> temp = new ArrayList<T>();
        		printInorder_string(temp);
        		boolean check = check(temp, a2);
        		if (!check) {
        			t = null;
        			return;
        		}
    		}
    	} else if (t.myLeft == null && t.myRight == null) {
    		// left first
    		t.myLeft = this.new TreeNode(key);
    		// using a2 to check
    		ArrayList<T> temp = new ArrayList<T>();
    		printInorder_string(temp);
    		boolean check_left = check(temp, a2);
    		if (!check_left) {
        		// not left -> must be right
    			t.myLeft = null;
    			constructor_helper(key, a2, t.myRight);
    			return;
    		} else {
    			// left is correct
    			return;
    		}
    	} else if (t.myLeft == null && t.myRight != null) {
    		// try to insert in the left
    		t.myLeft = this.new TreeNode(key);
    		// using a2 to check
    		ArrayList<T> temp = new ArrayList<T>();
    		printInorder_string(temp);
    		boolean check_left = check(temp, a2);
    		if (!check_left) {
    			// not left -> must be right
    			t.myLeft = null;
    			constructor_helper(key, a2, t.myRight);
    		} else {
    			// left is correct
    			return;
    		}
    	} else if (t.myLeft != null && t.myRight == null) {
    		// try to insert in the right
    		t.myRight = this.new TreeNode(key);
    		// using a2 to check
    		ArrayList<T> temp = new ArrayList<T>();
    		printInorder_string(temp);
    		boolean check_right = check(temp, a2);
    		if (!check_right) {
    			// not right -> must be left
    			t.myRight = null;
    			constructor_helper(key, a2, t.myLeft);
    		} else {
    			// right is correct
    			return;
    		}
    	} else {
    		constructor_helper(key, a2, t.myLeft);
    		constructor_helper(key, a2, t.myRight);
    	}
    }
    
    private void printInorder_string(ArrayList<T> temp) {
    	if (myRoot != null) {
        	myRoot.printInorder_string_helper(temp);
    	}
    }
    
    private boolean check(ArrayList<T> temp, ArrayList<T> a2) {
    	
    	int size_temp = temp.size();
    	int size_a2 = a2.size();
    	
    	for (int i = 0; i < size_temp; i++) {
    		for (int j = 0; j < size_temp; j++) {
    			if (i < j) {
    				int key1_pos_in_a2 = a2.indexOf(temp.get(i));
    				int key2_pos_in_a2 = a2.indexOf(temp.get(j));
    				if (key1_pos_in_a2 >= key2_pos_in_a2) {
    					// key1_pos_in_a2 should be less than key2_pos_in_a2
    					return false;
    				}
    			}
    		}
    	}
    	
    	return true;
    	
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
        
        ArrayList<String> a1 = new ArrayList<String>();
        ArrayList<String> a2 = new ArrayList<String>();
        a1.add("A");
        a1.add("B");
        a1.add("C");
        a1.add("D");
        a1.add("E");
        a1.add("F");
        
        a2.add("B");
        a2.add("A");
        a2.add("E");
        a2.add("D");
        a2.add("F");
        a2.add("C");
        BinaryTree<String> tree4 = new BinaryTree<String>(a1, a2);
        print(tree4, "sample tree 4");
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
        // Comparable kthLargest (int k);
        public int size = 0;

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
        
        
        private void printInorder_string_helper(ArrayList<T> temp) {
            if (myLeft != null) {
                myLeft.printInorder_string_helper(temp);
            }
            temp.add(myItem);
            if (myRight != null) {
                myRight.printInorder_string_helper(temp);
            }
        }
        
    }
}
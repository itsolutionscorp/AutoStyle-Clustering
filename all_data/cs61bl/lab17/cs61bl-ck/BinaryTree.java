import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    
    public BinaryTree(ArrayList preorder, ArrayList inorder) {
        myRoot = constructorHelper(preorder, inorder);
    }
    
    public TreeNode constructorHelper(List preorder, List inorder) {
        if (preorder.isEmpty()) {
            return null;
        }
        if (preorder.size() == 1) {
            System.out.println(preorder.get(0));
            return new TreeNode((T) preorder.get(0));
        }
        T rootItem = (T) preorder.get(0);
        T firstRight = (T) inorder.get(inorder.size()-1);
        int idxOfRight = preorder.indexOf(firstRight);
        int idxOfRoot = inorder.indexOf(rootItem);
        List leftbranch = inorder.subList(0, idxOfRoot);
        System.out.println("Left : " + leftbranch);
        List Rightbranch = inorder.subList(idxOfRoot + 1, inorder.size());
        System.out.println("Right : " + Rightbranch);
        
        List leftPOBranch = new ArrayList();
        List rightPOBranch = new ArrayList();
        for (int k = 0; k < preorder.size(); k++) {
            if (leftbranch.contains(preorder.get(k))) {
                leftPOBranch.add(preorder.get(k));
            } else if (Rightbranch.contains(preorder.get(k))) {
                rightPOBranch.add(preorder.get(k));
            }
        }
        return new TreeNode(rootItem, constructorHelper(leftPOBranch, leftbranch), constructorHelper(rightPOBranch, Rightbranch));
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
    
    public BinaryTree<T> getLeft() {
    	if (myRoot.myLeft == null) return null;
    	BinaryTree<T> left = new BinaryTree<T>(myRoot.myLeft);
    	return left;
    }
    
    public void setLeft(T o) {
    	if (myRoot.myLeft == null) {
    		myRoot.myLeft = new TreeNode(o);
    	} else {
    		myRoot.myLeft.myItem = o;
    	}
    }
    
    public BinaryTree<T> getRight() {
    	if (myRoot.myRight == null) return null;
    	BinaryTree<T> right = new BinaryTree<T>(myRoot.myRight);
    	return right;
    }
    
    public void setRight(T o) {
    	if (myRoot.myRight == null) {
    		myRoot.myRight = new TreeNode(o);
    	} else {
    		myRoot.myRight.myItem = o;
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
    
    public int size() {
    	if (myRoot == null) {
    		return 0;
    	}
    	if (myRoot.myLeft == null && myRoot.myRight == null) {
    		return 1;
    	}
    	if (myRoot.myLeft != null && myRoot.myRight == null) {
    		return 1 + myRoot.myLeft.size();
    	}
    	if (myRoot.myLeft == null && myRoot.myRight != null) {
    		return 1 + myRoot.myRight.size();
    	}
    	return 1 + myRoot.myLeft.size() + myRoot.myRight.size();
    }

    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        System.out.println(s.size());
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");
        ArrayList pre = new ArrayList();
        pre.add("A");
        pre.add("B");
        pre.add("C");
        pre.add("D");
        pre.add("E");
        pre.add("F");
        
        
        
        ArrayList ino = new ArrayList();
        ino.add("B");
        ino.add("A");
        ino.add("E");
        ino.add("D");
        ino.add("F");
        ino.add("C");
        
       BinaryTree<String> test = new BinaryTree<String>(pre, ino);
       print(test, "some");
       System.out.println(test.size());
       
    }
    
    public T getRoot() {
    	if (myRoot == null) {
    		return null;
    	}
    	return myRoot.myItem;
    }
    
    public void setRoot(T v) {
    	if (myRoot == null) {
    		myRoot = new TreeNode(v);
    	}
    	myRoot.myItem = v;
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
        
        public int size() {
        	if (myLeft == null && myRight == null) {
        		return 1;
        	}
        	if (myLeft != null && myRight == null) {
        		return 1 + myLeft.size();
        	}
        	if (myRight != null && myLeft == null) {
        		return 1 + myRight.size();
        	}
        	return 1 + myRight.size() + myLeft.size();
        }
        
       
    }
}
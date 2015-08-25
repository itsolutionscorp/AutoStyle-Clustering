import java.util.*;

//import AmoebaFamily.Amoeba;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }
    
    public BinaryTree(ArrayList<T> list1, ArrayList<T> list2) {
    	myRoot = binaryTreeConstructorHelper(list1, list2);
    	
    }
    
    public TreeNode binaryTreeConstructorHelper(ArrayList<T> list1, ArrayList<T> list2) {
    	if (list1.size() == 0) {
    		return null;
    	}
    	T rootItem = list1.get(0);
    	list1.remove(rootItem);
    	int indexOfRoot = list2.indexOf(rootItem);
    	ArrayList<T> leftList2 = new ArrayList<T>();
    	ArrayList<T> rightList2 = new ArrayList<T>();
    	ArrayList<T> leftList1 = new ArrayList<T>();
    	ArrayList<T> rightList1 = new ArrayList<T>();

    	
    	for (int i = 0; i < indexOfRoot; i++) {
    		leftList2.add(list2.get(i));
    	}
    	for (int i = indexOfRoot + 1; i < list2.size(); i++) {
    		rightList2.add(list2.get(i));
    	}
    	int indexOfSplit = leftList2.size();
    	for (int i = 0; i < indexOfSplit; i++) {
    		leftList1.add(list1.get(i));
    	}
    	for (int i = indexOfSplit; i < list1.size(); i++) {
    		rightList1.add(list1.get(i));
    	}

    	return new TreeNode(rootItem, binaryTreeConstructorHelper(leftList1, leftList2), binaryTreeConstructorHelper(rightList1, rightList2));
    	
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
    	
        //tests
//    	ArrayList<String> arr1 = new ArrayList<String>();
//    	arr1.add("A");
//    	arr1.add("B");
//    	arr1.add("C");
//    	arr1.add("D");
//    	arr1.add("E");
////    	arr1.add("F");
//    	ArrayList<String> arr2 = new ArrayList<String>();
//    	arr2.add("B");
//    	arr2.add("A");
//    	arr2.add("D");
//    	arr2.add("C");
//    	arr2.add("E");
////    	arr2.add("C");
//    	BinaryTree t = new BinaryTree(arr1, arr2);
//    	print(t, "t");
    }

    protected static void print(BinaryTree<?> t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    
    //This is the old iterator class you provided. We were not sure whether to use it, or the new one provided in the zipped files.
    // Method for the BinaryTree class
//    public Iterator<T> iterator(){
//        return new InorderIterator();
//    }
//
//    // Inner class inside of the BinaryTree class.
//    // Also, it uses java.util.Iterator and java.util.Stack.
//    private class InorderIterator implements Iterator<T> {
//        private Stack<TreeNode> nodeStack;
//        private TreeNode currentNode;
//
//        public InorderIterator() {
//            nodeStack = new Stack<TreeNode>();
//            currentNode = myRoot;
//        }
//
//        public boolean hasNext() {
//            return !nodeStack.isEmpty() || (currentNode != null);
//        }
//
//        public T next() {
//            TreeNode nextNode = null;
//
//            // find leftmost node with no left child
//            while (currentNode != null) {
//                nodeStack.push(currentNode);
//                currentNode = currentNode.myLeft;
//            }
//
//            // get leftmost node, then move to its right subtree
//            if (!nodeStack.isEmpty()) {
//                nextNode = nodeStack.pop();
//                assert nextNode != null;    // since nodeStack was not empty before the pop
//                currentNode = nextNode.myRight;
//            } else {
//                throw new NoSuchElementException();
//            }
//
//            return nextNode.myItem; 
//        }
//
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//
//    }
    
 // This code gets put inside the BinaryTree class.

 // Method for the BinaryTree class
 public Iterator iterator(){
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
        public int mySize;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
            mySize = setMySize();
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
            mySize = setMySize();
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
        
        
        public int setMySize() {
        	if (myLeft == null && myRight == null) {
        		return 1;
        	}
        	else if (myLeft == null) {
        		return 1 + myRight.setMySize();
        	}
        	else if (myRight == null) {
        		return 1 + myLeft.setMySize();
        	}
        	else {
        		return 1 + myLeft.setMySize() + myRight.setMySize();
        	}
        }
        
        public T kthLargest(int k) {
//        	if (myLeft == null && k == mySize - 1) {
//        		return myItem;
//        	}
//        	System.out.println(myRight.mySize);
        	if (myLeft != null && myRight != null) {
        		if (k == myRight.mySize) {
        			return myItem;
        		}
        		else if (k < myRight.mySize) {
//        			System.out.println(myRight.mySize);
        			return myRight.kthLargest(k);
        		}
        		else {
        			return myLeft.kthLargest(k - myRight.mySize - 1);
        		}
        	}
//        	else if (myRight == null && myLeft == null) {
//        		return myItem;
//        	}
        	else if (myRight == null && myLeft != null) {
        		return myLeft.kthLargest(k - 1);
        	}
        	else if (myLeft == null && myRight != null) {
        		return myRight.kthLargest(k);
        	}
        	return myItem;
        }
        
        public String toString() {
        	return myItem.toString();
        }
       
        
        
        //this method is used in the case that we were supposed to implement contains like previous labs; however, contans works fine as it is right now
//        public boolean contains(T key) {
//        	Comparable<T> keyComp = (Comparable<T>) key;
//        	if (myItem == keyComp) {
//        		return true;
//        	}
//        	else if (keyComp.compareTo(myItem) < 0) {
//        		return myLeft.contains(key);
//        	}
//        	else {
//        		return myRight.contains(key);
//        	}
//        }
    }
}
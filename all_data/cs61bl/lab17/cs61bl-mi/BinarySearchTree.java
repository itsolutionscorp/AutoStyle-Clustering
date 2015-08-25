import java.util.*;

//import BinaryTree.TreeNode;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.compareTo(key) == 0) {
			return true;
		}
		else if (key.compareTo(t.myItem)< 0) {
			return contains(t.myLeft, key);
		}
		else {
			return contains(t.myRight, key);
		}
	}
	
	public boolean contains(T key) {
		return contains(myRoot, key);
	}
	
	//This is implemented in the case that we were supposed to implement contains as in previous labs. However, contains works fine as is.
//	public boolean contains (T key) {
//		if (myRoot != null) {
//			return myRoot.contains(key);
//		}
//		return false;
//	}
	
//	public void print(TreeNode t, String indent) {
//		if (t != null) {
//			print(t.myRight, "    ");
//			System.out.println(indent + t.myItem.toString());
//			print(t.myLeft, "    ");
//		}
//	}
	
	public T replace(TreeNode t, T item) {
		if (t.myItem.compareTo(item) == 0) {
			T toReturn = t.myItem;
			t.myItem = item;
			return toReturn;
		}
		else if (t.myItem.compareTo(item) > 0) {
			return replace (t.myLeft, item);
		}
		else {
			return replace (t.myRight, item);
		}
	}
	
	public void put(TreeNode t, T item) {
		if (t == null) {
			myRoot = new TreeNode(item);
		}
		else {
			if (t.myLeft == null && t.myItem.compareTo(item) > 0) {
				t.myLeft = new TreeNode(item);
			}
			else if (t.myRight == null && t.myItem.compareTo(item) < 0) {
				t.myRight = new TreeNode(item);
			}
			else {
				if (t.myItem.compareTo(item) > 0) {
					put(t.myLeft, item);
				}
				else if (t.myItem.compareTo(item) < 0) {
					put(t.myRight, item);
				}
			}
		}

		
	}
	
//	public T put(T item) {
//		return put(myRoot, item);
//	}
//	
//	public T put(TreeNode t, T item) {
////		if (myRoot == null) {
////			myRoot = new TreeNode(item);
////			return null;
////		}
//		if (t == null) {
//			TreeNode toAdd = new TreeNode(item);
//			
//		}
//		if (t.myItem.compareTo(item) == 0) {
//			T toReturn = t.myItem;
//			t.myItem = item;
//			return toReturn; //Returns KVPair
//		}
//		else if (t.myItem.compareTo(item) < 0) {
//			return put(t.myLeft, item);
//		}
//		else {
//			return put(t.myRight, item);
//		}
//	}
	
	public T get(TreeNode t, T item) {
		//return KVPair
		if (t== null) {
			return null;
		}
		if (t.myItem.compareTo(item) == 0) {
			return t.myItem;
		}
		else if (t.myItem.compareTo(item) > 0) {
			return get(t.myLeft, item);
		}
		else {
			return get(t.myRight, item);
		}
		
	}
	
//	public ArrayList<TreeNode> inorder = new ArrayList<TreeNode>();
//	
//	public T remove(TreeNode t, T item) {
//		if (isEqual(t.myLeft, item)) {
//			//remove t.myLeft;
//		}
//		else if (isEqual(t.myRight, item)) {
//			//remove t.myright
//		}
//		
//		
//		
//			
//		}
//	}

	public boolean isEqual(TreeNode t, T item) {
		if (t == null || t.myItem.compareTo(item) != 0) {
			return false;
		}
		return true;
	}
		
	
//	public TreeNode inorderSuccessor(TreeNode t) {
//		String pr
//	}
//	
//	public boolean isLeaf(TreeNode t) {
//		return t.myLeft == null && t.myRight == null;
//	}
	
	public void add(T key) {
	    myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        t.mySize = t.setMySize();
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        t.mySize = t.setMySize();
	        return t;
	    }
	}
	
	private T kthLargest(int k) {
		//depth = mySize --> runtime of depth
		if (myRoot != null) {
			return myRoot.kthLargest(k);
		}
		return null;
	}
	
	//This was added as a precaution in order to ensure kth largest and myTreeMap function when submitted (The original class was in BinaryTree.java which is not being submitted)
//    protected class TreeNode {
//
//        public T myItem;
//        public TreeNode myLeft;
//        public TreeNode myRight;
//        public int mySize;
//
//        public TreeNode(T item) {
//            myItem = item;
//            myLeft = myRight = null;
//            mySize = setMySize();
//        }
//
//        public TreeNode(T item, TreeNode left, TreeNode right) {
//            myItem = item;
//            myLeft = left;
//            myRight = right;
//            mySize = setMySize();
//        }
//
//        private void printPreorder() {
//            System.out.print(myItem + " ");
//            if (myLeft != null) {
//                myLeft.printPreorder();
//            }
//            if (myRight != null) {
//                myRight.printPreorder();
//            }
//        }
//
//        private void printInorder() {
//            if (myLeft != null) {
//                myLeft.printInorder();
//            }
//            System.out.print(myItem + " ");
//            if (myRight != null) {
//                myRight.printInorder();
//            }
//        }
//        
//        
//        public int setMySize() {
//        	if (myLeft == null && myRight == null) {
//        		return 1;
//        	}
//        	else if (myLeft == null) {
//        		return 1 + myRight.setMySize();
//        	}
//        	else if (myRight == null) {
//        		return 1 + myLeft.setMySize();
//        	}
//        	else {
//        		return 1 + myLeft.setMySize() + myRight.setMySize();
//        	}
//        }
//        
//        public T kthLargest(int k) {
////        	if (myLeft == null && k == mySize - 1) {
////        		return myItem;
////        	}
////        	System.out.println(myRight.mySize);
//        	if (myLeft != null && myRight != null) {
//        		if (k == myRight.mySize) {
//        			return myItem;
//        		}
//        		else if (k < myRight.mySize) {
////        			System.out.println(myRight.mySize);
//        			return myRight.kthLargest(k);
//        		}
//        		else {
//        			return myLeft.kthLargest(k - myRight.mySize - 1);
//        		}
//        	}
////        	else if (myRight == null && myLeft == null) {
////        		return myItem;
////        	}
//        	else if (myRight == null && myLeft != null) {
//        		return myLeft.kthLargest(k - 1);
//        	}
//        	else if (myLeft == null && myRight != null) {
//        		return myRight.kthLargest(k);
//        	}
//        	return myItem;
//        }
//        
//        public String toString() {
//        	return myItem.toString();
//        }
       
        
        
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
//    }
	
//	public static void main(String[] args) {
//		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
//		t.add(4);
//		t.add(2);
//		t.add(1);
//		t.add(3);
//		t.add(8);
//		t.add(6);
//		t.add(9);
//		t.add(5);
//		t.add(7);
//		print(t, "test");
//		for (int i = 0; i < 9; i++) {
//			System.out.print(t.kthLargest(i));}
////		System.out.println(t.contains("a"));
////		System.out.println(t.contains("ab"));
////		System.out.println(t.contains("e"));
//		
//	}
	
	
}

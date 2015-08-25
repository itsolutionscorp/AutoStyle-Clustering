import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		if (myRoot == null) {
			// even if key == null, still return false
			return false;
		} else if (key == null) {
			// no way to find null in a non-empty tree ???????????????????????/
			return false;
		} else {
			return contains(myRoot, key);
		}
	}

	private boolean contains(TreeNode t, T key) {
		// assuming key != null
		if (t == null) {
			return false;
		} else if (t.myItem.compareTo(key) == 0) {
			return true;
		} else if (t.myItem.compareTo(key) > 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
	}



	public void add(T key) {
		myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			TreeNode rtn = new TreeNode(key);
			rtn.size = 1;
			return rtn;
		} else if (key.compareTo(t.myItem) == 0) {
			return t;
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



	public static BinaryTree<String> fillSampleTree3() {
		BinaryTree<String> t = new BinarySearchTree<String>();
		t.myRoot = t.new TreeNode("C", t.new TreeNode("A", null, t.new TreeNode("B")), t.new TreeNode("E", t.new TreeNode("D"), null));
		// Note: why t.new TreeNode(...)? NON-STATIC TreeNode class now!
		return t;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree<String> t = new BinarySearchTree<String>();
		print(t, "the empty tree");

		BinaryTree<String> tree3 = fillSampleTree3();
		print(tree3, "sample tree 3");

		// recreate using add

		BinarySearchTree<String> tree3_re = new BinarySearchTree<String>();
		tree3_re.add("C");
		tree3_re.add("A");
		tree3_re.add("D");
		tree3_re.add("E");
		tree3_re.add("B");
		tree3_re.add("D");
		print(tree3_re, "sample tree 3 reconstructed");

		System.out.println("************************************************");
		BinarySearchTree<Integer> k = new BinarySearchTree<Integer>();
		k.add(5);
		k.add(2);
		k.add(1);
		k.add(3);
		k.add(4);
		k.add(8);
		k.add(6);
		k.add(7);
		k.add(9);
		print(k, "k");
		System.out.println("k.kthLargest(1): " + k.kthLargest(1));
		System.out.println("k.kthLargest(2): " + k.kthLargest(2));
		System.out.println("k.kthLargest(3): " + k.kthLargest(3));
		System.out.println("k.kthLargest(4): " + k.kthLargest(4));
		System.out.println("k.kthLargest(5): " + k.kthLargest(5));
		System.out.println("k.kthLargest(6): " + k.kthLargest(6));
		System.out.println("k.kthLargest(7): " + k.kthLargest(7));
		System.out.println("k.kthLargest(8): " + k.kthLargest(8));
		System.out.println("k.kthLargest(9): " + k.kthLargest(9));


	}

	Comparable kthLargest(int k) {
		if (myRoot == null) {
			return 0;
		} else {
			return kthLargestHelper(myRoot, k);
		}
	}

	private static Comparable kthLargestHelper(BinaryTree.TreeNode t, int k) {
		int pos = pos(t);
		if (t.size - k == pos) {
			return (Comparable) t.myItem;
		} else if (t.size - k > pos) {

			return kthLargestHelper(t.myRight, k);
		} else {
			return kthLargestHelper(t.myLeft, t.myLeft.size - (t.size - k));
		}

	}


	private static int pos(BinaryTree.TreeNode t) {
		if (t.myLeft == null) {
			return 0;
		} else {
			return t.myLeft.size;
		}
	}

	public T find(T toFind) {
		for (T c : this) {
			if (c.compareTo(toFind) == 0) {
				return c; 
			}
		}
		return null; 

	}

	public T delete(T victim) {
		T removed = null;
		// find node 
		TreeNode victim_node = delete_helper(victim); // victim_node.myItem == victim
		if (victim_node == null) {
			return null;
		} else {
			removed = victim_node.myItem;
		}
		if (victim_node.myLeft != null && victim_node.myRight != null) {
			// first case: victim_node has 2 children - myLeft and myRight 
			// replace with in order successor 
			// delete successor (son) 
			T son = null; 
			Iterator i = this.iterator(); 
			while (i.hasNext()) {
				T t = (T) i.next(); 
				if (t.compareTo(victim) == 0) {
					son = (T) i.next(); 
					break; 
				}
			}
			remove_son(myRoot, son); 
			victim_node.myItem = son; 
		} else {
			// second and third cases: victim_node has 0 or 1 child
			// victim is similar to son in the first case above
			remove_son(myRoot, victim);
		}
		return removed;
	}

	private TreeNode delete_helper(T key) {
		if (myRoot == null) {
			// even if key == null, still return false
			return null; 
		} else if (key == null) {
			// no way to find null in a non-empty tree ???????????????????????/
			return null;
		} else {
			return delete_helper(myRoot, key);
		}
	}

	private TreeNode delete_helper(TreeNode t, T key) {
		// assuming key != null
		if (t == null) {
			return null;
		} else if (t.myItem.compareTo(key) == 0) {
			return t;
		} else if (t.myItem.compareTo(key) > 0) {
			return delete_helper(t.myLeft, key);
		} else {
			return delete_helper(t.myRight, key);
		}
	}

	private void remove_son(TreeNode t, T son) {
		// son must be in the tree 
		// son has 2 possibilities 
		// (1) son has no child 
		// (2) son has 1 child 
		// in both cases, find its parent and set its parent's son to its grandchild 
		if (myRoot.myItem.compareTo(son) == 0) {
			// t.myItem is the son
			if (myRoot.myLeft != null) {
				myRoot = myRoot.myLeft;
				return;
			} else if (myRoot.myRight != null) {
				myRoot = myRoot.myRight;
				return;
			} else {
				myRoot = null;
				return;
			}
		}
		if (t.myLeft != null && t.myLeft.myItem.compareTo(son) == 0) {
			if (t.myLeft.myLeft != null) {
				t.myLeft = t.myLeft.myLeft;
			} else {
				// no matter what t.myLeft.myRight is, just set t.myRight to t.myLeft.myRight
				t.myLeft = t.myLeft.myRight;
			}
		} else if (t.myRight != null && t.myRight.myItem.compareTo(son) == 0) {
			if (t.myRight.myLeft != null) {
				t.myRight = t.myRight.myLeft;
			} else {
				// no matter what t.myRight.myRight is, just set t.myRight to t.myRight.myRight
				t.myRight = t.myRight.myRight;
			}
		} else {
			// t is not the parent
			if (t.myItem.compareTo(son) > 0) {
				// son is in t's left
				remove_son(t.myLeft, son);
				// Note: don't need null check because the method assumes that son must be in the tree
			} else {
				// son is in t's right
				remove_son(t.myRight, son);
			}
		}
	}

}




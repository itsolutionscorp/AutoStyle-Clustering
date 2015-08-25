public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		if (myRoot != null) {
			return contains(myRoot, key);
		} else
			return false;
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null) {
			return false;
		} else if (t.myItem == null) {
			return false;
		} else if (t.myItem.compareTo(key) == 0) {
			return true;
		} else if (t.myItem.compareTo(key) < 0) {
			return contains(t.myRight, key);
		} else if (t.myItem.compareTo(key) > 0) {
			return contains(t.myLeft, key);
		} else
			return false;
	}

	public T findEqual(T key) {
		if (myRoot != null) {
			return findEqualHelper(myRoot, key);
		} else
			return null;
	}

	private T findEqualHelper(TreeNode t, T key) {
		if (t == null) {
			return null;
		} else if (t.myItem == null) {
			return null;
		} else if (t.myItem.compareTo(key) == 0) {
			return t.myItem;
		} else if (t.myItem.compareTo(key) < 0) {
			return findEqualHelper(t.myRight, key);
		} else if (t.myItem.compareTo(key) > 0) {
			return findEqualHelper(t.myLeft, key);
		} else
			return null;
	}

	public Comparable kthLargest(int k) {
		if (myRoot != null) {
			return kthLargestHelper(myRoot, k);
		}
		return null;

	}

	private Comparable<T> kthLargestHelper(TreeNode t, int k) {
		if (t != null) {
			if (k < 0 || k >= t.mySize) {
				return null;
			}
			if (t.myLeft != null) {
				if (k == t.myLeft.mySize) {
					return t.myItem;
				} else if (k < t.myLeft.mySize) {
					return kthLargestHelper(t.myLeft, k);
				} else if (k > t.myLeft.mySize && t.myRight != null) {
					return kthLargestHelper(t.myRight, k - t.myLeft.mySize - 1);
				} else {
					return null;
				}
			} else if (k == 0) {
				return t.myItem;
			} else if (t.myRight != null) {
				return kthLargestHelper(t.myRight, k - 1);
			}
		} else {
			return null;
		}
		return null;
	}

	public void add(T key) {
		myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key) {
		if (t == null) {
			return new TreeNode(key);
		} else if (contains(t, key)) { // ADDED IN BY KELLY
			return t;
		} else if (key.compareTo(t.myItem) < 0) {
			t.myLeft = add(t.myLeft, key);
			t.mySize += 1;
			return t;
		} else {
			t.myRight = add(t.myRight, key);
			t.mySize += 1;
			return t;
		}
	}

	public T remove(T key) {
		if (myRoot == null) {
			return null;
		}
		if (!contains(key)) {
			return null;
		} else {
			return remove(myRoot, key);
		}

	}

	private T remove(TreeNode t, T key) {
		T temp = null;
		if (myRoot.myItem.compareTo(key) == 0) {
			temp = myRoot.myItem;
			if (myRoot.mySize == 1) {
				myRoot = null;
				myRoot.mySize--;
			} else { // remove self and find a successor
				TreeNode successor = findSuccessor(myRoot);
				if (successor != null) {
					TreeNode successorParent = parentRemoveNode(myRoot,
							successor.myItem);
					findMax(successor).myRight = myRoot.myRight;
					successor.myLeft = myRoot.myLeft;
					successorParent.myLeft = null;
					myRoot = successor;
				} else {
					myRoot = myRoot.myLeft;
				}
			}
		}

		TreeNode parent = parentRemoveNode(t, key);
		if (parent != null) { // either left or right child is the one to remove
			if (parent.myItem.compareTo(key) < 0) {
				// remove right child
				TreeNode toRmv = parent.myRight;
				temp = toRmv.myItem;
				if (toRmv.mySize == 1) {
					parent.myRight = null;
				} else {
					// deal with child nodes - find successor
					TreeNode successor = findSuccessor(toRmv);
					if (successor != null) {
						TreeNode successorParent = parentRemoveNode(t,
								successor.myItem);
						if (successorParent != toRmv) {
							findMax(successor).myRight = toRmv.myRight; // set
																		// max
																		// of
																		// successor
																		// to
																		// the
																		// target's
																		// right
																		// branch
						}
						successor.myLeft = toRmv.myLeft; // set successor's left
															// (should be empty)
															// to the target's
															// left branch
						parent.myRight = successor; // parent's right child
													// becomes successor
						successorParent.myLeft = null;
					} else {
						parent.myRight = toRmv.myRight;
					}
				}
			} else {
				// remove left child
				TreeNode toRmv = parent.myLeft;
				temp = parent.myLeft.myItem;
				if (toRmv.mySize == 1) {
					parent.myLeft = null;
				} else {
					// // deal with child nodes - find successor
					TreeNode successor = findSuccessor(toRmv);
					if (successor != null) {
						TreeNode successorParent = parentRemoveNode(t,
								successor.myItem);
						if (successorParent != toRmv) {
							findMax(successor).myRight = toRmv.myRight;
						}
						successor.myLeft = toRmv.myLeft;
						parent.myLeft = successor;
						successorParent.myLeft = null;

					} else {
						parent.myLeft = toRmv.myLeft;

					}
				}
			}
		}
		resize(myRoot);
		return temp;
	}

	private void resize(TreeNode t) {
		if (t == null) {
			return;
		}
		if (t != null) {
			t.mySize = setSize(t);
		}
		if (t.myRight != null) {
			resize(t.myRight);
		}
		if (t.myLeft != null) {
			resize(t.myLeft);
		}
	}

	private int setSize(TreeNode t) {
		if (t == null) {
			return 0;
		} else if (t.myRight == null && t.myLeft == null) {
			return 1;
		} else if (t.myRight == null && t.myLeft != null) {
			return 1 + setSize(t.myLeft);
		} else if (t.myRight != null && t.myLeft == null) {
			return 1 + setSize(t.myRight);
		} else if (t.myRight != null && t.myLeft != null) {
			return 1 + setSize(t.myLeft) + setSize(t.myRight);
		} else
			return 0;
	}

	private TreeNode findSuccessor(TreeNode t) {
		if (t == null) {
			return null;
		} else if (t.myRight == null) {
			return null;
		} else if (t.myRight.myLeft == null) {
			return t.myRight;
		} else {
			return findMin(t.myRight);
		}
	}

	private TreeNode findMin(TreeNode t) {
		if (t == null) {
			return null;
		} else if (t.myRight == null && t.myLeft == null) {
			return t;
		} else if (t.myLeft != null) { // if the left branch is not empty;
			return findMin(t.myLeft);
		} else if (t.myLeft == null) {
			return t;
		} else
			return null;
	}

	private TreeNode findMax(TreeNode t) {
		if (t == null) {
			return null;
		} else if (t.myRight == null) {
			return t;
		} else {
			return findMax(t.myRight);
		}
	}

	private TreeNode parentRemoveNode(TreeNode t, T key) {
		if (t == null) {
			return null;
		}
		if (t.myItem.compareTo(key) < 0) {
			if (t.myRight != null) {
				if (t.myRight.myItem.compareTo(key) == 0) {
					return t; // the parent
				} else
					return parentRemoveNode(t.myRight, key);
			}
		} else if (t.myItem.compareTo(key) > 0) {
			if (t.myLeft != null) {
				if (t.myLeft.myItem.compareTo(key) == 0) {
					return t; // the parent
				} else {
					return parentRemoveNode(t.myLeft, key);
				}
			}
		}
		return null; // couldn't find the parent
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>();
		myTree.add(1);
		myTree.add(5);
		myTree.add(6);
		myTree.add(4);
		myTree.add(2);
		myTree.add(3);
		// myTree.printInorder();
		// print(myTree, "test pre");

		myTree.remove(6);
		// System.out.println("this is what i removed: " + myTree.remove(4));
		// System.out.println("this is what i removed: " + myTree.remove(3));
		print(myTree, "test post");

		// for (int i = 0; i < 6; i++) {
		// System.out.println(myTree.kthLargest(i));
		// }

	}

}

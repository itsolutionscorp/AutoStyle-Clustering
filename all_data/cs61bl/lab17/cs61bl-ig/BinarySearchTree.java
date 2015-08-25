

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		return contains(myRoot, key);
	}

	private boolean contains(TreeNode t, T key) {
		if (t == null)
			return false;
		if (t.myItem.compareTo(key) == 0)
			return true;
		if (t.myItem.compareTo(key) > 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}

	}

	public void add(T key) {
		if (contains(key)) return;
		if(myRoot == null) {
			myRoot = new TreeNode(key);
		} else {
			add(myRoot, key);
		}

	}
	

	public void add(TreeNode t, T key) {
		if (t.myItem.compareTo(key) > 0) {
			if (t.myLeft == null) {
				t.myLeft = new TreeNode(key);
				
			} else {
				add(t.myLeft, key);
			}
		} else {
			if (t.myRight == null) {
				t.myRight = new TreeNode(key);
			} else {
				add(t.myRight, key);

			}

		}
		int right = t.myRight == null ? 0 : t.myRight.size;
		int left = t.myLeft == null ? 0 : t.myLeft.size;
		t.size = right + 1 + left;
	}
	
	public TreeNode remove(T key) {
		return remove(key, myRoot);
	}
	
	public TreeNode remove(T key, TreeNode node) {
		if(node.myItem.compareTo(key) > 0) {
			node.myLeft = remove(key, node.myLeft);
		} else if(node.myItem.compareTo(key) < 0) {
			node.myRight = remove(key, node.myRight);
		} else {
			int count = 0;
			if(node.myRight != null) 	count++;
			if(node.myLeft != null) 	count++;
			if(count == 0) {
				return null;
			} else if(count == 1) {
				if(node.myRight != null) 	return node.myRight;
				if(node.myLeft != null) 	return node.myLeft;
			} else {
				replaceWithNextInorder(node, node);
			}
		}
		return node;
		
	}
	
	public void replaceWithNextInorder(TreeNode node, TreeNode replaced) {
		if(node.myRight != null) {
			TreeNode deepest = node.myRight;
			while(deepest.myLeft != null) {
				if(deepest.myLeft.myLeft == null) break;
				deepest = deepest.myLeft;
			}
			System.out.println(deepest.myItem + " " + replaced.myItem);
			replaced.myItem = deepest.myItem;
			replaced.myRight = deepest.myRight;
			replaced.myLeft = deepest.myLeft;
		} else if(node.myLeft != null) {
			TreeNode deepest = node.myLeft;
			while(deepest.myRight != null) {
				if(deepest.myRight.myRight == null) break;
				deepest = deepest.myRight;
			}
			replaced.myItem = deepest.myItem;
			replaced.myRight = deepest.myRight;
			replaced.myLeft = deepest.myLeft;
		}
	}
	
	
	public T find(T key) {
		return find(myRoot, key);
	}
	
	public T find(TreeNode t, T key) {
		if (t.myItem.compareTo(key) == 0)
			return t.myItem;
		if (t.myItem.compareTo(key) > 0) {
			if(t.myLeft == null) return null;
			return find(t.myLeft, key);
		} else {
			if(t.myRight == null) return null;
			return find(t.myRight, key);
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.add(2);

		tree.add(5);
		tree.add(7);
		tree.add(3);
		tree.add(4);
		tree.add(6);

		tree.add(10);
		tree.add(8);
		tree.add(14);
		tree.print();
		System.out.println("------------");
		tree.remove(7);
		tree.print();
		//System.out.println(tree.kthLargest(6));
		
	}

	public Comparable kthLargest (int k) {
		return kthLargest(k + 1, myRoot);
	}
	
    public Comparable kthLargest(int index, TreeNode node) {
    	
        int leftS = node.myLeft == null ? 0 : node.myLeft.size;
        int rightS = node.myRight == null ? 0 : node.myRight.size;
        System.out.println(leftS + " " + rightS + " " + index);
        if(index <= rightS)             return kthLargest(index, node.myRight);
        else if(index == rightS + 1)    return node.myItem;
        else                            return kthLargest(index - rightS - 1, node.myLeft);
}


}

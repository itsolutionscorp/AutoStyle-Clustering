import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public boolean contains(T key) {
		if (myRoot == null) {
			return false;
		}
		if (myRoot.myItem.equals(key)) {
			return true;
		}
		else if (myRoot.myItem.compareTo(key) > 0) {
			return contains(myRoot.myLeft, key);
		}
		else {
			return contains(myRoot.myRight, key);
		}
	}
	
	private boolean contains (TreeNode t, T key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.equals(key)) {
			return true;
		}
		else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		}
		else {
			return contains(t.myRight, key);
		}
	}
	
	public void add(T key) {
	    if (!this.contains(key)) {
	    	myRoot = add(myRoot, key);
	    	System.out.println(myRoot.getClass());
	    }
	}
	
	private TreeNode add(TreeNode t, T key) {
	    if (t == null) {
	        return new TreeNode(key);
	    } else if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = add(t.myLeft, key);
	        return t;
	    } else {
	        t.myRight = add(t.myRight, key);
	        return t;
	    }
	}
	
	public T get(T key) {
		return getHelper(key, myRoot);
	}
	
	private T getHelper(T key, TreeNode t) {
		if(t==null){
			return null;
		}
		if (t.myItem.compareTo(key)==0) {	
			return t.myItem;
		}
		else if (t.myItem.compareTo(key) < 1) {
			return getHelper(key, t.myRight);
		}
		else {
			return getHelper(key, t.myLeft);
		}
	}
	
	//Algorithm inspired by www.mathcs.emory.edu
	public T remove(T key){
		TreeNode toRemove = removeHelper(key, myRoot);
		System.out.println(toRemove.myItem);
		if (toRemove.myRight == null && toRemove.myLeft == null) {
			T res = toRemove.myItem;
			TreeNode parent = removeHelperTwo(this.myRoot, toRemove);
			if (parent.myLeft.equals(toRemove)) {
				parent.myLeft = null;
			}
			else {
				parent.myRight = null;
			}
			return res;
		}
		TreeNode iOSuc = toRemove.myRight;
		TreeNode iOSucPar = toRemove;
		if (iOSuc.myLeft == null) {
			T res = (T) toRemove.myItem;
			toRemove.myItem = iOSuc.myItem;
			toRemove.myRight = iOSuc.myRight;
			return res;
		}
		while (iOSuc.myLeft != null) {
			iOSucPar = iOSuc;
			iOSuc = iOSuc.myLeft;
		}
		T res = (T) toRemove.myItem;
		toRemove.myItem = iOSuc.myItem;
		iOSucPar.myLeft = iOSuc.myRight;
		return res;
	}
	
	public TreeNode removeHelper(T key, TreeNode t) {
		if(t==null){
			return null;
		}
		if (t.myItem.compareTo(key)==0) {	
			return t;
		}
		else if (t.myItem.compareTo(key) < 1) {
			return removeHelper(key, t.myRight);
		}
		else {
			return removeHelper(key, t.myLeft);
		}
	}
	
	private TreeNode removeHelperTwo(TreeNode t, TreeNode target) {
		if (t.myLeft == null && t.myRight == null) {
			return null;
		}
		if (t.myLeft.equals(target) || t.myRight.equals(target)) {
			return t;
		}
		if (t.myItem.compareTo(target.myItem) < 0) {
			return removeHelperTwo(t.myRight, target);
		}
		return removeHelperTwo(t.myLeft, target);
	}
		
//		Object target = key;
//		System.out.println(key.getClass());
//		Iterator i = this.iterator();
//		if (this.myRoot.myItem.equals(target)) {
//			T inOrderSuc = null;
//			TreeNode toDelete = null;
//			while (i.hasNext()) {
//				toDelete = (TreeNode) i.next();
//				if (toDelete.myItem.equals(target)) {
//					inOrderSuc = ((TreeNode) i.next()).myItem;
//					break;
//				}
//			}
//			myRoot.myItem = inOrderSuc;
//			return remove(toDelete.myItem);
//		}
//		while(i.hasNext()){
//			TreeNode next = (TreeNode) i.next();
//			if (next.myLeft != null) {
//				if (next.myLeft.myItem.equals(target)) {
//					if (next.myLeft.myLeft == null
//							&& next.myLeft.myRight == null) {
//						next.myLeft = null;
//						return next.myLeft.myItem;
//					} else {
//						Iterator iter = this.iterator();
//						T inOrderSuc = null;
//						TreeNode toDelete = null;
//						while (iter.hasNext()) {
//							toDelete = (TreeNode) iter.next();
//							if (toDelete.myItem.equals(target)) {
//								inOrderSuc = ((TreeNode) iter.next()).myItem;
//								break;
//							}
//						}
//						next.myLeft.myItem = inOrderSuc;
//						return remove(toDelete.myItem);
//					}
//				}
//			}
//			if (next.myRight != null) {
//				if (next.myRight.myItem.equals(target)) {
//					if (next.myRight.myLeft == null
//							&& next.myRight.myRight == null) {
//						next.myRight = null;
//						return next.myRight.myItem;
//					} else {
//						Iterator iter = this.iterator();
//						T inOrderSuc = null;
//						TreeNode toDelete = null;
//						while (iter.hasNext()) {
//							toDelete = (TreeNode) iter.next();
//							if (toDelete.myItem.equals(target)) {
//								inOrderSuc = ((TreeNode) iter.next()).myItem;
//								break;
//							}
//						}
//						next.myRight.myItem = inOrderSuc;
//						return remove(toDelete.myItem);
//					}
//				}
//			}
//		}
//		MyTreeMap tm = new MyTreeMap();
//		return (T) tm.new KVPair("Penguin", "Noot noot!");
//	}
	
	
	
	public Comparable kthLargest(int k) {
		return kthLargest(myRoot, k);
	}
	
	private Comparable kthLargest(TreeNode t, int k) {
		if(t.myLeft == null && t.myRight == null) {
			return t.myItem;
		}
		if (t.myLeft == null) {
			if (k == t.height) {
				return t.myItem;
			}
			else {
				return kthLargest(t.myRight, k);
			}
		}
		if (t.myRight == null) {
			if (k == 1) {
				return t.myItem;
			}
			else {
				return kthLargest(t.myLeft, k - 1);
			}
		}
		if (t.myRight != null && t.myRight.height == k - 1) {
			return t.myItem;
		}
		else if (t.myRight.height < k - 1) {
			return kthLargest(t.myLeft, k - t.myRight.height - 1);
		}
		else {
			return kthLargest(t.myRight, k);
		}
	}
}
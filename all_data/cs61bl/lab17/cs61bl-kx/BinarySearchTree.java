import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;





public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	
	T replaced;
	T removed;

	public BinarySearchTree(){
		super();
	}
	public BinarySearchTree(TreeNode t){
		super(t);
	}
	public boolean contains(T key){
		if(myRoot!=null){
			return containsHelper(myRoot, key);
		}
		return false;
	}
	
	private boolean containsHelper(TreeNode t, T key){
		
		if(t==null){
			return false;
		}
		if(key.compareTo(t.myItem)==0){
			return true;
		}
		else if(key.compareTo(t.myItem)<0){
			return containsHelper(t.myLeft, key);
		}
		else{
			return containsHelper(t.myRight, key);
		}
	}
	
	public T add(T key){
		replaced = null;
		myRoot = addHelper(myRoot, key);
		return replaced;
	}
	
	private TreeNode addHelper(TreeNode t, T key){
		
		if (t == null) {
	        return new TreeNode(key);
	    }
		if (key.compareTo(t.myItem) < 0) {
	        t.myLeft = addHelper(t.myLeft, key);
	        t.size++;
	        return t;
	    } else if(key.compareTo(t.myItem) > 0) {
	        t.myRight = addHelper(t.myRight, key);
	        t.size++;
	        return t;
	    }
	    else{
	    	T temp = t.myItem;
	    	t.myItem = key;
	    	replaced = temp;
	    	return t;
	    }
	}
	
	public T get(T target){
		return getHelper(myRoot, target);
	}
	
	private T getHelper(TreeNode t, T target){
		if(t==null){
			return null;
		}
		if(target.compareTo(t.myItem)<0){
			return getHelper(t.myLeft, target);
		}
		else if(target.compareTo(t.myItem)>0){
			return getHelper(t.myRight, target);
		}
		else{
			return t.myItem;
		}
	}
	
	public T remove(T target){
		removed = null;
		myRoot = removeHelper(myRoot, target);
		return removed;
		
	}
	
	private TreeNode removeHelper(TreeNode t, T target){
		if(t==null){
			return null;
		}
		if(target.compareTo(t.myItem)<0){
			t.myLeft = removeHelper(t.myLeft, target);
			return t;
		}
		else if(target.compareTo(t.myItem)>0){
			t.myRight = removeHelper(t.myRight, target);
			return t;
		}
		else{
			if(removed == null){
			removed = t.myItem;
			}
			if(t.myLeft==null && t.myRight==null){
				
				return null;
			}
			else if(t.myLeft == null){
			
				return t.myRight;
			}
			else if(t.myRight == null){
				
				return t.myLeft;
			}
			else{
				TreeNode sucNode = calcInorder(t);
				removeHelper(myRoot, sucNode.myItem);
				t.myItem = sucNode.myItem;
				return t;
			}
		}
	}
	
	private TreeNode calcInorder(TreeNode t){
		Iterator i = iterator();
		while(i.hasNext()){
			Object next = i.next();
			T n = (T) next;
			if(n.compareTo(t.myItem) == 0){
				BinarySearchTree b = new BinarySearchTree();
				T item = (T) i.next();
				return b.find(myRoot, item);
			}
		}
		return null;
	}
	
	private TreeNode find(TreeNode t, T item){
		if(item.compareTo(t.myItem) == 0){
			return t;
		}
		else if(item.compareTo(t.myItem) < 0){
			return find(t.myLeft, item);
		}
		else{
			return find(t.myRight, item);
		}
	}
	
	
	
	public Comparable kthLargest(int k){
		if(myRoot!=null){
			return kthLargestHelper(myRoot, k);
		}
		return null;
	}
	
	private Comparable kthLargestHelper(TreeNode t , int k){
		if((t.myLeft==null && t.myRight==null) && k==1){
			return t.myItem;
		}
		if(t.myLeft==null){
			return kthLargestHelper(t.myRight, k-1);
		}
		
		if(t.myLeft.size==k-1){
			return t.myItem;
		}
		else if(t.myLeft.size>k-1){
			return kthLargestHelper(t.myLeft, k);
		}
		else{
			return kthLargestHelper(t.myRight, k-t.myLeft.size-1);	
		}
	}
	
	/*public static void main(String args[]){
		BinarySearchTree t = new BinarySearchTree();
		t.add(new Integer(6));
		t.add(new Integer(4));
		//System.out.println(t.contains(6));
		t.add(new Integer(8));
		//System.out.println(t.contains(2));
		t.add(new Integer(5));
		t.add(new Integer(7));
		t.add(new Integer(10));
		t.add(new Integer(2));
		//t.add(new Integer(3));
		t.add(new Integer(1));
		t.add(new Integer(41));
		t.add(new Integer(-1));
		//System.out.println(t.myRoot.size);
		System.out.println("answer " + t.kthLargest(10));
	}*/
}
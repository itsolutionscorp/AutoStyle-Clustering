public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    
	public boolean contains (T key){
		return containsHelper(myRoot, key);
	}
	
	public boolean containsHelper (TreeNode t, T key){
    	if (t == null){
    		return false;
    	} else if (t.myItem.equals(key)){
    		return true;
    	} else if (t.myItem.compareTo(key) < 0){
    		return containsHelper(t.myLeft, key);
    	} 
    	return containsHelper(t.myRight, key);
    }
    
	public void delete (T key){
		deleteHelper(myRoot, key);
	}
	
	public void deleteHelper (TreeNode t, T key){
    	if(t.mySize == 1){
    		t = null;
    	} else if (t.mySize ==2){
    		if (t.myLeft != null){
    			t.myItem = t.myLeft.myItem;
    			t.myLeft = null;
    		} else{
    			t.myItem = t.myRight.myItem;
    			t.myRight = null;
    		}
    	} else{
    		int rightSize = t.myRight.mySize;
    		Comparable temp = kthLargestHelper(t, rightSize - 1);
    		deleteHelper(t, (T) temp);
    		t.myItem = (T) temp;
    		
    	}
    }
	
	//Assume tree contains key
	public T get (T key){
		return getHelper(myRoot, key);
	}
	
	//Assume tree contains key
	public T getHelper (TreeNode t, T key){
    	if (t == null || key == null){
    		return null;
    	} else if (t.myItem.equals(key)){
    		return t.myItem;
    	} else if (t.myItem.compareTo(key) < 0){
    		return getHelper(t.myLeft, key);
    	} 
    	return getHelper(t.myRight, key);
    }
	
	public Comparable kthLargest (int k){
		//assuming 0 < k < number of keys in tree
		return kthLargestHelper(myRoot, k);
	}
	
	public int Size(TreeNode t){
		//so that when we ask for size of a null tree, it'll just return 0 rather than null pter.
		if (t == null){
			return 0;
		}
		return t.mySize;
	}
	
	public Comparable kthLargestHelper (TreeNode t, int k){
		if (k == Size(t.myRight)){
			return t.myItem;
		} else if (k < Size(t.myRight)){
			return kthLargestHelper(t.myRight, k);
		}
		//if k is larger than the size of right tree, search left		
		return kthLargestHelper(t.myLeft, k - Size(t.myRight) - 1);
		
	}
	
    public void add(T key) {
        if (!contains(key)){
        	if (myRoot != null){
        		myRoot.mySize ++;
        	}
        	myRoot = add(myRoot, key);
        	
        }
    }

    public TreeNode add(TreeNode t, T key) {
        if (t == null) {
            return new TreeNode(key);
            
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            t.myLeft.mySize ++;
            return t;
        } else {
            t.myRight = add(t.myRight, key);
            t.myRight.mySize ++;
            return t;
        }
    }
    
    
    
    public static BinarySearchTree<Integer> fillSampleTree(){
    	BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
    	t.myRoot = t.new TreeNode(5, t.new TreeNode(3, t.new TreeNode(1), t.new TreeNode(2)), t.new TreeNode(8, t.new TreeNode(7), t.new TreeNode(9)));
    	return t;
    }
    
    public static void main(String[] args){
//    	BinarySearchTree<String> t = new BinarySearchTree<String>();
//    	t.add("C");
//    	t.add("E");
//    	t.add("D");
//    	t.add("A");
//    	t.add("B");
//    	
//    	t.printInorder();
//    	t.printPreorder();
    	
    	BinarySearchTree<Integer> s = fillSampleTree();
    	s.printInorder();
    	System.out.println(s.myRoot.mySize); //should be 7
    	System.out.println(s.myRoot.myRight.mySize); // should be 3
    	System.out.println(s.kthLargest(3)); // should be 5
    	System.out.println(s.kthLargest(0)); // should be 9
    	System.out.println(s.kthLargest(6)); // should be 1
    	System.out.println(s.kthLargest(4)); // should be 2
    }
    
}

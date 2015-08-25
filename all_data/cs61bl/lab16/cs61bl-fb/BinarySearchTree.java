public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    	public boolean contains(T key){
    		

            return containsHelper(myRoot,key);
    	}
    	private boolean containsHelper (TreeNode t, T key){
    		if(t == null){
    			return false;
    		}
    		if(key.compareTo(t.myItem)==0){
    			return true;
    		}else if(key.compareTo(myRoot.myItem) <0){
    			return containsHelper(t.myLeft, key);
    		}else{
    			return containsHelper(t.myRight, key);
    		}
    	}
    	public void add(T key) {
    	    myRoot = add(myRoot, key);
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
    	public static void main(String[] args){
//    		BinarySearchTree B = new BinarySearchTree();
//    		B.add("abc");
//    		B.add("bcd");
//    		B.add("cde");
//    		System.out.println(B.contains("abcd"));
    	}
    }
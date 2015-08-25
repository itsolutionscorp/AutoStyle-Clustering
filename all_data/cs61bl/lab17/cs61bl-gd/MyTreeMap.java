import javax.swing.tree.TreeNode;

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree tree;
	// TODO You may declare new instance variables here
	

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		tree=new BinarySearchTree();
		size=0;
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns whether the map contains the given key.
	 */
	public boolean containsKey(K key) {
		// TODO Complete this!
		System.out.println("Star!!!!!!");
		tree.print();
		System.out.println("end");
		if(tree==null){return true;}
			
			else{return containsKey(tree.myRoot,key);}
		
	}
	public boolean containsKey(BinaryTree.TreeNode t,K key){
		if(t==null){return false;}
		if(((KVPair)t.myItem).getKey().compareTo(key)==0){
			return true;
		}
		if(((KVPair)t.myItem).getKey().compareTo(key)<0){
			//System.out.println("to the right");
			return containsKey(t.myRight,key);}
		else{
	if(((KVPair)t.myItem).getKey().compareTo(key)>0){
		//System.out.println("to the left");
		return containsKey(t.myLeft,key);
	}
	return true;
	}
	
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		// TODO Complete this!
		size++;
		boolean inserted =false;
		if(containsKey(key)){
			size--;
			BinaryTree.TreeNode temp =(BinaryTree.TreeNode) (tree.myRoot);
			while ((temp.myLeft!=null||temp.myRight!=null)&&
					temp!=null
					&&!inserted){
				KVPair cur =(KVPair)(temp.myItem);
			int C=cur.getKey().compareTo(key);
			if(C==0)
			{
				V old = cur.getValue();
			cur.setValue(value);
			inserted =true;
			return old;}
			if(C<0){
				temp=temp.myRight;
			}
			if(C>0){
				temp=temp.myLeft;
			}
			}
//			KVPair toBeAdd=new KVPair(key,value);
//			if(((KVPair)temp.myItem).getKey().compareTo(key)<0){
//				tree.addToRight(temp, toBeAdd);
//			}else{tree.addToLeft(temp,toBeAdd);}
			}
		//not exist before add directly 
		KVPair temp = new KVPair(key,value);
		tree.add( temp);
		
//			//addkeyfirst and replace;
//			tree.add(key);
//			KVPair temp = new KVPair(key,value);
//			tree.replace(key, temp);
		    
			
		
				
			
			
			
		return null;}
	

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if(containsKey(key)){
			size--;
			V temp = get(key);
			KVPair cur = new KVPair(key,temp);
			tree.delete(cur);
			
			tree.print();
			return temp;
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		tree.print();
		BinaryTree.TreeNode temp = (BinaryTree.TreeNode) ( tree.myRoot);
		while(temp!=null){
			if(((KVPair)temp.myItem).getKey()==key){
				return ((KVPair)temp.myItem).getValue();
			}
			if(((KVPair)temp.myItem).getKey().compareTo(key)<0){
				temp=temp.myRight;
			}
			if(((KVPair)temp.myItem).getKey().compareTo(key)>0){
				temp=temp.myLeft;
			}
		}
		return null;
		
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
		public K getKey(){
			return key;
		}
		public V getValue(){
			return value;
		}

		@Override
		public int compareTo(Object o) {
			return key.compareTo(((KVPair)o).getKey());
		}
		public String toString(){
			return key.toString();
		}
	}
}

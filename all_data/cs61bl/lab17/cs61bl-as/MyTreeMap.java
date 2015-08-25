/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	//private TreeNode
	private  TreeNode myRoot;
	
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		myRoot =  null;
		// TODO Complete this!
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
		// TODO Complete this
		return get(key) !=null;
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value)   {
		
		//case 1. dose not contain this key
		if(!containsKey(key)){
			myRoot.put(myRoot,key,value);
			return null;
		}else{
			V return_value = this.get(key);
			myRoot.put(myRoot,key,value);
			return return_value;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if(!containsKey(key)){
			return null;
		}else{
			
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if(myRoot !=null){
			myRoot.get(key);
		}
		return null;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
	}
	
	private class TreeNode{
        public KVPair myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        public int size = 1;

        public TreeNode(KVPair item) {
            myItem = item;
            myLeft = myRight = null;
        }

        public V get(K key) {
        	if(key ==null){
        		return null;
        	}else if(this.myItem.key == key){
        		return this.myItem.value;
        	}else if(this.myItem.key.compareTo(key)<1){
        		if(this.myLeft !=null){
        		return this.myLeft.get(key);
        				}
        	}else{
        		if(this.myRight !=null){
        		return this.myRight.get(key);
        		}
        	}
        	return null;
        
		}

		public  TreeNode put(TreeNode t, K key, V value) {
			
        	KVPair putValue = new KVPair(key, value);
        	if (t ==null){
        		return new TreeNode(putValue);
        	}else if(t.myItem ==null){
        		return new TreeNode(putValue);
        	}else if(t.myItem.key == value){
        		t.myItem.setValue(value);
        		return null;
        	}else if (key.compareTo(t.myItem.key) <0){
        		t.size++;
        		if(myLeft !=null){
        		t.myLeft =put(t.myLeft,key,value);}
        		return t;
        	}else{
        		t.size++;
        		if(myRight !=null){
        		t.myRight = put(t.myRight,key,value);}
        		return t;
        	}			
		}
		
		public TreeNode(KVPair item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
        }
	}
}

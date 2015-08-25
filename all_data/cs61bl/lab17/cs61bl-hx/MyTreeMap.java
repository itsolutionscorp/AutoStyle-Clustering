/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private static int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private KVPair myRoot;


	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myRoot = null;		
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
		if (myRoot==null){
			return false;
		}
		return containsHelper(myRoot, key);

	}

	private boolean containsHelper (KVPair p, K key){
		if (p==null){
			return false;
		}
		if(key.compareTo(p.key)==0){
			return true;
		}
		if(key.compareTo(p.key)<0){
			return containsHelper(p.myLeft, key);
		}
		if(key.compareTo(p.key)>0){
			return containsHelper(p.myRight, key);
		}
		return false;
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
		if (myRoot==null){
			myRoot = new KVPair(key, value);;
			return null;
		}
		if (containsKey(key)){
			V rv = get(key);
			putHelper(myRoot, key, value);
			return rv;
		}
		// never contain return null;
		putHelper(myRoot, key, value);
		return null;
	}

	private KVPair putHelper(KVPair p, K key, V value){		
		if (p == null){	
			size++;	
			return new KVPair(key, value);			
		}

		if(p.key.compareTo(key)==0){
			KVPair prev = p;
			p.setValue(value);
			return prev;
		}
		if(p.key.compareTo(key)>0){		
			p.myLeft = putHelper(p.myLeft, key, value);					
		}
		else{			
			p.myRight = putHelper(p.myRight, key, value);					
		}
		return p;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (containsKey(key)!=true){
			return null;
		}

		if (myRoot.key.equals(key) && myRoot.myLeft == myRoot.myRight) // single leaf
		{
			size--;
			V rv = myRoot.value;
			myRoot = null;
			return rv;
		}

		return remove(myRoot, myRoot, key).value;		
	}

	private KVPair subLeftRightMost (KVPair p, KVPair parent){		
		if (p == null)
			return parent;
		else if (p.myRight != null)
			return subLeftRightMost(p.myRight, p);
		else if (p.myLeft != null)
			return subLeftRightMost(p.myLeft, p);
		else{
			if (parent.myLeft == p)
				parent.myLeft = null;
			else if (parent.myRight == p)
				parent.myRight = null;
			return p;
		}

	}
	
	private KVPair subRightLeftMost (KVPair p, KVPair parent){		
		if (p == null)
			return parent;		
		else if (p.myLeft != null)
			return subLeftRightMost(p.myLeft, p);
		else if (p.myRight != null)
			return subLeftRightMost(p.myRight, p);
		else{
			if (parent.myLeft == p)
				parent.myLeft = null;
			else if (parent.myRight == p)
				parent.myRight = null;
			return p;
		}

	}	

	private KVPair remove(KVPair p,KVPair parent, K k){
		if (p.key.equals(k))
		{
			size--;
			KVPair rv = new KVPair (p.key,p.value);
			if (p.myLeft == null && p.myRight == null) // if it is a leaf
			{					
				if(parent.key.compareTo(k)>0)
					parent.myLeft = null;	
				else if (parent.key.compareTo(k)<0)
					parent.myRight = null;
				return rv;
			}

			KVPair sub;
			if (p.myLeft != null)
			sub  = subLeftRightMost (p.myLeft, parent);
			else
			sub = subRightLeftMost (p.myRight, parent);
			
			p.setKey(sub.key);
			p.setValue(sub.value);

			return rv;
		}
		else if(p.key.compareTo(k)>0 && p.myLeft !=null)
			return remove(p.myLeft, p, k);
		else if(p.key.compareTo(k)<0 && p.myRight !=null)
			return remove(p.myRight, p, k);
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (!containsKey(key)){
			return null;
		}
		return getHelper(myRoot, key);

	}

	private V getHelper(KVPair p , K key){
		if (p.key.equals(key)){
			return p.value;
		}
		if(p.key.compareTo(key)>0){
			return getHelper(p.myLeft, key);
		}
		if(p.key.compareTo(key)<0){
			return getHelper(p.myRight, key);
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
		private KVPair myLeft;
		private KVPair myRight;

		public KVPair(K k, V v) {
			key = k;
			value = v;
			myLeft = null;
			myRight = null;
		}

		public void setValue(V v) {
			value = v;
		}
		public void setKey(K k) {
			key = k;
		}
		public void myLeft(KVPair p) {
			myLeft = p;
		}
		public void myRight(KVPair p) {
			myRight = p;
		}
	}
}

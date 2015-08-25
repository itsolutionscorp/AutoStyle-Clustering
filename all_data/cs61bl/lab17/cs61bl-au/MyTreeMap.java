
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
    protected KVPair myRoot;
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myRoot = null;
		size = 0;
	}
	
	public MyTreeMap(KVPair k){
		myRoot = k;
		size = 1;
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
	
	public boolean containsKeyHelper(KVPair pair, K k) {
    	if (pair == null) return false;
		if (pair.key.compareTo(k) == 0) return true;
		if (pair.myLeft != null) return containsKeyHelper(pair.myLeft, k);
		if (pair.myRight != null) return containsKeyHelper(pair.myRight, k);
		else return false;
	}
	
	public boolean containsKey(K key) {
		// TODO Complete this!
		return containsKeyHelper(myRoot, key);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	private KVPair put(KVPair pair, K key, V value) {
        if (pair == null) {
            return new KVPair(key, value);
        } else if (key.compareTo(pair.key) > 0) {
            pair.myLeft = put(pair.myLeft, key, value);
            return pair;
        } else if (key.compareTo(pair.key) == 0) {
        	pair.value = value;
            return pair;
        }
        	else {
            pair.myRight = put(pair.myRight, key, value);
            return pair;
        }
    }
	
	public V put(K key, V value) {
		// TODO Complete this!
		if (containsKey(key)){
			KVPair temp = myRoot.getHelper(key);
			V vTemp = temp.value;
			temp.setValue(value);
			return vTemp;
		}
		else {
		    size++;
			myRoot = put(myRoot, key, value);
		    return null;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (containsKey(key)){
			KVPair temp = myRoot.getHelper(key);
			V tValue = temp.value;
			if (temp.myRight == null && temp.myLeft == null) { // the leaf or has only one child
				if (temp == myRoot) {
					myRoot = null;
					return tValue;
				}
				KVPair parent = myRoot.getParent(key);
				if (parent.myLeft == temp) parent.myLeft = null;
				else if (parent.myRight == temp) parent.myRight = null;
				return tValue;
			}
			if ((temp.myRight == null && temp.myLeft != null && temp.myLeft.myLeft == null && temp.myLeft.myRight == null) 
				||(temp.myLeft == null && temp.myRight != null && temp.myRight.myLeft == null && temp.myRight.myRight == null)){
				if (temp.myLeft != null) {
					System.out.println(tValue);
					temp.value = temp.myLeft.value;
					temp.myLeft = null;
					return tValue;
				}
				else if (temp.myRight != null) {
					temp.value = temp.myRight.value;
					temp.myRight = null;
					return tValue;
				}
			}
			
			if (temp.myRight == null){
				if (temp == myRoot) {
					myRoot = myRoot.myLeft;
					return tValue;
				}
				KVPair parent = myRoot.getParent(key);
				if (parent.myLeft == temp) parent.myLeft = temp.myLeft;
				else if (parent.myRight == temp) parent.myRight = temp.myLeft;
				return tValue;
			}
			KVPair insuc = temp.myRight.inorderSuc();
			V insucValue = insuc.value;
			K insucKey = insuc.key;
			insuc.value = temp.value;
			insuc.key = temp.key;
			temp.value = insucValue;
			temp.key = insucKey;
			return remove(key);
		}
		else return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (containsKey(key)){
		    KVPair rtn = myRoot.getHelper(key);
			return rtn.value;
		}
		else return null;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	public class KVPair {
		public K key;
		public V value;
		public KVPair myLeft;
		public KVPair myRight;

		public KVPair(K k, V v) {
			key = k;
			value = v;
			myLeft = null;
			myRight = null;
		}
		

		public void setValue(V v) {
			value = v;
		}
		
		
		public KVPair getHelper(K k){
			System.out.println("get "+ key.compareTo(k));
			if (this.key.compareTo(k) == 0) {
				return this;
			}
			if (myLeft != null) return myLeft.getHelper(k);
			if (myRight != null) return myRight.getHelper(k);
			return null;
		}
		
		public KVPair getParent(K k){
			if ((myLeft != null && myLeft.key.compareTo(k) == 0) 
					|| (myRight != null && myRight.key.compareTo(k) == 0)) return this;
			if (myLeft.myLeft != null || myLeft.myRight != null) myLeft.getParent(k); 
		    if (myRight.myLeft != null || myRight.myRight != null) myRight.getParent(k);
		    return null;
		}
		
		public KVPair inorderSuc(){
			if (myLeft == null) return this;
			return myLeft.inorderSuc();
		}
		
	}
	
	
	
	public static void main(String[] args){
		MyTreeMap<Integer, String> map = new MyTreeMap<Integer, String>();
		System.out.println("---------");
		map.put(6, "tom");
		System.out.println("---------");
		map.put(4, "mary");
		System.out.println("---------");
		map.put(50, "eaha");
		System.out.println("---------");
		map.put(1, "kso");
		map.myRoot.printPreorder();
		System.out.println(map.containsKey(50) + "====");
		System.out.println(map.get(50));
		System.out.println(map.size());
		System.out.println(map.remove(10));
		System.out.println(map.size());
		map.myRoot.printPreorder();


	}
}


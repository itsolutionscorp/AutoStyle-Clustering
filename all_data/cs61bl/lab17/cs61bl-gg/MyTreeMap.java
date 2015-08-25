
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private Entry myMap;
	
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	
	public MyTreeMap() {
		myMap = null;
		this.size = 0;
	}
	
	public MyTreeMap(KVPair p) {
		myMap = new Entry(p);
		this.size = 1;
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
		return this.contains(key);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		KVPair entry = new KVPair(key, value);
		size++;
		V result = null;
		if (this.containsKey(key)) {
			result = this.get(key);
			size--;
		}
		this.add(entry);
		return result;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V result = null;
		if (this.containsKey(key)) {
			result = this.get(key);
			myMap = myMap.removeHelper(key);
			size--;
		}
		return result;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		V result = null;
		if (this.contains(key)){
			result = myMap.find(key);
		}
		return result;
	}
	
	public void add(KVPair item) {
			myMap = add(myMap, item);
	}
	
	public void printInorder() {
        if (myMap == null) {
            System.out.println("(empty tree)");
        } else {
            myMap.printInorder();
            System.out.println();
        }
    }

	private Entry add(Entry e, KVPair item) {
	    if (e == null)
	       return new Entry(item);
	    if(item.key.compareTo(e.myItem.key) == 0){
	    	e.myItem.setValue(item.value);
	    	return e;
	    }
	    else if (item.key.compareTo(e.myItem.key) < 0) {
	        e.myLeft = add(e.myLeft, item);
	        return e;
	    } else {
	        e.myRight = add(e.myRight, item);
	        return e;
	    }
	}
	
	public boolean contains(K key) {
		return contains(this.myMap, key);
	}
	
	private boolean contains (Entry e, K key) {
		if (e == null)
			return false;
		if (key.compareTo(e.myItem.key) == 0)
			return true;
		if (key.compareTo(e.myItem.key) < 0)
			return contains(e.myLeft, key);
		if (key.compareTo(e.myItem.key) > 0)
			return contains(e.myRight, key);
		return false;
	}
	
	public class Entry {
		public Entry myRight;
		public Entry myLeft;
		public KVPair myItem;
		
		public Entry(KVPair item) {
			myItem = item;
			myRight = myLeft = null;
		}
		
		public Entry(KVPair item, Entry left, Entry right) {
			myItem = item;
			myRight = right;
			myLeft = left;
		}
		
		public V find(K key) {
			if(myLeft == null && myRight == null){
				if (myItem.key.compareTo(key) == 0)
					return myItem.value;
				else
					return null;
			}
			if (myItem.key.compareTo(key) == 0) {
				return myItem.value;
			}
			if (myItem.key.compareTo(key) > 0) {
				if(myLeft != null)
					return myLeft.find(key);
				return null;
			}
			else {
				if(myRight != null)
					return myRight.find(key);
				return null;
			}
		}
		
		public Entry findEntry(K key){
			if (myItem.key.compareTo(key) == 0)
				return this;
			else if (myItem.key.compareTo(key) <0)
				return this.myLeft.findEntry(key);
			else
				return this.myRight.findEntry(key);
		}
		
		private Entry removeHelper(K key) {
			if (myItem.key.compareTo(key) == 0) {
				if (myRight == null && myLeft == null) {
					return null;
				} else if (myRight == null) {
					return myLeft;
				} else if (myLeft == null) {
					return myRight;
				} else { // find inorder successor 
					KVPair search = myRight.returnMin();
					myMap.removeHelper(search.key);
					this.myItem = new KVPair(search.key, search.value);
					return this;
				}
			} else if (myItem.key.compareTo(key) > 0) {
				myLeft = myLeft.removeHelper(key);
				return this;
			} else {
				myRight = myRight.removeHelper(key);
				return this;
			}
		} 
		
		private KVPair returnMin() {
			Entry temp = this;
			while (temp.myLeft != null) {
				temp = temp.myLeft;
			}
			return temp.myItem;
		}
		
		private void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
        }
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 * 
	 */
	public class KVPair implements Comparable<KVPair>{
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
		
		public String toString(){
			return value.toString();
		}
		
		public int compareTo(KVPair kvp){
			return this.key.compareTo(kvp.key);
		}
	}
	
	public static void main(String[] args){
		MyTreeMap e = new MyTreeMap();
		
		e.put(5, "e");
		e.put(2, "b");
		e.put(1, "a");
		e.put(3, "c");
		e.put(4, "d");
		
		e.printInorder();
		e.remove(2);
		e.remove(4);
		e.printInorder();
		e.put(2, "b");
		e.put(4, "d");
		e.printInorder();
	}
}

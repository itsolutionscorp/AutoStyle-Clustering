/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	public static void main (String[] args) {
		MyTreeMap<Person, PhoneNumber> map = new MyTreeMap<Person, PhoneNumber>();
		Person joey = new Person("joey");
		Person alice = new Person("alice");
		Person joanna = new Person("joanna");
		Person rashmi = new Person("rashmi");
		Person lilly = new Person("lilly");
		
		PhoneNumber joeyNum = new PhoneNumber("1234567890");
		PhoneNumber aliceNum = new PhoneNumber("0987654321");
		PhoneNumber joannaNum = new PhoneNumber("3333333333");
		PhoneNumber rashmiNum = new PhoneNumber("4444444444");
		PhoneNumber lillyNum = new PhoneNumber("5555555555");
		
		map.put(joey,joeyNum);
		map.put(alice,aliceNum);
		map.put(joanna,joannaNum);
		map.put(rashmi,rashmiNum);
		map.put(lilly,lillyNum);
		
		System.out.println(map.size());
		System.out.println(map.get(joey));
		System.out.println(map.get(alice));
		System.out.println(map.get(joanna));
		System.out.println(map.get(rashmi));
	}
	
	
	
	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private KVPair myRoot;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myRoot = new KVPair(null,null);
		size = 0;
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
		if (myRoot != null) {
			return myRoot.containsKey(key);
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
		if (!myRoot.containsKey(key)) {
			size++;
		}
		if (myRoot != null) {
			myRoot.put(key,value);
		}
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V temp = get(key);
		myRoot = removeHelper(key,myRoot);
		return temp;
	}
	
	public KVPair removeHelper(K key, KVPair root) {
		if (root != null) {
			if (key.compareTo(root.key) == 0) {
				if ((root.myLeft == null) && (root.myRight == null)) {
					root.key = null;
					root.value = null;
				}
				else if ((root.myLeft != null) && (root.myRight == null)) {
					root = root.myLeft;
				}
				else if ((root.myLeft == null) && (root.myRight != null)) {
					root = root.myRight;
				}
				else {
					KVPair temp = root.myRight;
					while(temp.myLeft.myLeft != null) {
						temp = temp.myLeft;
					}
					root.value = temp.myLeft.value;
					root.key = temp.myLeft.key;
					temp.myLeft = null;	
				}
				return root;
			}
			else if (key.compareTo(root.key) < 0) {
				return removeHelper(key,root.myLeft);
			}
			else {
				return removeHelper(key,root.myRight);
			}
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (myRoot != null) {
			return myRoot.get(key);
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
		private KVPair myRight;
		private KVPair myLeft;

		public KVPair(K k, V v) {
			key = k;
			value = v;
			myRight = null;
			myLeft = null;
		}

		public void setValue(V v) {
			value = v;
		}
		
		/**
		 * Returns whether the map contains the given key.
		 */
		public boolean containsKey(K key) {
			// TODO Complete this!
			if (key.equals(this.key)) {
				return true;
			}
			if (this.myLeft != null) {
				return (this.myLeft).containsKey(key);
			}
			if (this.myRight != null) {
				return (this.myRight).containsKey(key);
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
			if ((this.key == null) && (this.value == null)) {
				this.key = key;
				this.value = value;
				return null;
			}
			if (key.compareTo(this.key) == 0) {
				V prevValue = this.value;
				this.setValue(value);
				return prevValue;
			}
			if (key.compareTo(this.key) < 0) {
				if (this.myLeft != null) {
					this.myLeft.put(key, value);
				}
				else {
					this.myLeft = new KVPair(key,value);
					return null;
				}

			}
			if (key.compareTo(this.key) > 0) {
				if (this.myRight != null) {
					this.myRight.put(key, value);
				} else {
					this.myRight = new KVPair(key,value);
					return null;
				}

			}
			return null;
		}
		
		/**
		 * Returns the value associated with the key in the map, or null if there is
		 * no such value.
		 */
		public V get(K key) {
			// TODO Complete this!
			if (key.compareTo(this.key) == 0) {
				return this.value;
			}
			else if (key.compareTo(this.key) < 0) {
				if (this.myLeft != null) {
					return this.myLeft.get(key);
				}
				return null;
			}
			else { //(key.compareTo(this.key) == 1) {
				if (this.myRight != null) {
					return this.myRight.get(key);
				}
				return null;
			}
			//return null;
		}
	}
}

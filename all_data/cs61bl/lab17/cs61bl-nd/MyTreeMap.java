/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private KVPair myHead;
	
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myHead = null; 
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
		if (key == null || myHead == null){
			return false;
		}
		if (myHead.getKey().compareTo(key) == 0){
			return true;
		}
		else{
			return myHead.containsKey(key);
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
		size++;
		if (myHead == null){
			myHead = new KVPair(key, value); 
		}
		return myHead.put(key, value);
	}	

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if(myHead == null){
			return null;
		}
		else{
			return myHead.get(key);
		}
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable<KVPair>{
		private K key;
		private V value;
		private KVPair Right;
		private KVPair Left;
		
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
		public boolean containsKey(K input){
			if (key.compareTo(input) == 0){
				return true;
			}
			if (Right == null && Left == null){
				return false;
			}
			if (key.compareTo(input) > 0){
				return Left.containsKey(input);
			}
			else{
				return Right.containsKey(input);
			}
		}
		public V get(K input){
			if (input.compareTo(key) == 0){
				return value;
			}
			if (key.compareTo(input) > 0){
				return Left.get(input);
			}
			else{
				return Right.get(input);
			}
		}
		public V put(K input, V value){
			if (input.compareTo(key) == 0){
				V temp = value;
				setValue(value);
				return temp;
			}
			if (input.compareTo(key) > 0){
				if(Left != null){
					return Left.put(input,value);
					}
				else{
					Left  = new KVPair(input, value);
					return null;
						}
					}
			else{
				if(Right != null){
					return Right.put(input,value);
					}
				else{
					Right  = new KVPair(input, value);
					return null;
						}
					}
				}
			
		
		
		
		public V remove(K input){
			if (input.compareTo(key) == 0){
				V temp = value;
				setValue(null);
				return temp;
			}
			if(Left == null && Right == null){
				return null;
			}
			else if (input.compareTo(key) > 0){
				return Left.put(input,  value);
			}
			else{
				return Right.put(input,value);
			}
		}

		@Override
		public int compareTo(KVPair o) {
			if(o.getKey().equals(key)){
				return 0;
			}
			else{
				return o.getKey().compareTo(key);
			}
		}
	}
}


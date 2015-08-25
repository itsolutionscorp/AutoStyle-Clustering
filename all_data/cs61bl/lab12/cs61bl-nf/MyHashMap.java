import java.util.Iterator;


/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here

	private KVPair[] arr;
	private double factor;	

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		arr = (KVPair[]) new MyHashMap.KVPair[DEFAULT_CAPACITY];
		capacity = DEFAULT_CAPACITY;		
		factor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity) {
		arr = (KVPair[]) new MyHashMap.KVPair[initialCapacity];
		capacity=initialCapacity;		
		factor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given intial capacity and the given load
	 * factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		arr = (KVPair[]) new MyHashMap.KVPair[initialCapacity];
		capacity = initialCapacity;				
		factor = loadFactor;
	} 

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the capacity of the underlying array of the map.
	 */
	public int capacity() {
		return this.capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		if (get(key) != null) return true;
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {

	for (KVPair pair: arr){
		while(pair!= null){
			if (pair.value.equals(value))
				return true;
			pair = pair.next;
		}
	}
	return false;
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 * 
	 * Note: If this method causes size / capacity to be greater than the load
	 * factor, then this method should also expand the map.
	 */
	public V put(K key, V value) {		
		int index = key.hashCode() % capacity;
		V rv = get(key);		
		KVPair temp = arr[index];
		
		if (rv == null){
			if (temp == null)
				arr[index] = new KVPair(key,value);
			else{
				while(temp.next != null){				
					temp = temp.next;	
				}
				temp.next = new KVPair(key,value);	
			}
			size++;
		}
		else{		
			while(temp != null){
				if (temp.key.equals(key))
					temp.value = value;
				temp = temp.next;	
			}
		}
		
		if (size/capacity > factor)
		    expand(size+size);// not sure the right size
		
		return rv;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int index = key.hashCode() % capacity;
		KVPair temp = arr[index];
		KVPair prev = null;
		boolean start = true;
		if (temp == null) return null;		
		
		while (temp != null){
			if (start && temp.key.equals(key)){ 
				size--;
				arr[index] = temp.next;				
				return temp.value;
			}
			else if (temp.key.equals(key)){
				size--;
				prev.next = temp.next;				
				return temp.value;				
			}
			prev = temp;
			start = false;			
			temp = temp.next;
		}		
	return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		int index = key.hashCode() % capacity;
		KVPair temp = arr[index];

		if (temp == null) return null;		

		while (temp != null){
			if (temp.key.equals(key)) 
				return temp.value;
			temp = temp.next;
		}		
		return null;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		@SuppressWarnings("unchecked")
		KVPair[] arr1 = (KVPair[]) new MyHashMap.KVPair[newCapacity];
		capacity=newCapacity;	
		
		for (KVPair pair: arr){
			
			while(pair!= null){				
				int index = pair.key.hashCode() % capacity;
				KVPair temp = arr1[index];
				if (temp == null) 
					arr1[index] = new KVPair(pair.key,pair.value);
				else{
				while (temp.next != null) 
					temp = temp.next;				
				temp.next = new KVPair(pair.key,pair.value);
				}				
			pair = pair.next;
			}
		}
		arr = arr1;
		
	}

	/**
	 * Returns an iterator over the keys of this map.
	 */
	public Iterator<K> iterator() {
		return new HashMapIterator();
	}

	/**
	 * An iterator for the keys of the enclosing map.
	 */
	private class HashMapIterator implements Iterator<K> {
		private int indexforbucket = -1;
		private KVPair position;
		
		public HashMapIterator() {
			for (int i = 0; i < capacity; i++){	
				indexforbucket = i;
				if (arr[i] != null){
					position = arr[i];					
					break;
				}	
			}			
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if ( indexforbucket == -1 || indexforbucket>=capacity) 
				return false;
			return true;
		}

		@Override
		public K next() {
			K rv = position.key;
			if (position.next == null){
				for (int i = indexforbucket+1; i < capacity; i++){					
					indexforbucket = i;
					if (arr[i] != null){
						position = arr[i];						
						break;
					}	
				}				
			}
			else
			position = position.next;
			
			return rv;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		private KVPair next;

		public K getKey(){
			return key;
		}
		public V getValue(){
			return value;
		}

		public KVPair(K key, V value){
			this.key = key;
			this.value = value;
		}
	}

}

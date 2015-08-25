import java.util.*;

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

	private LinkedList<KVPair> [] map;
	private double loadFactor;
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this.capacity = 10;
		this.size = 0;	
//		this.map =  new LinkedList<KVPair>[capacity];
		this.map = (LinkedList<KVPair> []) new LinkedList[capacity];
		this.loadFactor = 0.7;
		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		this.capacity = initialCapacity;
		this.size = 0;
		this.map = (LinkedList<KVPair> []) new LinkedList[capacity];
		this.loadFactor = 0.7;
		
	}

	/**
	 * Constructs an empty map with the given initial capacity and the given load
	 * factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		this.capacity = initialCapacity;
		this.loadFactor = loadFactor;
		this.size = 0;
		this.map = (LinkedList<KVPair> []) new LinkedList[capacity];
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
		return capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		// TODO Complete this!
		int pos = key.hashCode() % capacity;
		for (KVPair items: map[pos]){
			if (items.getKey().equals(key)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!	
		for (int i = 0; i < capacity; i++){
			if (map[i] != null){
				for (KVPair items: map[i]){
				  if (items.getValue().equals(value))
					  return true;
				}
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
		// TODO Complete this!
		size++;
		if (loadFactor * capacity <= size )
			expand(capacity * 2);
		int pos = key.hashCode() % capacity;
		if (map[pos] == null) {
			map[pos] = new LinkedList<KVPair>() ;
			map[pos].add(new KVPair(key,value));
			return null;
		}
		else {
			for (KVPair items : map[pos]){
				if (items.getKey().equals(key)){
					V temp = items.getValue();
					items.setValue(value);
					return temp;
				}
			}
			map[pos].addLast(new KVPair(key,value));
			return null;
		} 
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (!containsKey(key))
			return null;
		else {
			int pos = key.hashCode() % capacity;
			for (KVPair items : map[pos]){
				if (items.getKey().equals(key)) {
					size--;
					V temp = items.getValue();
					map[pos].remove(items);
					return temp;
				} 
			}
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (containsKey(key)){
			int pos = key.hashCode() % capacity;
			for (KVPair items: map[pos]){
				if (items.getKey().equals(key)){
					return items.getValue();
				}
			}
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
		// TODO Complete this!
		 MyHashMap tempHashMap = new MyHashMap(newCapacity);
		 for (int i = 0; i < size; i++){
			 if( map[i] != null){
				 for (KVPair items : map[i]){
					 tempHashMap.put(items.getKey(), items.getValue());
				 }
			 }
		 }
		 capacity = newCapacity;
		 map = tempHashMap.map;
		 
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

		int arrayPlace;
		int linkedListPlace;
		int count;
		boolean isLinkedList;
		
		public HashMapIterator() {
			// TODO Complete this!
			int count = 0;
			isLinkedList = false;
			arrayPlace = 0;
			linkedListPlace = 0;
			
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return count < size;
		}

		@Override
		public K next() {
			// TODO Complete this!
			if (!isLinkedList){
				for (; arrayPlace < capacity; arrayPlace++) {
					if (map[arrayPlace] != null ) {
						isLinkedList = true;
						break;
					}
				}
			}
			KVPair mypair = map[arrayPlace].get(linkedListPlace);
			count++;
			linkedListPlace++;
			if (map[arrayPlace].peekLast().equals(mypair)) {
				linkedListPlace = 0;
				arrayPlace++;
				isLinkedList = false;
			}
			return mypair.getKey();

		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

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
		public KVPair(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		public V getValue(){
			return value;
		}
		public K getKey(){
			return key;
		}
		
		public void setValue(V value){
			this.value = value;
		}
		
		public void setKey(K key){
			this.key = key;
		}
		
		public boolean equals( Object o){
			return key.equals(((KVPair) o).key);
		}
		
	}

}

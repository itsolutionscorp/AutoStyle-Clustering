import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

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
	private double lf; // load factor

	private ArrayList<LinkedList<KVPair>> a;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		size = 0;
		lf = DEFAULT_LOAD_FACTOR;
		a = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i++) {
			a.add(new LinkedList<KVPair>()); 
			// add null because on default ArrayList is null and size = 0 
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		size = 0;
		lf = DEFAULT_LOAD_FACTOR;
		a = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i++) {
			a.add(new LinkedList<KVPair>());
		}
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
		capacity = initialCapacity;
		size = 0;
		lf = loadFactor;
		a = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i = 0; i < capacity; i++) {
			a.add(new LinkedList<KVPair>());
		}
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
		// TODO Complete this!
		for (int i = 0; i < this.capacity; i++) { // goes through buckets (linked list), i.e. a.get(i) 
			for (KVPair item : a.get(i)) { // goes through each node in the bucket 
				if (key.equals(item.getKey())) {
					return true; 
				}
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
		for (int i = 0; i < this.capacity; i++) { // goes through buckets (linked list), i.e. a.get(i) 
			for (KVPair item : a.get(i)) { // goes through each node in the bucket 
				if (value.equals(item.getValue())) {
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


		int code = key.hashCode(); // intermediate step towards bucket index 
		int index = code % capacity; // we calculate the bucket index from hash code
		LinkedList l = a.get(index); // get the pointer l that points to the bucket 
		if (l == null) {
			// empty -- just add the first node
			a.get(index).add(new KVPair(key, value)); // add the first node to the empty list
			size++;
			return null;
		} else {
			// already some nodes, so search first
			KVPair toBeAdded = new KVPair(key, value);
			int containsKey = l.indexOf(toBeAdded);
			if (containsKey != -1) {
				// already there -- so replace it
				V prev_val = ((KVPair)l.get(containsKey)).getValue();
				a.get(index).set(containsKey, toBeAdded);
				return prev_val; 
			} else {
				// not there - -append it to the end
				
				a.get(index).add(toBeAdded);
				size++;
				
				
				// then check size
				double ratio = (double) ((size + 1)) / (double) capacity;
				// int desired_capacity = ((int) ((size + 1) / lf)) * 2;
				if (ratio > lf) {
					// expand
					expand(capacity * 2);
				}





				return null;
			}
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
		for (int i = 0; i < this.capacity; i++) { // goes through buckets (linked list), i.e. a.get(i)  
			KVPair toBeSearched = new KVPair(key, null);
			int containsKey = a.get(i).indexOf(toBeSearched);
			if (containsKey != -1) {
				// found the thing to be removed
				V value_save = a.get(i).get(containsKey).getValue();
				// 1) get index of Linked List 2) get Key-Value pair 3) get value of Key-Value Pair 
				a.get(i).remove(containsKey);
				size--;
				return value_save;
			}
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		int code = key.hashCode();
		int index = code % capacity;
		// LinkedList l = a.get(index);
		// Note: the above line has a bug!
		//       We don't know if key is there, so index may not make sense at all!
		if (!(index >= 0 && index < capacity)) {
			// index does not make sense!
			return null;
		}
		LinkedList l = a.get(index);
		if (l.isEmpty()) {
			// empty -- definitely no such key
			return null;
		} else {
			// already some nodes, so search
			KVPair toBeSearched = new KVPair(key, null);
			int containsKey = l.indexOf(toBeSearched);
			if (containsKey != -1) {
				// already there -- so return the value
				return ((KVPair)l.get(containsKey)).getValue();
			} else {
				// not there
				// the key passed into this method has a hash value that corresponds to
				// a non-empty bucket, but it is just a coincidence.
				// recall that two keys may have the same hash value
				return null;
			}
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		// TODO Complete this!
		MyHashMap<K, V> result = new MyHashMap<K, V>(newCapacity); 
		// Note: have to get space right between K and V
		for (int i = 0; i < this.capacity; i++) {
			for (KVPair item : a.get(i)) {
				result.put(item.getKey(), item.getValue()); 
			}
		}
		// update instance variables 
		capacity = newCapacity;
		// size is the same 
		// lf is constant 
		a = result.a; 
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

		private int iterator_arraylist_index; 
		private int iterator_linkedlist_index;  



		private void advance_for_constructor() { 
			// this advances gives you the FIRST valid pointer 
			// at this point, iterator_arraylist_index and iterator_linkedlist_index are both 0
			int i = 0; 
			for (; i < capacity; i++) {
				if (!a.get(i).isEmpty()) {
					break; 
				} 
			}
			// 2 cases: 
			// 1) i is within the range of the ArrayList, 
			// which means we have found the first non-null LinkedList
			// 2) We have been past the end of the ArrayList (now i becomes capacity)
			iterator_arraylist_index = i; 
			// Note: even if it is the 2nd case, hasNext() will handle that by returning false (see below)
			iterator_linkedlist_index = 0;
			// Note: used in constructor, so the first node is what we need to return in next()
		}

		private void advance_for_next() { 
			// this advance advances through the linkedlist and arraylist 
			// at this point, iterator_arraylist_index and iterator_linkedlist_index can be anything!
			// first, check if we are at the end of the linked list
			// if so, reset iterator_linkedlist_index to 0 and try to find the next valid linked list
			// else just increment iterator_linkedlist_index by 1
			boolean has_reached_end_of_ll = false;
			int size_of_ll = a.get(iterator_arraylist_index).size();
			has_reached_end_of_ll = (iterator_linkedlist_index >= size_of_ll - 1);
			if (has_reached_end_of_ll) {
				iterator_linkedlist_index = 0;
				int i = iterator_arraylist_index + 1; 
				for (; i < capacity; i++) {
					if (!a.get(i).isEmpty()) {
						break; 
					} 
				}
				iterator_arraylist_index = i; // i points to the next non-empty bucket
			} else {
				iterator_linkedlist_index++;
			}
		}


		public HashMapIterator() {
			// TODO Complete this!
			iterator_arraylist_index = 0; 
			iterator_linkedlist_index = 0; 
			advance_for_constructor();
		}



		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return iterator_arraylist_index < capacity;
		}

		@Override
		public K next() {
			// TODO Complete this!
			K rtn = a.get(iterator_arraylist_index).get(iterator_linkedlist_index).getKey(); 
			advance_for_next();
			return rtn;
		}

		@Override
		public void remove() {
			// not supported
			return;
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
		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			return this.key.equals(((KVPair)obj).key); 
			// Note: doesn't need to compare values because we may change the value
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
	}

}

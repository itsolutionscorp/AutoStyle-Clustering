import java.util.ArrayList;
import java.util.Iterator;
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
	private ArrayList<LinkedList<MyHashMap<K, V>.KVPair>> key_store;
	private double load_factor;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		key_store = new ArrayList<LinkedList<MyHashMap<K, V>.KVPair>>(DEFAULT_CAPACITY);
		load_factor =  DEFAULT_LOAD_FACTOR;
		for (LinkedList<MyHashMap<K, V>.KVPair> elem: key_store) {
			elem = new LinkedList<KVPair>();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		key_store = new ArrayList<LinkedList<MyHashMap<K, V>.KVPair>>(initialCapacity);
		load_factor =  DEFAULT_LOAD_FACTOR;
		for (LinkedList<MyHashMap<K, V>.KVPair> elem: key_store) {
			elem = new LinkedList<KVPair>();
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
		key_store = new ArrayList<LinkedList<MyHashMap<K, V>.KVPair>>(initialCapacity);
		load_factor = loadFactor;
		for (LinkedList<MyHashMap<K, V>.KVPair> elem: key_store) {
			elem = new LinkedList<KVPair>();
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
		int index = key.hashCode() % capacity;
		for (KVPair p: key_store.get(index)) {
			if (p.key == key) {
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
		for (LinkedList<MyHashMap<K, V>.KVPair> elem1: key_store) {
			for (KVPair elem2: elem1) {
				if (elem2.value == value) {
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
		int index = key.hashCode() % capacity;
		LinkedList<MyHashMap<K, V>.KVPair> sub = key_store.get(index) ;
		for (int i = 0; i < sub.size(); i ++) {
			if (sub.get(i).get_key() == key) {
				V old_value = sub.get(i).get_value();
				sub.get(i).set_value(value);
				return old_value;
			}
			
		}
		KVPair e = new KVPair(key, value);
		sub.add(e);
		size ++;
		return null;
		
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		int mod_key = key.hashCode() % capacity;
		LinkedList<MyHashMap<K, V>.KVPair> sub = key_store.get(mod_key);
		for (KVPair elem: sub) {
			if (elem.get_key() == key) {
				V return_val = elem.get_value();
				sub.remove(elem);
				size --;
				return return_val;
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
		int moded = key.hashCode() % capacity;
		for (KVPair elem: key_store.get(moded)) {
			if (elem.get_key() == key) {
				return elem.get_value();
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
		this.capacity = newCapacity;
		ArrayList<LinkedList<MyHashMap<K, V>.KVPair>> storer2 = key_store ;
		key_store = new ArrayList<LinkedList<MyHashMap<K, V>.KVPair>>(newCapacity);
		for (LinkedList<MyHashMap<K,V>.KVPair> elem1: storer2) {
			for (KVPair elem2: elem1) {
				this.put(elem2.get_key(), elem2.get_value());
			}
		}
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
		int curr_index;
		int curr_subindex;
		int count;
		int size2;
		
		public HashMapIterator() {
			// TODO Complete this!
			count = 0;
			curr_subindex = 0;
			curr_index = 0;
			size2 = 0;
			for (LinkedList<MyHashMap<K, V>.KVPair> elem : key_store) {
				size2 += elem.size();
			}
			
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return count < size2 ;
		}

		@Override
		public K next() {
			// TODO Complete this!
			try {
				KVPair to_return = key_store.get(curr_index).get(curr_subindex);
			}
			catch (IndexOutOfBoundsException e) {
				if (curr_index >= key_store.size()) {
					return null;
				}
				else if (curr_subindex >= key_store.get(curr_index).size()) {
					curr_subindex = 0;
					curr_index ++;
					return this.next();
				}
			}
			KVPair to_return = key_store.get(curr_index).get(curr_subindex);
			return to_return.get_key();
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
		public KVPair(K keys, V values) {
			key = keys;
			value = values;
		}
		
		public K get_key() {
			return key;
		}
		public V get_value() {
			return value;
		}
		public void set_value(V new_value) {
			value = new_value;
		}
	}

}

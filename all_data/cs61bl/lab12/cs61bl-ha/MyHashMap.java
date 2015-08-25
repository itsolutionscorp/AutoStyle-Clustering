import java.util.*;
import java.lang.Math;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size;// the number of items that have been put into the map
	
	// TODO You may declare new instance variables here
	private double loadFactor;
	private ArrayList<KVPair>[] myStorage;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		myStorage = new ArrayList[capacity];
		size = 0;
		loadFactor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		myStorage = new ArrayList[capacity];
		size = 0;
		loadFactor = DEFAULT_LOAD_FACTOR;
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
		this.loadFactor = loadFactor;
		size = 0;
		myStorage = new ArrayList[capacity];
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
		int h = Math.abs(key.hashCode() % capacity);
		ArrayList<KVPair> list = myStorage[h];
		if(list != null){
			if(!list.isEmpty()){
				for (KVPair p: list){
					if (p.getKey().equals(key)){
						return true;
					}
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
		for (ArrayList<KVPair> l:myStorage){
			if (l!= null){
				if (!l.isEmpty()){
					for(KVPair p: l){
						if(p.getValue().equals(value)){
							return true;
						}
					}
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
		double sizeCheck = (size + 1)/capacity;
		if(sizeCheck > loadFactor){
			expand(capacity * 2);
		}
		int h = Math.abs(key.hashCode() % capacity);
		V returnValue = null;
		boolean didSet = false;
		ArrayList<KVPair> list = myStorage[h];
		if (list == null){
			list = new ArrayList<KVPair>();
			list.add(new KVPair(key, value));
			myStorage[h] = list;
			size++;
		} else {
			for (KVPair p: list){
				if (p.getKey().equals(key)){
					returnValue = p.getValue();
					p.setValue(value);
					didSet = true;
					break;
				}
			}
			if (!didSet){
				KVPair kvp = new KVPair(key, value);
				list.add(kvp);
				size++;
			}
		}
		return returnValue;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int h = Math.abs(key.hashCode() % capacity);
		ArrayList<KVPair> list = myStorage[h];
		if(list != null){
			for (int i = 0; i < list.size(); i++){
				if(list.get(i).getKey().equals(key)){
					size--;
					return list.remove(i).getValue();
				}
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
		int h = Math.abs(key.hashCode() % capacity);
		ArrayList<KVPair> list = myStorage[h];
		if(list != null){
			for (KVPair p: list){
				if(p!=null){
					if(p.getKey().equals(key)){
						return p.getValue();
					}
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
		capacity = newCapacity;
		ArrayList<KVPair>[] currentStorage = myStorage;
		myStorage = new ArrayList[capacity];
		for (ArrayList<KVPair> l: currentStorage){
			if(l != null){
				for (KVPair kvp: l){
					put(kvp.getKey(), kvp.getValue());
				}
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

		private int index, myCount, arrayCount;
		public HashMapIterator() {
			index = 0;
			myCount = 0;
		}

		@Override
		public boolean hasNext() {
			
			return myCount < size();
		}

		@Override
		public K next() {
			while(myStorage[index] == null){
				index++;
			}
			K returnKey = myStorage[index].get(arrayCount).getKey();
			arrayCount++;
			myCount++;
			if(arrayCount >= myStorage[index].size()){
				arrayCount = 0;
				index++;
			}
			return returnKey;
		}
		
		@Override
		public void remove(){
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
		
		private KVPair(K k, V val){
			key = k;
			value = val;
		}
		
		private K getKey(){
			return key;
		}
		
		private V getValue(){
			return value;
		}
		
		private void setValue(V newVal){
			value = newVal;
		}
		
	}

}

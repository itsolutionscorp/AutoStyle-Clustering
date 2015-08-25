
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

	// TODO You may declare new instance variables here
	private LinkedList<KVPair>[] stuff;
	private double load;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		stuff = new LinkedList[capacity];
		load = DEFAULT_LOAD_FACTOR;
		for (int i = 0; i<capacity; i++){
			stuff[i]=new LinkedList<KVPair>();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity= initialCapacity;
		load = DEFAULT_LOAD_FACTOR;
		stuff = new LinkedList[capacity];
		for (int i = 0; i<capacity; i++){
			stuff[i]=new LinkedList<KVPair>();
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
		stuff = new LinkedList[initialCapacity];
		load = loadFactor;
		capacity = initialCapacity;
		for (int i = 0; i<capacity; i++){
			stuff[i]=new LinkedList<KVPair>();
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
		int el = Math.abs(key.hashCode() % capacity);
		LinkedList lst = stuff[el];
		Iterator iter = lst.iterator();
		while (iter.hasNext()){
			if (((KVPair)iter.next()).key().equals(key)){
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
		int h = Math.abs(key.hashCode() % capacity);    //hashcode % capacity
		V val = null;
		//check through the linkedlist if key is present, if it is, then change the kvpair and return the old value
		if (stuff[h].isEmpty()){
			stuff[h].addFirst(new KVPair(key, value));
			this.size++;
		} else {
			if (this.containsKey(key)){
				Iterator iter = stuff[h].iterator();
				while (iter.hasNext()){
					KVPair curr = (KVPair)iter.next();
					if (curr.key().equals(key)){
						val = curr.value();
						curr.value = value;
						break;
					}
				}
			} else {
				stuff[h].addFirst(new KVPair(key, value));
				size++;
			}	
		}
		if ((size*10/capacity) >= load*10) {
			this.expand(capacity+capacity);
		}
		//System.out.println("Should have put" + value);
		return val;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		int h = Math.abs(key.hashCode() % capacity);
		Iterator iter = stuff[h].iterator();
		while (iter.hasNext()){
			KVPair curr = (KVPair)iter.next();
			if (curr.key().equals(key)){
				iter.remove();
				return curr.value();
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
		int h = Math.abs(key.hashCode() % capacity);
		Iterator iter = stuff[h].iterator();
		while (iter.hasNext()){
			KVPair curr = (KVPair)iter.next();
			if (curr.key().equals(key)){
				return curr.value();
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
		//System.out.println("Expand started");
		LinkedList<KVPair>[] temp = new LinkedList[newCapacity];
		//System.out.println(temp);
		for (int i = 0; i<newCapacity; i++){
			temp[i] = new LinkedList<KVPair>();
			//System.out.println(i);
			//System.out.println(temp[i]);
		}
		Iterator<K> iter = this.iterator();
		while (iter.hasNext()){
			K curr = iter.next();
			V val = this.get(curr);
			temp[Math.abs(curr.hashCode() % newCapacity)].addFirst(new KVPair(curr, val));
			//System.out.println(temp[curr.hashCode() % newCapacity].getFirst());
		}
		//System.out.println(stuff);
		capacity = newCapacity;
		this.stuff=temp;
		//System.out.println(capacity);
		//System.out.println(stuff);
		
		
		//make a new linkedlist[] with a new length of newcapacity
		//take every single KVPair and reassign according to the new key.hashcode%capacity value
			//use the iterator to do this
		//set stuff to equal the new linkedlist array
		/*LinkedList[] newStuff = new LinkedList[newCapacity];
		Iterator iter = new Iterator<K> iterator();
		while (iter.hasNext()){
			K key = iter.next();
			int h = key.hashCode() % capacity;      //hashcode % capacity
			stuff[h].addFirst(new));
		}*/
		/*
		LinkedList<KVPair>[] newStuff = new LinkedList[newCapacity];
		for (int i = 0; i<newCapacity; i++){
			newStuff[i]=new LinkedList<KVPair>();
		}
		Iterator<K> iter = this.iterator();
		while (iter.hasNext()){
			K currKey = iter.next();
			//hashcode it and add it to newStuff
			newStuff[currKey.hashCode() % newCapacity].addFirst(new KVPair(currKey, this.get(currKey)));//KVPair that we want
		}
		this.stuff = newStuff;
		this.capacity = newCapacity;*/
		/*MyHashMap<K, V> newMap = new MyHashMap<K, V>(newCapacity, load);
		Iterator<K> iter = this.iterator();
		while (iter.hasNext()){
			K curr =iter.next();
			V val = this.get(curr);
			newMap.put(curr, val);
		}*/
		
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

		int count = 0;
		int index = 0;
		int linkIndex = 0;
		
		public HashMapIterator() {
			// TODO Complete this!
			count = 0;
			index = 0;
			linkIndex = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if (size == count){
				return false;
			}
			return true;
		}

		@Override
		public K next() {
			// TODO Complete this!
			count++;
			if (linkIndex == stuff[index].size()){
				index++;
				linkIndex = 0;
			}
			if (stuff[index].isEmpty()){
				linkIndex=0;
				while (stuff[index].isEmpty()){
					index++;
				}
			}
			linkIndex++;
			return  (stuff[index]).get(linkIndex-1).key();
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
		public KVPair(K myKey, V myValue){
			key = myKey;
			value = myValue;
		}
		
		public K key(){
			return key;
		}
		
		public V value(){
			return value;
		}
	}

}

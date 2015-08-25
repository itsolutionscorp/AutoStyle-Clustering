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
	private LinkedList[] myTable;
	private double loadFactor;
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this.myTable = new LinkedList[DEFAULT_CAPACITY];
		this.loadFactor = DEFAULT_LOAD_FACTOR;	
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		this.capacity = initialCapacity;
		this.myTable = new LinkedList[capacity];
		this.loadFactor = DEFAULT_LOAD_FACTOR;	
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
		this.myTable = new LinkedList[initialCapacity];
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
		System.out.println(key.hashCode());
		int i = Math.abs(key.hashCode()%capacity);
		System.out.println(i);
		
		
		if (myTable[i] != null){
			int listSize = myTable[i].size();
			for(int j=0; j<listSize;j++){
				KVPair currentNode = (KVPair) myTable[i].get(j);
				if(currentNode.getKey().equals(key)){
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
		for(int i=0; i<capacity; i++){
			int listSize = myTable[i].size();
			LinkedList currentList = myTable[i];
			for(int j=0; j<listSize; j++){
				KVPair currentNode = (KVPair) currentList.get(j);
				if(currentNode.getValue().equals(value)){
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
		int hashValue = key.hashCode();
		
		V toReturn = null;
		
		if(this.containsKey(key)){
			toReturn = remove(key);
		}
		
		KVPair toEnter = new KVPair(key,value);
		if(myTable[Math.abs(hashValue%capacity)] == null){
			myTable[Math.abs(hashValue%capacity)] = new LinkedList();
		}
		myTable[Math.abs(hashValue%capacity)].addLast(toEnter);
		size++;
		
		
		double loadCompare = (double)size/(double)capacity;
		
		System.out.println("Size: "+ size);
		System.out.println("Capacity: " + capacity);
		System.out.println("Size/Capacity: " + loadCompare);
		
		if(loadCompare > loadFactor){
			this.expand(2*capacity);
		}
		return toReturn;
		//CALL EXAPAND BEFORE RETURNING
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		V tempValue = null;
		int hashValue = key.hashCode();
		
		LinkedList thisList = myTable[Math.abs(hashValue%capacity)];
		int listSize = thisList.size();
		for(int j=0; j<listSize;j++){
			KVPair currentNode = (KVPair) thisList.get(j);
			if(currentNode.getKey().equals(key)){
				tempValue = currentNode.getValue();
				thisList.remove(j);
				size--;
			}
		}
		
		return tempValue;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		V tempValue = null;
		int hashValue = key.hashCode();
		
		if(this.containsKey(key)){
			LinkedList thisList = myTable[Math.abs(hashValue%capacity)];
			int listSize = thisList.size();
			for(int j=0; j<listSize;j++){
				KVPair currentNode = (KVPair) thisList.get(j);
				if(currentNode.getKey().equals(key)){
					tempValue = currentNode.getValue();
				}
			}
		}
		
		return tempValue;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		// TODO Complete this!
		int prevCapacity = capacity;
		capacity = newCapacity;
		LinkedList[] prevTable = myTable;
		myTable = new LinkedList[newCapacity];
		for(int i = 0; i<prevCapacity; i++){
			myTable[i] = prevTable[i];
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

		int myTableIndex;
		int linkedListIndex;
		KVPair head;
		int tableIterCount;
		
		public HashMapIterator() {
			// TODO Complete this!
			myTableIndex = 0;
			while(myTable[myTableIndex]==null){
				myTableIndex = (myTableIndex + 1) % capacity;
			}
			linkedListIndex = 0;
			tableIterCount = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return tableIterCount<capacity;
		}

		@Override
		public K next() {
			// TODO Complete this!
			System.out.println("LinkedListIndex: " + linkedListIndex);
			System.out.println("myTableIndex: " + myTableIndex);
			KVPair tempKVPair = (KVPair) myTable[myTableIndex].get(linkedListIndex);
			K tempKey = tempKVPair.getKey();
			if(myTable[myTableIndex].size()>linkedListIndex+1){
				linkedListIndex++;
			}else{
				myTableIndex = (myTableIndex + 1) % capacity;
				tableIterCount++;
				while(myTable[myTableIndex]==null){
					myTableIndex = (myTableIndex + 1) % capacity;
					tableIterCount++;
				}
				linkedListIndex = 0;
			}
			return tempKey;
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
		
		public KVPair (K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey(){
			return key;
		}
		
		public V getValue(){
			return value;
		}
		
	}
	
	public void main(String[] args){
		
		
		
	}

}

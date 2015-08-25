import java.util.ArrayList;
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
	private KVPair[] myArr;
	private double myLoadFactor;
	// TODO You may declare new instance variables here
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		myArr = new KVPair[capacity];
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity =  initialCapacity;
		myArr = new KVPair[capacity];
		size = 0;
		myLoadFactor = DEFAULT_LOAD_FACTOR;

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
		
		capacity =  initialCapacity;
		myArr = new KVPair[capacity];
		myLoadFactor = loadFactor;
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
		int h = key.hashCode() % capacity;
		
		for (int i = h; i != h-1;) {
			
			if(myArr[i]!= null && myArr[i].getKey().equals(key)) return true;
			i++;
			if(h==0 && i == capacity) {
				break;
			}
			if (i == capacity) {
				i = 0;
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
		for (int i = 0; i < capacity; i++) {
			if(myArr[i]!= null && myArr[i].getValue().equals(value)) return true;
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
		V temp = null;
		int h = key.hashCode() % capacity;
		for (int i = h; i != h-1; i++) {
			
			if (myArr[i] == null) {
				double tester = (double) (size+1)/capacity;
				if (tester > myLoadFactor){
					expand(capacity*2);

				}
				myArr[i] =  new KVPair<K, V>(key, value);
				size++;
				
				
				break;
			}
			else if (myArr[i].getKey().equals(key)){
				temp = (V) myArr[i].getValue();
				myArr[i].setValue(value);
				break;
				
			}
			if (i == capacity - 1) {
				i = -1;
			}



		}
		return temp;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		V temp = null;
		int h = key.hashCode() % capacity;
		for (int i = h; i != h-1;) {
			if (myArr[i]!=null && myArr[i].getKey().equals(key)){
				temp = (V) myArr[i].getValue();
				myArr[i] = null;
			}
			i++;
			if(h==0 && i == capacity) {
				break;
			}
			if (i == capacity ) {
				i = 0;
			}
		}
		
		
		return temp;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		int h = key.hashCode() % capacity;
		for (int i = h; i != h-1; i++) {
			if(myArr[i].getKey().equals(key)){
				return (V)myArr[i].getValue();
			}
			if (i == capacity - 1) {
				i = -1;
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
	public void expand(int newCapacity) {
		// TODO Complete this!
		if (capacity > newCapacity) return;
		MyHashMap<K, V> t = new MyHashMap<K, V>(newCapacity, myLoadFactor);
		for(int j = 0; j < capacity ; j++) {
			if(myArr[j]!=null){
				t.put((K)myArr[j].getKey(), (V)myArr[j].getValue());
			}
		}
		myArr = t.myArr;
		capacity = newCapacity;
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
		int nextIndex;
		public HashMapIterator() {
			// TODO Complete this!
			nextIndex = capacity;
			for(int i = 0; i < capacity; i++) {
				if(myArr[i] != null) {
					nextIndex = i;
					break;
				}
			}
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			System.out.println("next "+ nextIndex);
			if (nextIndex == capacity) return false;
			return true;
		}

		@Override
		public K next() {
			// TODO Complete this!
			int temp = nextIndex;

			boolean checked = false;
			for(int i = nextIndex + 1; i < capacity; i++) {
				if(myArr[i] != null) {
					nextIndex = i;
					checked = true;
					break;
				}
			}
			if(!checked) nextIndex = capacity;
			return (K) myArr[temp].getKey();
		}
		
		public void remove(){}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair<L,R> {
		  private L left;
		  private R right;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */

		  public KVPair(L left, R right) {
		    this.left = left;
		    this.right = right;
		  }

		  public L getKey() { return left; }
		  public R getValue() { return right; }
		  public void setKey(L key) {
			  left = key;
		  }
		  public void setValue(R value) {
			  right = value;
		  }

		  @Override
		  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

		  @Override
		  public boolean equals(Object o) {
		    if (!(o instanceof KVPair)) return false;
		    KVPair pairo = (KVPair) o;
		    return this.left.equals(pairo.getKey()) &&
		           this.right.equals(pairo.getValue());
		  }
		
	}

}

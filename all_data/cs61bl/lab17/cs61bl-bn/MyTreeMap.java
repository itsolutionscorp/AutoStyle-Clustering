/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private KVPair myRoot;

	public static void main(String[] ars) {
		MyTreeMap<Integer, String> m = new MyTreeMap<Integer, String>();
		m.put(50, "default");
		m.put(30, "333");
		m.put(20, "222");
		m.put(10, "111");
		m.put(40, "444");
		m.put(35, "3535");
		m.put(34, "34");
		m.put(32, "32");
		m.put(33, "3333");
		m.put(80, "888");
		m.put(75, "7474");
		m.put(90, "999");
		m.put(100, "101010");
		System.out.println(m.size());
		System.out.println(m.remove(80));
		System.out.println(m.remove(75));
		System.out.println(m.size());

	}

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		size = 0;
		myRoot = null;

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
	 * 
	 * EIFU I AM ALMOST CERTAIN THIS METHOD NEEDS WORK
	 */
	public boolean contains(K key) {
		MyTreeMap<K, V>.KVPair pointer = myRoot;
		if (key == null || myRoot == null) {
			return false;
		}
		// if (myRoot.myLeft == null && myRoot.myRight == null) {
		// return key.compareTo(pointer.key) == 0;
		// }
		while (pointer != null) {

			if (key.compareTo(pointer.key) < 0) {
				pointer = pointer.myLeft;
			} else if (key.compareTo(pointer.key) == 0) {
				return true;
			} else {
				pointer = pointer.myRight;
			}
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
		if (myRoot == null) {
			myRoot = new KVPair(key, value);
			return null;
		}

		MyTreeMap<K, V>.KVPair pointer = myRoot;
		MyTreeMap<K, V>.KVPair pointerParent = null;
		while (pointer != null) {
			if (key.compareTo(pointer.key) < 0) {
				pointerParent = pointer;
				pointer = pointer.myLeft;
			} else if (key.compareTo(pointer.key) == 0) {

				V temp = pointer.getValue();
				pointer.setValue(value);
				size++;
				return temp;

			} else if (key.compareTo(pointer.key) > 0) {
				pointerParent = pointer;
				pointer = pointer.myRight;

			}
		}
		if (key.compareTo(pointerParent.key) < 0) {
			pointerParent.setlr(new KVPair(key, value), pointerParent.myRight);
			size++;
			return null;
		} else {
			pointerParent.setlr(pointerParent.myLeft, new KVPair(key, value));
			size++;
			return null;
		}

	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (myRoot == null) {
			return null;
		}
		MyTreeMap<K, V>.KVPair pointer = myRoot;
		MyTreeMap<K, V>.KVPair pointerParent = null;
		if (myRoot.myLeft == null && myRoot.myRight == null) {
			if (myRoot.key.equals(key)) {
				V temp = myRoot.value;
				myRoot = null;
				size--;
				return temp;
			} else {
				return null;
			}
		} else if (myRoot.myLeft != null && myRoot.myRight == null) {
			if (myRoot.key.equals(key)) {
				V temp = myRoot.value;
				myRoot = myRoot.myLeft;
				size--;
				return temp;
			}
		} else if (myRoot.myLeft == null && myRoot.myRight != null) {
			if (myRoot.key.equals(key)) {
				V temp = myRoot.value;
				myRoot = myRoot.myRight;
				size--;
				return temp;
			}
		}

		while (pointer != null) {
			if (key.compareTo(pointer.key) < 0) {
				pointerParent = pointer;
				pointer = pointer.myLeft;
			} else if (key.compareTo(pointer.key) == 0) {
				V temp = pointer.value;
				if (pointer.myLeft == null && pointer.myRight == null) {
					if (pointer.key.compareTo(pointerParent.key) < 0) {
						pointerParent.setlr(null, pointerParent.myRight);
					} else {
						pointerParent.setlr(pointerParent.myLeft, null);
					}

				} else if (pointer.myLeft == null && pointer.myRight != null) {
					if (pointer.key.compareTo(pointerParent.key) < 0) {
						pointerParent.setlr(pointer.myRight,
								pointerParent.myRight);

					} else {
						pointerParent.setlr(pointerParent.myLeft,
								pointer.myRight);
					}
				} else if (pointer.myLeft != null && pointer.myRight == null) {
					if (pointer.key.compareTo(pointerParent.key) < 0) {
						pointerParent.setlr(pointer.myLeft,
								pointerParent.myRight);
					} else {
						pointerParent.setlr(pointerParent.myLeft,
								pointer.myLeft);
					}
				} else {
					MyTreeMap<K, V>.KVPair justRight = pointer.myRight;
					MyTreeMap<K, V>.KVPair farLeft = justRight;
					MyTreeMap<K, V>.KVPair farLeftParent = null;

					while (farLeft.myLeft != null) {
						farLeftParent = farLeft;
						farLeft = farLeft.myLeft;
					}
					if (farLeft.equals(justRight)) {
						farLeft = new KVPair(farLeft.key, farLeft.value,
								pointer.myLeft, farLeft.myRight);
						if (pointer.equals(myRoot)) {
							farLeft.setlr(pointer.myLeft, farLeft.myRight);
							temp = pointer.value;
							myRoot = farLeft;
							size--;
							return temp;
						}
						if (pointer.key.compareTo(pointerParent.key) < 0) {
							pointerParent.setlr(farLeft, pointerParent.myRight);
						} else {
							pointerParent.setlr(pointerParent.myLeft, farLeft);
						}

					} else {

						if (farLeft.myRight == null) {
							farLeftParent.setlr(null, farLeftParent.myRight);
						} else {
							farLeftParent.setlr(farLeft.myRight,
									farLeftParent.myRight);
						}
						farLeft.setlr(pointer.myLeft, justRight);

						if (pointer.key.compareTo(pointerParent.key) < 0) {
							pointerParent.setlr(farLeft, pointerParent.myRight);
						} else {
							pointerParent.setlr(pointerParent.myLeft, farLeft);
						}
					}

				}
				size--;
				return temp;

			} else if (key.compareTo(pointer.key) > 0) {
				pointerParent = pointer;
				pointer = pointer.myRight;
			}
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (myRoot == null) {
			return null;
		}
		MyTreeMap<K, V>.KVPair pointer = myRoot;
		while (pointer != null) {
			if (key.compareTo(pointer.key) < 0) {
				pointer = pointer.myLeft;
			} else if (key.compareTo(pointer.key) == 0) {
				return pointer.value;

			} else if (key.compareTo(pointer.key) > 0) {
				pointer = pointer.myRight;

			}
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
		private KVPair myLeft;
		private KVPair myRight;

		// when given a key and value
		public KVPair(K k, V v, KVPair left, KVPair right) {
			key = k;
			value = v;
			myLeft = left;
			myRight = right;
		}

		// when @param left blank, sets key and value to null, which can be
		// changed later.
		public KVPair(K k, V v) {
			key = k;
			value = v;
			myLeft = null;
			myRight = null;
		}

		public void setlr(KVPair l, KVPair r) {
			myLeft = l;
			myRight = r;
		}

		public void setValue(V v) {
			value = v;
		}

		public V getValue() {
			return value;
		}
	}
}

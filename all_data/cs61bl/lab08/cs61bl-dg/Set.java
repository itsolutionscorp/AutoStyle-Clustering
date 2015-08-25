public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement.

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int max;
    private int index;

	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
		max = maxElement;
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if (k>=0 && k < max) {
			contains[k] = true;
		}
	}

	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if (k>=0 && k < max) {
			contains[k] = false;
		}
	}

	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if (k>=0 && k < max) {
			return contains[k];
		}
		return false;
	}

	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for(boolean value:contains){
			if (value){
				return false;
			}
		}
		return true;
	}

    public void initIterator() {
        index = 0;
    }

    public int next() {
        while (!member(index)) {
            index++;
        }
        return index;
    }

    public boolean hasNext() {
        int check = index;
        while (!member(check)) {
            check++;
            if (check == max) {
                return false;
            }
        }
        return true;
    }

}

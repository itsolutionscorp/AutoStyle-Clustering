public class Set {
	
	private int nextIndexToReturn;
	private boolean[] contains;
	
	public Set (int maxElement) {
		contains = new boolean[maxElement];
		}
	
	public void insert (int k) {
		contains[k] = true;
	}
	
	public void remove (int k) {
		contains[k] = false;
	}
	
	public boolean member (int k) {
		if (contains[k] == true) {
			return true;
		}
		return false;			
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < contains.length ; i++) {
			if (contains[i] == true) {
				return false;
			}
		}
		return true;
			}
	
	public void initIterator() {
        nextIndexToReturn = 0;
    }

    public int next() {
    	while (nextIndexToReturn < contains.length) {
			if (member(nextIndexToReturn)) {
				int valToReturn = nextIndexToReturn;
				nextIndexToReturn++;
				return valToReturn;
			}
			nextIndexToReturn++;
    	}
    	return -1;
    }

    public boolean hasNext() {
    	if (nextIndexToReturn > contains.length) {
    		return false;
    	}
    	else {
    		int i = nextIndexToReturn;
    		while (i < contains.length) {
    			if (member(i)) {
    				return true;
    			}
    			i++;
    		}
    		return false;
        }
    }
}

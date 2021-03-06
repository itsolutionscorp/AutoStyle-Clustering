public class Set {

    // Represent a set of nonnegative ints from 0 to maxElement-1
    // for some initially specified maxElement.

    // contains[k] is true if k is in the set, false if it isn't
    int index;
    private boolean[] contains;

    // Initialize a set of ints from 0 to maxElement-1.
    public Set (int maxElement) {
        contains = new boolean[maxElement];
        for(int i = 0; i < maxElement; i++){
            contains[i] = false;
        }
    }

    // precondition: 0 <= k < maxElement.
    // postcondition: k is in this set.
    public void insert (int k) {
        if(k < 0 || k >= contains.length){
            return;
        }
        contains[k] = true;
    }

    // precondition: 0 <= k < maxElement.
    // postcondition: k is not in this set.
    public void remove (int k) {
        if(k < 0 || k >= contains.length){
            return;
        }
        contains[k] = false;
    }

    // precondition: 0 <= k < maxElement
    // Return true if k is in this set, false otherwise.
    public boolean member (int k) {
        if(k < 0 || k >= contains.length){
            System.out.println("invalid input");
            return false;
        }
        return contains[k];
    }

    // Return true if this set is empty, false otherwise.
    public boolean isEmpty() {
        for(int i = 0; i < contains.length; i++) {
            if (contains[i]) {
                return false;
            }
        }
        return true;
    }

    public void initIterator() {
        index = -1;
    }

    public boolean hasNext() {
        for (int i = index + 1; i < contains.length; i++) {
            if (contains[i]) {
                return true;
            }
        }
        return false;
    }

    public int next() {
        while (index + 1 < contains.length && !contains[index + 1]) {
            index ++;
        }
        index ++;
        return index;
    }
}



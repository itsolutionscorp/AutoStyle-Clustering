import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private TreeMap<Person, LinkedList<PhoneNumber>> t = new TreeMap<Person, LinkedList<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (!t.containsKey(personToAdd)) {
    		LinkedList<PhoneNumber> l = new LinkedList<PhoneNumber>();
    		l.add(numberToAdd);
    		t.put(personToAdd, l);
    	} else {
    		LinkedList<PhoneNumber> l = t.get(personToAdd);
    		l.add(numberToAdd);
        	t.put(personToAdd, l);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return t.get(personToLookup);
    }

}

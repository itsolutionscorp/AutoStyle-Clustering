import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> myTree = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (myTree.containsKey(personToAdd)) {
    		myTree.get(personToAdd).add(numberToAdd);
    	}
    	else {
        	myTree.put(personToAdd, new ArrayList<PhoneNumber>());
        	myTree.get(personToAdd).add(numberToAdd);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	if (personToLookup.hasChangedName()) {
    		myTree.put(personToLookup, null);
    	}
    	return myTree.get(personToLookup);
    }

}

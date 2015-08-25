import java.util.*;

public class PhoneBook {
    
	private HashMap<Person, PhoneNumber> pBook = new HashMap<Person,PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	pBook.put(personToAdd, numberToAdd);
    	personToAdd.isAdded();
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	if (pBook.containsKey(personToLookup)) {
    		if (!personToLookup.changedName()) {
    			return pBook.get(personToLookup);
    		} else {
    			throw new IllegalArgumentException();
    		}
    	}
    	return null;
    }

}

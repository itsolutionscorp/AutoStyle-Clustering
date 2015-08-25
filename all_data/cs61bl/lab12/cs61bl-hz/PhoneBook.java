import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	HashMap<String, PhoneNumber> PhoneBook = new HashMap<String, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	PhoneBook.put(personToAdd.name(), numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	return PhoneBook.get(personToLookup.name());
    }

}

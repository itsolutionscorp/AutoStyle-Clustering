import java.util.*;
import java.util.HashMap;

public class PhoneBook {
    // TODO Add any instance variables necessary
	Object person; 
	Object phoneNumber; 
	HashMap<Person,PhoneNumber> book; 
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (book == null) {
    		book = new HashMap<Person,PhoneNumber>(); 
    	}
    	book.put(personToAdd, numberToAdd); 
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	if (!book.containsKey(personToLookup)){
    	return null;
    	}
    	return book.get(personToLookup); 
    }
}

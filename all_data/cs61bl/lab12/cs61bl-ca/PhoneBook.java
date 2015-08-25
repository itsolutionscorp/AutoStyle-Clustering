import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private HashMap<Person, PhoneNumber> book = new HashMap<Person, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	book.put(personToAdd, numberToAdd);
    	
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	return book.get(personToLookup);
    }

}

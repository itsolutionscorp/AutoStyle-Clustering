import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	Object Person;
	Object phoneNumber; 
	TreeMap<Person,PhoneNumber> book; 
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (book == null) {
    		book = new TreeMap<Person,PhoneNumber>(); 
    	}
    	book.put(personToAdd,numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	if(!book.containsKey(personToLookup)) {
    		return null; 
    	}
    	return book.get(personToLookup);
    }

}

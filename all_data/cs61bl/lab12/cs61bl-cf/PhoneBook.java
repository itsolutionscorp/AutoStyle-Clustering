import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	HashMap<Person, PhoneNumber> myBook = new HashMap<Person, PhoneNumber>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	myBook.put(personToAdd, numberToAdd);
    	// TODO Add your own code
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	return myBook.get(personToLookup);
    }
    
    

}

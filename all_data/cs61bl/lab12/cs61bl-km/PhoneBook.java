import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	
	private HashMap<Person, PhoneNumber> myPhoneBook;
	
	public PhoneBook() {
		myPhoneBook = new HashMap<Person, PhoneNumber>();
	}
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	myPhoneBook.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
        	return myPhoneBook.get(personToLookup);
    }

}

//the HashSet has nodes that point to p, and mutating p with new values of k result in HashSet still pointing to p but whose x is the last k (k = 2 in this case)
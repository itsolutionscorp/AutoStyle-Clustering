import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private TreeMap<Person, PhoneNumber> myBook;

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	myBook.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return myBook.get(personToLookup);
    }
    
    public PhoneBook() {
    	myBook = new TreeMap<Person, PhoneNumber>();
    }

}

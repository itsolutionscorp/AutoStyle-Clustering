import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

    private HashMap<Person, PhoneNumber> myNumbers;


    public PhoneBook() {
        myNumbers = new HashMap <Person, PhoneNumber>();
    }

    /*
     * Adds a person with this name to the phone book and associates
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	myNumbers.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book.
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return myNumbers.get(personToLookup);
    }

}

import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private HashMap<Person, PhoneNumber> myTable;
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	public PhoneBook() {
		myTable = new HashMap<Person, PhoneNumber>();
	}
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	myTable.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return myTable.get(personToLookup);
    }

}

import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private HashMap<Person, PhoneNumber> myBook;
	
	public PhoneBook() {
		this.myBook = new HashMap<Person, PhoneNumber>();
	}
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
    	if (personToLookup.changedName())
    		return null;
    	return myBook.get(personToLookup);
    }

}

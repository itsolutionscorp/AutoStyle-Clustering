import java.util.*;

public class PhoneBook {
    TreeMap<String, PhoneNumber> myPhoneBook = new TreeMap<String, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	System.out.println("In method");
    	myPhoneBook.put(personToAdd.toString(), numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	if (personToLookup.hasChanged()) {
    		return null;
    	} else {
    		return myPhoneBook.get(personToLookup.toString());
    	}
    }

}

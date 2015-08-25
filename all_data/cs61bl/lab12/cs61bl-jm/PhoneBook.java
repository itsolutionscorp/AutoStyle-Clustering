import java.util.*;

public class PhoneBook {
    private HashMap<String, PhoneNumber> myNumbers = new HashMap<String, PhoneNumber>();
    private HashMap<Person, String> myContacts = new HashMap<Person, String>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	myNumbers.put(personToAdd.toString(), numberToAdd);
    	myContacts.put(personToAdd, personToAdd.toString());
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	if (personToLookup.toString().equals(myContacts.get(personToLookup))) {
    		return myNumbers.get(myContacts.get(personToLookup));
    	}
    	else {
    		return null;
    	}
    }

}

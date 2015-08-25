import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	public Map<Person, List<PhoneNumber>> phoneBook = new TreeMap<Person, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (phoneBook.get(personToAdd) == null) {
    		phoneBook.put(personToAdd, new ArrayList<PhoneNumber>());
    	}
    	phoneBook.get(personToAdd).add(numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return phoneBook.get(personToLookup);
    }

}

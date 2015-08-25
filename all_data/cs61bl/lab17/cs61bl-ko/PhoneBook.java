import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

	private TreeMap<Person, List<PhoneNumber>> book = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	List<PhoneNumber> entries = book.get(personToAdd);
    	if (entries == null) {
    		List<PhoneNumber> newEntries = new ArrayList<PhoneNumber>();
    		newEntries.add(numberToAdd);
    		book.put(personToAdd, newEntries);
    	} else {
    		entries.add(numberToAdd);
    		book.put(personToAdd, entries);
    	}
    }
    	
    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return book.get(personToLookup);
    }

}

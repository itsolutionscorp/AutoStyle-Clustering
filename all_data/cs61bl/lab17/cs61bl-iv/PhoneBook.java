import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private static TreeMap<Person, List<PhoneNumber>> table = new TreeMap<Person, List<PhoneNumber>>();
	
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (table.get(personToAdd) == null) {
    		List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
    		phoneNumbers.add(numberToAdd);
    		table.put(personToAdd, phoneNumbers);
    	}
    	else {
    		List<PhoneNumber> phoneNumbers = getNumbers(personToAdd);
    		phoneNumbers.add(numberToAdd);
    		table.put(personToAdd, phoneNumbers);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	// TODO Add your own code
    	return table.get(personToLookup);
    }
}

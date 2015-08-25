import java.util.*;

public class PhoneBook {
	
    // TODO Add any instance variables necessary
	private HashMap<Person, PhoneNumber> table;
	private ArrayList<String> originalNames;
	
	public PhoneBook() {
		table = new HashMap<Person, PhoneNumber>();
	}
	
	public HashMap<Person, PhoneNumber> myEntries() {
		return table;
	}
	
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
    	// TODO Add your own code
    	table.put(personToAdd, numberToAdd);
    }
    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	if (table.containsKey(personToLookup)) {
    		return table.get(personToLookup);
    	} else {
    		throw new IllegalArgumentException();
    	}
    	
    }

}

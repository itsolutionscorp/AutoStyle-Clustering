import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private Person key; 
	private PhoneNumber value; 
	private HashMap<Person, PhoneNumber> table; 
	
	public PhoneBook() { 
    	table = new HashMap <Person, PhoneNumber>();
	}
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	key = personToAdd; 
    	value = numberToAdd; 
    	table.put(key, value);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	if(!table.containsKey(personToLookup)) {
    		return null; 
    	}
    	return table.get(personToLookup);
    }
}

import java.util.*;
import java.util.HashMap;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private String myName;
	private String myNumber;
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	HashMap<Person, PhoneNumber> table = new HashMap <Person, PhoneNumber>();
	
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	
    	table.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	
    
    	return table.get(personToLookup);
    }

}
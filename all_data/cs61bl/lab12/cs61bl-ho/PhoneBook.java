import java.util.*;

public class PhoneBook {
	private HashMap<Person, PhoneNumber> table = new HashMap <Person, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	table.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	PhoneNumber temp = table.get(personToLookup);
    	if (temp == null){
    		throw new IllegalArgumentException("The person " + personToLookup + " was not found in this PhoneBook.");
    	}
    	return temp;
    }

}

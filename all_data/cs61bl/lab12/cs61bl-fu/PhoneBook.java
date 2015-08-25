import java.util.*;

public class PhoneBook {
    
	HashMap<Person, PhoneNumber> map = new HashMap<Person, PhoneNumber> ();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	map.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return map.get(personToLookup);
    }

}

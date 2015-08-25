import java.util.*;

public class PhoneBook {
    private HashMap<Person, PhoneNumber> m = new HashMap<Person, PhoneNumber> ();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	m.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	PhoneNumber num = m.get(personToLookup);
    	return num;
    }

}

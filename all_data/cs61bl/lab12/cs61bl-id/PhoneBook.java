import java.util.*;

public class PhoneBook {
    private HashMap<String, PhoneNumber> hash;

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	hash.put(personToAdd.toString(), numberToAdd);
    }

    /*
     * Access an entry in the phone book. j
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return hash.get(personToLookup.toString());
    }
    
    public PhoneBook() {
    	hash =  new HashMap<>();
    }

}

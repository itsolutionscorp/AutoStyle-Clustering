import java.util.*;

public class PhoneBook {
    TreeMap <Person, PhoneNumber> tree; 
	
    public PhoneBook() {
		 tree = new TreeMap <Person, PhoneNumber>();
	}
    
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	tree.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return tree.get(personToLookup);
    }

}

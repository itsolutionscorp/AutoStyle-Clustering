import java.util.*;
import java.util.HashMap;




public class PhoneBook {
    // TODO Add any instance variables necessary
	
	private HashMap<Person, PhoneNumber> phoneTable = new HashMap <Person, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	
    	phoneTable.put(personToAdd, numberToAdd);

    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	PhoneNumber num = phoneTable.get(personToLookup);
    	if(num == null){
    		throw new IllegalArgumentException ("This person cannot be found in this phone book");
    	}
    	return num;
    }
    
    
    
    
    
    

}

import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> map = new TreeMap<Person, List<PhoneNumber>>(); 

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	List<PhoneNumber> numbers = null;
    	if (getNumber(personToAdd) == null) {
    		numbers = new ArrayList<PhoneNumber>();
    		numbers.add(numberToAdd);
    		
    	} else {
    		numbers = getNumber(personToAdd);
    		numbers.add(numberToAdd);
    	}
    	map.put(personToAdd, numbers);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return map.get(personToLookup);
    }

}

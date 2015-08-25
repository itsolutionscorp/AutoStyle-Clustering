import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<String, List<PhoneNumber>> book = new TreeMap<String, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (book.containsKey(personToAdd.toString())) {
	    	List<PhoneNumber> numbers = book.get(personToAdd.toString());
	    	numbers.add(numberToAdd);
    	} else {
    		List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
    		numbers.add(numberToAdd);
    		book.put(personToAdd.toString(), numbers);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return book.get(personToLookup.toString());
    }

}

import java.util.*;

public class PhoneBook {
	TreeMap<Person, List<PhoneNumber>> book = new TreeMap<Person, List<PhoneNumber>>();
	
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	List l = book.get(personToAdd);
    	if (l == null) {
    		List result = new ArrayList<PhoneNumber>();
    		result.add(numberToAdd);
    		book.put(personToAdd, result);
    	}
    	else {
    		l.add(numberToAdd);
    	}
    	
    }

    /*
     * Access an entry in the phone book. 
     */
 
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return book.get(personToLookup);
    }

}

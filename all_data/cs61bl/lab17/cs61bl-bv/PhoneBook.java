import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	
	public TreeMap<Person, List<PhoneNumber>> book = new TreeMap<Person, List<PhoneNumber>>();
	
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (book.containsKey(personToAdd)) {
    		book.get(personToAdd).add(numberToAdd);
    	} else {
    		ArrayList<PhoneNumber> new1 = new ArrayList<PhoneNumber>();
    		new1.add(numberToAdd);
    		book.put(personToAdd, new1);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	if (book.containsKey(personToLookup)) {
    		return book.get(personToLookup).get(0);
    	}
    	return null;
    }
    
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return book.get(personToLookup);
    }

}

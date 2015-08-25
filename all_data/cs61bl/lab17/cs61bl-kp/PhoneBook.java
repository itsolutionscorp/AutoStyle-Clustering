import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> book = new TreeMap<Person, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (this.book.containsKey(personToAdd)) {
    		this.book.get(personToAdd).add(numberToAdd);
    		return;
    	}
    	List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
    	numbers.add(numberToAdd);
    	this.book.put(personToAdd, numbers);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return this.book.get(personToLookup);
    }

}

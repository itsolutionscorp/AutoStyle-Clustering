import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private TreeMap<Person, List<PhoneNumber>> book = new TreeMap<Person, List<PhoneNumber>>();
	/*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	List<PhoneNumber> numlist;
    	if (!book.containsKey(personToAdd)) {
    		numlist = new ArrayList();
    		numlist.add(numberToAdd);
    		book.put(personToAdd, numlist);
    	}
    	else {
    		numlist = book.get(personToAdd);
	    	numlist.add(numberToAdd);
	    	book.put(personToAdd, numlist);

    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	List<PhoneNumber> number = book.get(personToLookup);
    	if (number == null) {
    		return null;
    	}	
    	return number;
    }

}

import java.util.HashMap;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private HashMap<Person, PhoneNumber> book;
	private HashMap<String, PhoneNumber> copy;
	
	public PhoneBook() {
		book = new HashMap<Person, PhoneNumber>(); 
		copy = new HashMap<String, PhoneNumber>();
	}
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (book.containsKey(personToAdd)) {
    		book.remove(personToAdd);
    	}
    	book.put(personToAdd, numberToAdd);
    	copy.put(personToAdd.toString(), numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	if (book.containsKey(personToLookup)) {
    		if (book.get(personToLookup).equals(copy.get(personToLookup.toString()))) {
    			return book.get(personToLookup);
    		}
    	}
    	return null;
    }

}

import java.util.HashMap;

public class PhoneBook{
    // TODO Add any instance variables necessary
	
	private HashMap<Person, PhoneNumber> phonebook = new HashMap<Person, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	phonebook.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	PhoneNumber number = phonebook.get(personToLookup);
    	if (number == null) {
    		throw new IllegalArgumentException("Person not found in this phonebook.");
    	}	
    	return number;
    }

}

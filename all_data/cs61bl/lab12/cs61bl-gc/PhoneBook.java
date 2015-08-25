import java.util.*;

public class PhoneBook {
    HashMap<String, PhoneNumber> book;
    
    public PhoneBook() {
    	book = new HashMap<String, PhoneNumber>();
    }

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	book.put(personToAdd.toString(), numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return book.get(personToLookup.toString());
    }

}

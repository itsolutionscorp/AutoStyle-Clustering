import java.util.*;

public class PhoneBook {
    private HashMap<Person, PhoneNumber> book;

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public PhoneBook(){
    	book = new HashMap<Person, PhoneNumber>();
    }
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	book.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return book.get(personToLookup);
    }

}

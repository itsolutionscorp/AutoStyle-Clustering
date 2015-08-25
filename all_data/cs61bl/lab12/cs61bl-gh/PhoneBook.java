import java.util.*;

public class PhoneBook {
    private MyHashMap<Person, PhoneNumber> phoneBook = 
    		new MyHashMap<Person, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	phoneBook.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return phoneBook.get(personToLookup);
    }

}

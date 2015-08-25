import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	MyHashMap<Person, PhoneNumber> map;
	
	
	public PhoneBook() {
		map = new MyHashMap<Person, PhoneNumber>();
	}
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	map.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return map.get(personToLookup);
    }

}

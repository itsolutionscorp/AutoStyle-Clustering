import java.util.*;

public class PhoneBook {
    TreeMap<Person, List<PhoneNumber>> myPhoneBook = new TreeMap<Person, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	List numbers = myPhoneBook.get(personToAdd);
    	if (numbers == null) {
    		List toAdd = new ArrayList<PhoneNumber>();
    		toAdd.add(numberToAdd);
    		myPhoneBook.put(personToAdd, toAdd);
    	}
    	else {
    		numbers.add(numberToAdd);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	return myPhoneBook.get(personToLookup);
    }

}

import java.util.*;

public class PhoneBook {
	private MyTreeMap<Person, List<PhoneNumber>> phoneBook = 
    		new MyTreeMap<Person, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
//    	if (phoneBook.containsKey(personToAdd)) {
//    		phoneBook.get(personToAdd).add(numberToAdd);
//    	} else {
//    		ArrayList<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
//    		numbers.add(numberToAdd);
//    		phoneBook.put(personToAdd, numbers);
//    	}
		ArrayList<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
		numbers.add(numberToAdd);
		phoneBook.put(personToAdd, numbers);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	return phoneBook.get(personToLookup);
    }
}

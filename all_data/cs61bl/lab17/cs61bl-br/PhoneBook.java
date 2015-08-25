import java.util.*;

public class PhoneBook {
	TreeMap<Person, ArrayList<PhoneNumber>> phoneB = new TreeMap<Person, ArrayList<PhoneNumber>>();

	/*
	 * Adds a person with this name to the phone book and associates with the
	 * given PhoneNumber.
	 */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (phoneB.containsKey(personToAdd)) {
    		phoneB.get(personToAdd).add(numberToAdd);
    	} else {
    		phoneB.put(personToAdd, new ArrayList<PhoneNumber>()); 
    		phoneB.get(personToAdd).add(numberToAdd);
    	}
    }

	/*
	 * Access an entry in the phone book.
	 */
	public List<PhoneNumber> getNumbers(Person personToLookup) {
		return phoneB.get(personToLookup);
	}

}

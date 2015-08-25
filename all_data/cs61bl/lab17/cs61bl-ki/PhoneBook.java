import java.util.*;

public class PhoneBook {

	TreeMap<Person, List<PhoneNumber>> phoneBook = new TreeMap<Person, List<PhoneNumber>>();
	// TODO Add any instance variables necessary

	/* * 
	 * Adds a person with this name to the phone book and associates 
	 * with the given PhoneNumber.
	 */

	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		// check if null? set up the list and add to it
		if (phoneBook.get(personToAdd) != null) {
			phoneBook.get(personToAdd).add(numberToAdd);
		} else {
			List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
			numbers.add(numberToAdd);
			phoneBook.put(personToAdd, numbers);
		}
	}

	/*
	 * Access an entry in the phone book.
	 */

	public List<PhoneNumber> getNumber(Person personToLookup) {
		return phoneBook.get(personToLookup);
	}

}
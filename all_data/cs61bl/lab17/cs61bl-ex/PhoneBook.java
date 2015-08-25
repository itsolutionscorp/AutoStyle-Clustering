import java.util.*;

public class PhoneBook {
	// TODO Add any instance variables necessary

	/*
	 * Adds a person with this name to the phone book and associates with the
	 * given PhoneNumber.
	 */

	TreeMap<Person, List<PhoneNumber>> phoneBook = new TreeMap<Person, List<PhoneNumber>>();

	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		// TODO Add your own code
		List<PhoneNumber> number;
		if (phoneBook.containsKey(personToAdd)) {
			number = phoneBook.get(personToAdd);
			number.add(numberToAdd);
		} else {
			number = new ArrayList<PhoneNumber>();
			number.add(numberToAdd);			
		}
		phoneBook.put(personToAdd, number);
	}

	/*
	 * Access an entry in the phone book.
	 */
	public List<PhoneNumber> getNumbers(Person personToLookup) {
		// TODO Add your own code
		return phoneBook.get(personToLookup);
	}

}

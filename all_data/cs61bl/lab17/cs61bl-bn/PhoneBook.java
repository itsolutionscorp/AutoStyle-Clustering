import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
	Map<Person, List<PhoneNumber>> phoneBook = new TreeMap<Person, List<PhoneNumber>>();

	/*
	 * Adds a person with this name to the phone book and associates with the
	 * given PhoneNumber.
	 */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		personToAdd.phoneNumbers.add(numberToAdd);
		phoneBook.put(personToAdd, personToAdd.phoneNumbers);
	}

	// /*
	// * Access an entry in the phone book.
	// */
	// public PhoneNumber getNumber(Person personToLookup) {
	// return phoneBook.get(personToLookup).get(0);
	// }

	public List<PhoneNumber> getNumbers(Person personToLookup) {
		return phoneBook.get(personToLookup);
	}

}

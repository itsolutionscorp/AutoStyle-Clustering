import java.util.HashMap;

public class PhoneBook {
	HashMap<Person, PhoneNumber> myBook = new HashMap<Person, PhoneNumber>();

	/*
	 * Adds a person with this name to the phone book and associates with the
	 * given PhoneNumber.
	 */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		// Person person = new Person(personToAdd.toString());
		myBook.put(personToAdd, numberToAdd);
	}

	/*
	 * Access an entry in the phone book.
	 */
	public PhoneNumber getNumber(Person personToLookup) {
		if (personToLookup.hasChangedName())
			return null;
		return myBook.get(personToLookup);
	}

}

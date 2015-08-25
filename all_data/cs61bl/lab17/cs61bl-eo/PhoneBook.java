import java.util.HashMap;

public class PhoneBook {
	// Uses the default constructor that takes no arguments

	// Initializes the instance variable book to be a HashMap
	private HashMap<Person, PhoneNumber> book = new HashMap<Person, PhoneNumber>();

	/*
	 * Adds a person with this name to the phone book and associates with the
	 */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		book.put(personToAdd, numberToAdd);
	}

	/*
	 * Access an entry in the phone book.
	 */
	public PhoneNumber getNumber(Person personToLookup) {
		PhoneNumber num = book.get(personToLookup);
		if (num == null) {
			throw new IllegalArgumentException("The person "
					+ personToLookup.toString()
					+ " was not found in this PhoneBook");
		}
		return num;
	}
}
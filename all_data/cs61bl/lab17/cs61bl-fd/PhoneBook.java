import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PhoneBook {
	// TODO Add any instance variables necessary
	TreeMap<Person, ArrayList<PhoneNumber>> book = new TreeMap<Person, ArrayList<PhoneNumber>>();

	/*
	 * Adds a person with this name to the phone book and associates with the
	 * given PhoneNumber.
	 */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		// TODO Add your own code
		if (book.containsKey(personToAdd)) {
			ArrayList<PhoneNumber> p = book.get(personToAdd);
			p.add(numberToAdd);
		} else {
			ArrayList<PhoneNumber> p = new ArrayList<PhoneNumber>();
			p.add(numberToAdd);
			book.put(personToAdd, p);
		}
	}

	/*
	 * Access an entry in the phone book.
	 */
	public List<PhoneNumber> getNumbers(Person personToLookup) {
		// TODO Add your own code
		return book.get(personToLookup);
	}
}

import java.util.ArrayList;
import java.util.TreeMap;

public class PhoneBook {
	// TODO Add any instance variables necessary

	/*
	 * Adds a person with this name to the phone book and associates with the
	 * given PhoneNumber.
	 */
	TreeMap<Person, ArrayList<PhoneNumber>> book = new TreeMap<Person, ArrayList<PhoneNumber>>();

	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		if (book.containsKey(personToAdd)) {
			book.get(personToAdd).add(numberToAdd);
		} else {
			ArrayList<PhoneNumber> tempList = new ArrayList<PhoneNumber>();
			tempList.add(numberToAdd);
			book.put(personToAdd, tempList);
		}
	}

	/*
	 * Access an entry in the phone book.
	 */
	public ArrayList<PhoneNumber> getNumbers(Person personToLookup) {
		if (personToLookup.hasChangedName() == true) {
			return null;
		}
		return book.get(personToLookup);
	}

}

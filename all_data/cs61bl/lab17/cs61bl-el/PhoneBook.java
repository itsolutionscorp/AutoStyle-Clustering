import java.util.*;

public class PhoneBook {
	// TODO Add any instance variables necessary
	TreeMap<Person, ArrayList<PhoneNumber>> myTable = new TreeMap<Person, ArrayList<PhoneNumber>>();

	// This method should now associate a new number with a person
	// If this person doesn't have an entry, this will be the only number
	// associated with the person.
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		if (myTable.containsKey(personToAdd)) {
			myTable.get(personToAdd).add(numberToAdd);
		} else {
			myTable.put(personToAdd, new ArrayList<PhoneNumber>());
			myTable.get(personToAdd).add(numberToAdd);
		}
	}

	// This method should now return an List of PhoneNumbers
	public List<PhoneNumber> getNumbers(Person personToLookup) {
		return myTable.get(personToLookup);
	}
}

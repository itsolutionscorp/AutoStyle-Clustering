import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private TreeMap<Person, List<PhoneNumber>> book = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		List<PhoneNumber> numArray;
		if (book.containsKey(personToAdd)) {
			numArray = book.get(personToAdd);
			numArray.add(numberToAdd);
			book.put(personToAdd, numArray);
		} else {
			numArray = new ArrayList<PhoneNumber>();
			numArray.add(numberToAdd);
			book.put(personToAdd, numArray);
		}
	}
    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup) {
		List<PhoneNumber> nums = book.get(personToLookup);
		if (nums == null) {
			return null;
		}
		return nums;
	}

}

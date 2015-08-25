import java.util.*;

public class PhoneBook {
	// TODO Add any instance variables necessary
	private TreeMap<Person, List<PhoneNumber>> files = new TreeMap<Person, List<PhoneNumber>>();

	/*
	 * Adds a person with this name to the phone book and associates 
	 * with the given PhoneNumber.
	 */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
		if (files.containsKey(personToAdd)){
				files.get(personToAdd).add(numberToAdd);
		}
		else{
			List<PhoneNumber> multi = new ArrayList<PhoneNumber>();
			multi.add(numberToAdd);
			files.put(personToAdd, multi);
		}
	}

	/*
	 * Access an entry in the phone book. 
	 */
	public List<PhoneNumber> getNumber(Person personToLookup){
		// TODO Add your own code
		if (personToLookup.hasBeenChanged()){
			return null;
		}
		return files.get(personToLookup);
	}

}

import java.util.*;

public class PhoneBook {
	// TODO Add any instance variables necessary
	//private final static double load_factor = 0.7;

	private HashMap<Person, PhoneNumber> HashBaby = new HashMap<Person, PhoneNumber>();
	/*
	 * Adds a person with this name to the phone book and associates 
	 * with the given PhoneNumber.
	 */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd){    	
		HashBaby.put(personToAdd,numberToAdd);
		numberToAdd.setName(personToAdd.toString());
	}

	/*
	 * Access an entry in the phone book. 
	 */
	public PhoneNumber getNumber(Person personToLookup){
		
		for (Person key: HashBaby.keySet()){
			if (key == personToLookup){
				if (HashBaby.get(personToLookup).getName().equals(personToLookup.toString()))
					return HashBaby.get(personToLookup);
			}			
		}
		return null;
	}

}

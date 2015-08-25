import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> map = new TreeMap<Person, List<PhoneNumber>>();

	// This method should now associate a new number with a person
	// If this person doesn't have an entry, this will be the only number associated with the person.
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
		if(map.containsKey(personToAdd)) map.get(personToAdd).add(numberToAdd);
		else{
			List<PhoneNumber> lst = new ArrayList<PhoneNumber>();
			lst.add(numberToAdd);
			map.put(personToAdd, lst);
		}
	}

	// This method should now return an List of PhoneNumbers
	public List<PhoneNumber> getNumbers(Person personToLookup){
		return map.get(personToLookup);
	}

}
    
    



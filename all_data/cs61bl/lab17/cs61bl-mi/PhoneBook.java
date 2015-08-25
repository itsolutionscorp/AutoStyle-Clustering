import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	
	//TreeMap<String, PhoneNumber> map = new TreeMap<String, PhoneNumber>();
//	TreeMap<Person, PhoneNumber> map = new TreeMap<Person, PhoneNumber>();
	TreeMap<Person, List<PhoneNumber>> map = new TreeMap<Person, List<PhoneNumber>>();
	
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	//map.put(personToAdd.getName(), numberToAdd);
    	if (!map.containsKey(personToAdd)) {
    		List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
    		numbers.add(numberToAdd);
    		map.put(personToAdd, numbers);
    	}
    	else {
    		map.get(personToAdd).add(numberToAdd);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	//return map.get(personToLookup.getName());
    	return map.get(personToLookup);
    }

}

import java.util.*;

public class PhoneBook {
	private TreeMap<Person, List<PhoneNumber>> myMap;
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	public PhoneBook() {
		myMap = new TreeMap<Person, List<PhoneNumber>>();
	}
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (myMap.containsKey(personToAdd)) {
    		myMap.get(personToAdd).add(numberToAdd);
    	} else {
    		List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
    		phones.add(numberToAdd);
    		myMap.put(personToAdd, phones);
    	}
    	
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup) {
    	if (myMap.containsKey(personToLookup)) {
    		List<PhoneNumber> t = myMap.get(personToLookup);
    		return t.get(t.size()-1);
    	}
    	return null;
    }
    
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return myMap.get(personToLookup);
    }

}

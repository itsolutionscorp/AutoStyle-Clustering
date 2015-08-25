import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> myMap = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (myMap.containsKey(personToAdd)) {
    		List<PhoneNumber> myList = myMap.get(personToAdd);
    		myList.add(numberToAdd);
    	}
    	else {
    		List<PhoneNumber> myList = new ArrayList<PhoneNumber>();
    		myList.add(numberToAdd);
        	myMap.put(personToAdd, myList);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	// TODO Add your own code
    	if (personToLookup.isChanged()) {
    		return null;
    	}
    	else {
    		List<PhoneNumber> numbers = myMap.get(personToLookup);
    		return numbers;
    	}
    }

}

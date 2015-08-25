import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

	TreeMap<Person, ArrayList<PhoneNumber>> myMap = new TreeMap<Person, ArrayList<PhoneNumber>>();
	
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (! myMap.containsKey(personToAdd)){
    		myMap.put(personToAdd, new ArrayList<PhoneNumber>());
    	}
    	myMap.get(personToAdd).add(numberToAdd);	
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	if (myMap.get(personToLookup) != null){
    		return myMap.get(personToLookup).get(0);
    	}
    	return null;
    }
    
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return myMap.get(personToLookup);
    }

}

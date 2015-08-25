import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

	TreeMap<Person, List<PhoneNumber>> dict = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if(!dict.containsKey(personToAdd)) {
        	dict.put(personToAdd, new ArrayList<PhoneNumber>());	
    	}
    	dict.get(personToAdd).add(numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	return dict.get(personToLookup);
    }

}

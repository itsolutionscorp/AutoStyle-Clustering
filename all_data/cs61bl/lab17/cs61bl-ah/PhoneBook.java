import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<String, List<PhoneNumber>> tm = new TreeMap<String, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (!tm.containsKey(personToAdd.toString())) {
    		List<PhoneNumber> l = new ArrayList<PhoneNumber>();
    		l.add(numberToAdd);
    		tm.put(personToAdd.toString(), l);
    	}
    	else {
    		tm.get(personToAdd).add(numberToAdd);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return tm.get(personToLookup.toString());
    }

}

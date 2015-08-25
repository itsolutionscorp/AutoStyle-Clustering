import java.util.*;

public class PhoneBook {
    TreeMap<String, List<PhoneNumber>> map = new TreeMap<String, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (map.containsKey(personToAdd.toString())) {
    		(map.get(personToAdd.toString())).add(numberToAdd);
    	} else {
    		List<PhoneNumber> l = new ArrayList<PhoneNumber>();
    		l.add(numberToAdd);
    		map.put(personToAdd.toString(), l);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	return map.get(personToLookup.toString());
    }

}

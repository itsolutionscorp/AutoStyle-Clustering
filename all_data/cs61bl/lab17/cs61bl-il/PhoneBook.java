import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	ArrayList<String> addedBefore = new ArrayList<String>();
	private TreeMap<Person, List<PhoneNumber>> phonebook = 
			new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	
    	if (phonebook.containsKey(personToAdd)) {
    		List<PhoneNumber> a = phonebook.get(personToAdd);
    		a.add(numberToAdd);
    		phonebook.put(personToAdd, a);
    	} else {
	    	addedBefore.add(personToAdd.toString());
	    	List<PhoneNumber> b = new ArrayList<PhoneNumber>();
	    	b.add(numberToAdd);
	    	phonebook.put(personToAdd, b);
    }
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	if (!addedBefore.contains(personToLookup.toString())) {
    		return null;
    	}
    	return phonebook.get(personToLookup);
    }

}

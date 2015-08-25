import java.util.*;

public class PhoneBook {

	TreeMap<String, ArrayList<PhoneNumber>> contacts = new TreeMap<String, ArrayList<PhoneNumber>>();
   
	/*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (contacts.containsKey(personToAdd.toString())) {
    		contacts.get(personToAdd.toString()).add(numberToAdd);
    	} else {
    		contacts.put(personToAdd.toString(), new ArrayList<PhoneNumber>(Arrays.asList(numberToAdd)));
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	return contacts.get(personToLookup.toString());
    }

}

import java.util.*;
import java.util.TreeMap;

public class PhoneBook {
    // TODO Add any instance variables necessary
	
	private TreeMap<Person, ArrayList<PhoneNumber>> phoneTable = new TreeMap<Person, ArrayList<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	ArrayList<PhoneNumber> listNumber;
    	if (phoneTable.containsKey(personToAdd)){
    		listNumber = phoneTable.get(personToAdd);
    		listNumber.add(numberToAdd);
    		phoneTable.put(personToAdd, listNumber);
    	} else {
    		listNumber = new ArrayList<PhoneNumber>();
    		listNumber.add(numberToAdd);
    		phoneTable.put(personToAdd, listNumber);
    	}

    }

    /*
     * Access an entry in the phone book. 
     */
    public ArrayList<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	ArrayList<PhoneNumber> num = phoneTable.get(personToLookup);
    	if (num == null){
    		return null;
    	}

    	return num;
    }
}

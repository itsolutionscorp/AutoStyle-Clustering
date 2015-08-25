import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	
	//private ArrayList<Person> keys = new ArrayList<Person>();
	//private ArrayList<PhoneNumber> values = new ArrayList<PhoneNumber>();
	//private HashMap<String, PhoneNumber> table = new HashMap <String, PhoneNumber>();
	private HashMap<Person, PhoneNumber> table = new HashMap<Person, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	//table.put(personToAdd.toString(), numberToAdd);
    	table.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	//return table.get(personToLookup.toString());
    	return table.get(personToLookup);
    }

}

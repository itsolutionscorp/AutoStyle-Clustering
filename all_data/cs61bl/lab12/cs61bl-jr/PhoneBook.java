import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private Person myPerson;
	private PhoneNumber myNumber;
	private HashMap <Person, PhoneNumber> table = new HashMap <Person, PhoneNumber>();
	

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	myPerson = personToAdd;
    	myNumber = numberToAdd;
    	table.put(myPerson,myNumber);
    	
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	myPerson = personToLookup;
    	if (myPerson.returnChangeCount() > 0) {
    		throw new IllegalArgumentException ("Person's name was changed!");
    	}
    	return table.get(myPerson);
    }

}

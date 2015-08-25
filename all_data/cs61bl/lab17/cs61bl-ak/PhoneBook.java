import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

	TreeMap<Person, ArrayList<PhoneNumber>> numbers = new TreeMap<Person, ArrayList<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (numbers.containsKey(personToAdd)) {
    		numbers.get(personToAdd).add(numberToAdd);
    	} else {
    		ArrayList<PhoneNumber> numberList = new ArrayList<PhoneNumber>();
    		numbers.put(personToAdd, numberList);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public ArrayList<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return numbers.get(personToLookup);
    }

}

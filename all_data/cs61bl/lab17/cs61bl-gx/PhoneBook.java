import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person,  List<PhoneNumber>> data;

	public PhoneBook() {
		data = new TreeMap<Person, List<PhoneNumber>>(); 
	}
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	List<PhoneNumber> temp = getNumbers(personToAdd);
    	if (temp == null) {
    		temp = new ArrayList<PhoneNumber>();
    	}
    	temp.add(numberToAdd);
    	
    	data.put(personToAdd, temp);
    }

    // This method should now return an List of PhoneNumbers
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return data.get(personToLookup);
    }
    /*
     * Access an entry in the phone book. 
     */
    /* public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	//return data.get(personToLookup);
    } */

}

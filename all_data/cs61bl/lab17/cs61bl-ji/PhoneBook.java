import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	
	private TreeMap<Person, List<PhoneNumber>> table = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	List<PhoneNumber> nums= getNumber(personToAdd);
    	if (nums!= null) {
    		nums.add(numberToAdd);
    	} else {
    		nums = new ArrayList<PhoneNumber>();
    		nums.add(numberToAdd);
    	} 
    	table.put(personToAdd, nums);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return table.get(personToLookup);
    }

}

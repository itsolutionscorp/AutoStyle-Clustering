import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private TreeMap<Person, List<PhoneNumber>> table; 
	
	public PhoneBook(){
		table = new TreeMap<Person, List<PhoneNumber>>();
	}
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (table.containsKey(personToAdd)){
    		table.get(personToAdd).add(numberToAdd);
    	}
    	List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
    	numbers.add(numberToAdd);
    	table.put(personToAdd, numbers);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	if(!table.containsKey(personToLookup)) {
    		return null; 
    	}
    	return table.get(personToLookup);
    }
}

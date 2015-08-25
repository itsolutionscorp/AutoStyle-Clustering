import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private TreeMap<Person, List<PhoneNumber>> table = new TreeMap <Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if(table.get(personToAdd) == null){
    		List<PhoneNumber> temp = new ArrayList<PhoneNumber>();
    		temp.add(numberToAdd);
    		table.put(personToAdd, temp);
    	}else{
    		List<PhoneNumber> temp2 = table.get(personToAdd);
    		temp2.add(numberToAdd);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return table.get(personToLookup);
    	
    }

}

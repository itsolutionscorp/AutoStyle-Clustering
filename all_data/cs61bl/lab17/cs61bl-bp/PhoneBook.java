import java.util.*;

public class PhoneBook {
    TreeMap<Person, ArrayList<PhoneNumber>> pb = new TreeMap<Person, ArrayList<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (pb.containsKey(personToAdd))
    		pb.get(personToAdd).add(numberToAdd);
    	else {
    		ArrayList<PhoneNumber> num = new ArrayList<PhoneNumber>();
    		num.add(numberToAdd);
    		pb.put(personToAdd, num);
    	}
    		
    }

    /*
     * Access an entry in the phone book. 
     */
    public ArrayList<PhoneNumber> getNumber(Person personToLookup){
    	return pb.get(personToLookup);
    }

}

import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, ArrayList<PhoneNumber>> table = new TreeMap<Person, ArrayList<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	ArrayList<PhoneNumber> temp= table.get(personToAdd);
    	if (temp==null){
    		temp=new ArrayList<PhoneNumber>();
    	}
    	temp.add(numberToAdd);
    	table.put(personToAdd, temp);

    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){

    	return table.get(personToLookup);
    }

}

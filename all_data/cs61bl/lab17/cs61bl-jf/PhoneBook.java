import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	TreeMap<Person, ArrayList<PhoneNumber>> book = new TreeMap<Person, ArrayList<PhoneNumber>>();
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	ArrayList<PhoneNumber> temp = book.get(personToAdd);
    	if(temp != null){
    		temp.add(numberToAdd);
    	}else{
    		temp = new ArrayList<PhoneNumber>();
    		temp.add(numberToAdd);
    	}
    	book.put(personToAdd, temp);
    }

    /*
     * Access an entry in the phone book. 
     */
    public ArrayList<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return book.get(personToLookup);
    }

}

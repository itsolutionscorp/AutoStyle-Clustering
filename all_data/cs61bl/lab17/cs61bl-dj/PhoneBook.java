import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> book;

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    
	public PhoneBook() {
		book = new TreeMap <Person, List<PhoneNumber>>();
	}
	
	
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
		if (book.containsKey(personToAdd)){
			book.get(personToAdd).add(numberToAdd);
		}else{
			LinkedList<PhoneNumber> added = new LinkedList<PhoneNumber>();
			added.add(numberToAdd);
			book.put(personToAdd, added);
		}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	List<PhoneNumber> result; 
    	result = book.get(personToLookup);
    	return result;
    }
    
    

}

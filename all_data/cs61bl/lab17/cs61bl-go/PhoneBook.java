import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	private TreeMap<Person, List<PhoneNumber>> book = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	List<PhoneNumber> numbers;
    	if(book.containsKey(personToAdd)){
    		numbers = book.get(personToAdd);
    		numbers.add(numberToAdd);
    	}
    	else{
    		numbers = new ArrayList<PhoneNumber>();
    		numbers.add(numberToAdd);
    		book.put(personToAdd, numbers);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	List<PhoneNumber> numbers = book.get(personToLookup);
    	if(numbers == null){
    		throw new IllegalArgumentException("The person "+ personToLookup.toString() + " was not found in this PhoneBook");
    	}
    	return numbers;
    }

}

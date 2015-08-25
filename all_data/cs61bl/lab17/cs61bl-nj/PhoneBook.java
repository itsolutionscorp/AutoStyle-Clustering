import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> book = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (book.containsKey(personToAdd)) {
    		book.get(personToAdd).add(numberToAdd);
    		return;
    	}
    	ArrayList<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
    	numbers.add(numberToAdd);
    	book.put(personToAdd, numbers);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return book.get(personToLookup);
    }
    
    public static void main (String[] args) {
    	PhoneBook test =new PhoneBook();
    	Person a = new Person("Ann");
    	PhoneNumber aNum = new PhoneNumber("5108642468");
    	test.addEntry(a, aNum);
    	test.getNumber(a);
    }
}

import java.util.*;

public class PhoneBook {
    TreeMap<Person, ArrayList<PhoneNumber>> book;
    
    public PhoneBook() {
    	book = new TreeMap<Person, ArrayList<PhoneNumber>>();
    }
    // This method should now associate a new number with a person
    // If this person doesn't have an entry, this will be the only number associated with the person.
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (book.get(personToAdd) == null) {
    		book.put(personToAdd, new ArrayList<PhoneNumber>());
    	}
    	book.get(personToAdd).add(numberToAdd);
 	}
    // This method should now return an List of PhoneNumbers
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return book.get(personToLookup);
    }
}

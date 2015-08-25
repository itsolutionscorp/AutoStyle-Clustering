import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber> > book = new TreeMap<Person, List<PhoneNumber> >();
	
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
//    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
//    	// TODO Add your own code
//    	book.put(personToAdd, numberToAdd);
//    }
	
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		if (book.containsKey(personToAdd)) {
			personToAdd.getNumber().add(numberToAdd);
		}
		book.put(personToAdd, personToAdd.getNumber() );
		
	}

    /*
     * Access an entry in the phone book. 
     */
//    public PhoneNumber getNumber(Person personToLookup){
//    	// TODO Add your own code
//    	return book.get(personToLookup);
//    }
    
    public List<PhoneNumber> getNumbers(Person personToLookup) {
    		return personToLookup.getNumber();
    }
}

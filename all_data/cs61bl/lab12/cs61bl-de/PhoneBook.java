import java.util.*;

public class PhoneBook {
    Map<Person, PhoneNumber> book;
    
    public PhoneBook() {
    	book = new HashMap<Person, PhoneNumber>();
    }
     
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	book.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	return book.get(personToLookup);
    }
    
    

}

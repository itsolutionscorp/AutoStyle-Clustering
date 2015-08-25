import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
     TreeMap<Person, LinkedList<PhoneNumber>> myPhoneBook = new TreeMap<Person, LinkedList<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public PhoneBook(){
    	
    }
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	LinkedList<PhoneNumber> numberlist = new LinkedList<PhoneNumber>();
    	numberlist.add(numberToAdd);
    	if (myPhoneBook.get(personToAdd) == null)
    	     myPhoneBook.put(personToAdd,numberlist);
    	else myPhoneBook.get(personToAdd).add(numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public LinkedList<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return myPhoneBook.get(personToLookup);
    	
    }

}

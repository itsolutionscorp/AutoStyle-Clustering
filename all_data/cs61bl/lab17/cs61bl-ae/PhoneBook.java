import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
    TreeMap<Person,List<PhoneNumber>> phoneBook = new TreeMap<Person,List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (!phoneBook.containsKey(personToAdd)){
    	List<PhoneNumber> lst= new ArrayList<PhoneNumber>();
    	lst.add(numberToAdd);
    	phoneBook.put(personToAdd, lst);
    	} else{
    		phoneBook.get(personToAdd).add(numberToAdd);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	if(phoneBook.containsKey(personToLookup)){
    		return phoneBook.get(personToLookup);
    	}else{
    		return null;
    	}
    }

}

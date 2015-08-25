import java.util.*;

public class PhoneBook{
    // TODO Add any instance variables necessary
	TreeMap<Person, ArrayList<PhoneNumber>> entries = new TreeMap<Person, ArrayList<PhoneNumber>>();
	
	
	// This method should now associate a new number with a person
	// If this person doesn't have an entry, this will be the only number associated with the person.
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (!entries.containsKey(personToAdd)) { // meaning we need to create a new ArrayList ourselves
    		ArrayList<PhoneNumber> lol = new ArrayList();
    		lol.add(numberToAdd);
    		entries.put(personToAdd, lol);
    	} else { // meaning there are values 
    		entries.get(personToAdd).add(numberToAdd);
    	}
    }

	 // This method should now return an List of PhoneNumbers
	 public List<PhoneNumber> getNumbers(Person personToLookup){
    	return entries.get(personToLookup);
    }
}

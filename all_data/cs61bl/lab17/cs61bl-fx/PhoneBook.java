import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, ArrayList<PhoneNumber>> myTree = new TreeMap<Person, ArrayList<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (myTree.get(personToAdd) == null) {
    		ArrayList<PhoneNumber> newlist = new ArrayList<PhoneNumber>();
    		newlist.add(numberToAdd);
    		myTree.put(personToAdd, newlist);
    	}
    	else {
    	myTree.get(personToAdd).add(numberToAdd);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return myTree.get(personToLookup);
    }
//    public List<PhoneNumber> getNumber(Person personToLookup){
//    	return myTree.get(personToLookup);
//    }

}

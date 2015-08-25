import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap PhoneBookMap;
	TreeMap<Person, List<PhoneNumber>> pb = new TreeMap<Person, List<PhoneNumber>>();

	
	public PhoneBook() {
		PhoneBookMap = new TreeMap<Person, PhoneNumber>(); 
	}
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
//    	PhoneBookMap.put(personToAdd, numberToAdd);
    	if (!pb.containsKey(personToAdd)) {
            List<PhoneNumber> l = new ArrayList<PhoneNumber>();
            l.add(numberToAdd);
            pb.put(personToAdd, l);
        } else {
            List<PhoneNumber> lst = pb.get(personToAdd);
            lst.add(numberToAdd);
        }
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return pb.get(personToLookup);
    }
    

}

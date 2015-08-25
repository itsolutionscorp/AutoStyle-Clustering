import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
    private TreeMap<Person, List<PhoneNumber>> map = new TreeMap<Person, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
        if (map.containsKey(personToAdd)) {
            List<PhoneNumber> val = map.get(personToAdd);
            val.add(numberToAdd);
        } else {
            List<PhoneNumber> val = new ArrayList<PhoneNumber>();
            val.add(numberToAdd);
            map.put(personToAdd, val);
        }
    }

    /*
     * Access an entry in the phone book.
     */
    // public PhoneNumber getNumber(Person personToLookup){
    // 	// TODO Add your own code
    //     return map.get(personToLookup);
    // }

    public List<PhoneNumber> getNumbers(Person personToLookup) {
        return map.get(personToLookup);
    }

}

import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

    private TreeMap<Person, ArrayList<PhoneNumber>> myNumbers;


    public PhoneBook() {
        myNumbers = new TreeMap<Person, ArrayList<PhoneNumber>>();
    }

    /*
     * Adds a person with this name to the phone book and associates
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
        if (!(myNumbers.containsKey(personToAdd))) {
            myNumbers.put(personToAdd, new ArrayList<PhoneNumber>());
        }
    	myNumbers.get(personToAdd).add(numberToAdd);
    }

    /*
     * Access an entry in the phone book.
     */
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return myNumbers.get(personToLookup);
    }

}

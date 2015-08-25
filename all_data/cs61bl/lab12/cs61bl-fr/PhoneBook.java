import java.util.*;

public class PhoneBook {

    HashMap<Person, PhoneNumber> table;

    public PhoneBook() {
        table = new HashMap <Person, PhoneNumber>();
    }
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
        table.put(personToAdd, numberToAdd);    
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
        if (personToLookup.flagChange()) {
            table.remove(personToLookup);
            personToLookup.resetFlagChange();
        }
        return table.get(personToLookup);
    }

}

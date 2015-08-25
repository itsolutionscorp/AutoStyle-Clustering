import java.util.*;

public class PhoneBook {
    HashMap<Person, PhoneNumber> pb = new HashMap<Person, PhoneNumber>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	pb.put(personToAdd, numberToAdd);
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
        if (!personToLookup.hasNameChanged()) {
            System.out.println(pb.containsKey(personToLookup));
            return pb.get(personToLookup);
        }
        return null;
        
    }

}

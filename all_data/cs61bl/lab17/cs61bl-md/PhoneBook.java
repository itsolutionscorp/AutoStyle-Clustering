import java.util.*;

public class PhoneBook {
    TreeMap<Person,LinkedList<PhoneNumber>> myPhoneMap = new TreeMap<Person,LinkedList<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (myPhoneMap.containsKey(personToAdd)){
    		myPhoneMap.get(personToAdd).add(numberToAdd);
    	} else {
            LinkedList<PhoneNumber> numberList = new LinkedList<PhoneNumber>();
            numberList.add(numberToAdd);
         	myPhoneMap.put(personToAdd,numberList);
    	}


    }

    /*
     * Access an entry in the phone book. 
     */
    
    public LinkedList<PhoneNumber> getNumber(Person personToLookup){
    	if (myPhoneMap.containsKey(personToLookup)){
    		return myPhoneMap.get(personToLookup);
    	}   
    	return null;
    }
    
}

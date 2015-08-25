import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> myPhoneBook=new TreeMap<Person , List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	List<PhoneNumber> temp;
    	if(getNumber(personToAdd)==null){
    		temp=new ArrayList<PhoneNumber>();
    	}else{
    		temp=getNumber(personToAdd);    	
    	}
    	temp.add(numberToAdd);
    	myPhoneBook.put(personToAdd, temp);
    	
    	
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return myPhoneBook.get(personToLookup);
    }

}

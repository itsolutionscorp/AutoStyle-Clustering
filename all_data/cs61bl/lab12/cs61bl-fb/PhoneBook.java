import java.util.*;

public class PhoneBook {
    
		
	HashMap<Person, PhoneNumber> Hashyo = new HashMap <Person, PhoneNumber>();
	
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	
    	Hashyo.put(personToAdd, numberToAdd);
    }

    public PhoneNumber getNumber(Person personToLookup){
    	
    	
    	return Hashyo.get(personToLookup);
    }

}

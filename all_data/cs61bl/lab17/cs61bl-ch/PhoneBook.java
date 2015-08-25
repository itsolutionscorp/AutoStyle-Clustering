import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> myTreeMap;

	
	void PhoneBook(){
		myTreeMap = new TreeMap<Person, List<PhoneNumber>>();
	}
	
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if(!myTreeMap.containsKey(personToAdd)){
    		List<PhoneNumber> phoneNumberList=new ArrayList<PhoneNumber>();
    		phoneNumberList.add(numberToAdd);
    		myTreeMap.put(personToAdd, phoneNumberList);
    	}
    	else{
    		myTreeMap.get(personToAdd).add(numberToAdd);
    	}
    }
    
    // This method should now return an List of PhoneNumbers
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return myTreeMap.get(personToLookup);
    
    }
    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	if(myTreeMap.containsKey(personToLookup)){
    		return myTreeMap.get(personToLookup).get(0);
    	}
    	else{
    		return null;
    	}
    }

}

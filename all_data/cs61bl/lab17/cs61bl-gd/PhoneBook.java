import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
	public TreeMap<Person,ArrayList> record = new TreeMap();

    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if(!record.containsKey(personToAdd)){
    		ArrayList<PhoneNumber> temp = new ArrayList<PhoneNumber>();
    		temp.add(numberToAdd);
    		record.put(personToAdd,temp);
    		
    		
    	}else{
    		((ArrayList)record.get(personToAdd)).add(numberToAdd);
    	}
    	
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	if(!record.containsKey(personToLookup)){return null;}
    	int n= record.get(personToLookup).size()-1;
    	return (PhoneNumber)record.get(personToLookup).get(n);
    }
    public ArrayList<PhoneNumber> getNumbers(Person personToLookup){
    	if(!record.containsKey(personToLookup)){return null;}
    	return record.get(personToLookup);
    }


	

}

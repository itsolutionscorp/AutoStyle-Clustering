import java.util.*;

public class PhoneBook{
    // TODO Add any instance variables necessary
	//private MyTreeMap<Person,PhoneNumber> thisMap = new MyTreeMap<Person, PhoneNumber>();
	private TreeMap<Person,List<PhoneNumber>> thisMap = new TreeMap<Person, List<PhoneNumber>>();
	/*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if(thisMap.containsKey(personToAdd)){
    		List<PhoneNumber> temp = thisMap.get(personToAdd);
    		temp.add(numberToAdd);
    		thisMap.put(personToAdd, temp);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return thisMap.get(personToLookup);
    }

}

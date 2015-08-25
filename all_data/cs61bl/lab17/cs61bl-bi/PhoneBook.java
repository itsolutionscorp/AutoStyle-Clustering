import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	
	TreeMap<Person, ArrayList<PhoneNumber>> myTreeMap = new TreeMap <Person, ArrayList<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
//    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
//    	// TODO Add your own code
//    	myTreeMap.put(personToAdd, numberToAdd);
//    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own codes
    	ArrayList<PhoneNumber> myList = myTreeMap.get(personToLookup);
    	PhoneNumber phoneNumber = null;
    	if (myList != null) {
        	for (PhoneNumber pn: myList) {
        		phoneNumber = pn;
        	}
    	}
    	return phoneNumber;
    }

    
	 // This method should now associate a new number with a person
	 // If this person doesn't have an entry, this will be the only number associated with the person.
	 public void addEntry(Person personToAdd, PhoneNumber numberToAdd){

		 if (getNumbers(personToAdd) == null) {
			 ArrayList<PhoneNumber> myList = new ArrayList<PhoneNumber>();
			 myList.add(numberToAdd);
			 myTreeMap.put(personToAdd, myList);
		 }
		 myTreeMap.get(personToAdd).add(numberToAdd);
		 
	 }
	
	 // This method should now return an List of PhoneNumbers
	 public List<PhoneNumber> getNumbers(Person personToLookup){
		return myTreeMap.get(personToLookup);
	 
	 }
  
   
}


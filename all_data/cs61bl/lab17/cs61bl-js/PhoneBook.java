import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	
	private Person myPerson;
	private PhoneNumber myNumber;
	private List <PhoneNumber> numList;
	private TreeMap <Person, List<PhoneNumber>> table = new TreeMap <Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	myPerson = personToAdd; 
    	myNumber = numberToAdd; 
    	numList = new ArrayList<PhoneNumber>();
    	if (table.containsKey(myPerson)) {
    		numList = table.get(myPerson);
    		numList.add(myNumber);
    		table.put(myPerson, numList);
    	} else {
    		numList.add(myNumber);
    		table.put(myPerson, numList);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumbers (Person personToLookup) {
    	List<PhoneNumber> numberList = table.get(personToLookup);
		if (numberList == null) {
			throw new IllegalArgumentException("Diz nigga not found.");
		}
		return numberList;
	}


    	
//    	myPerson = personToLookup;
//    	if (table.containsKey(myPerson)) {
//    		return table.get(myPerson);
//    	} else {
//    		return null;
//    	}
//    }
    
    public PhoneNumber getNumber(Person personToLookup){
    	myPerson = personToLookup;
    	if (table.containsKey(myPerson)) {
    		return getNumbers(myPerson).get(table.get(myPerson).size()-1);
    	}
    	return null;
    }
}
//
//public void testCanAddAndGet() {
//	PhoneBook myBook = new PhoneBook();
//	Person person1 = new Person("Sally");
//	PhoneNumber num1 = new PhoneNumber("5105551234");
//
//	myBook.addEntry(person1, num1);
//	PhoneNumber numReturned = myBook.getNumber(person1);
//	assertEquals("Stored Number Not Correct", num1, numReturned);
//}
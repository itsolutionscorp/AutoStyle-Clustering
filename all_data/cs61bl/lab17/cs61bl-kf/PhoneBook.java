import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	
	private TreeMap<Person, List<PhoneNumber>> myBook = new TreeMap<Person, List<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	if (myBook.containsKey(personToAdd)) {
    		myBook.get(personToAdd).add(numberToAdd);
    	} else {
    		List<PhoneNumber> numberList = new ArrayList<PhoneNumber>();
    		numberList.add(numberToAdd);
    		myBook.put(personToAdd, numberList);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	// TODO Add your own code
    	return myBook.get(personToLookup);
    }
    
    public static void main(String[] args) {
    	PhoneBook myBook = new PhoneBook();
    	Person person = new Person("Daniel");
    	PhoneNumber number = new PhoneNumber("7078491313");
    	PhoneNumber number2 = new PhoneNumber("1234567890");
    	myBook.addEntry(person, number);
    	myBook.addEntry(person, number2);
    	
    	for (PhoneNumber n : myBook.getNumber(person)) {
    		System.out.println(n);
    	}

    }

}

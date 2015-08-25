import java.util.*;

public class PhoneBook {
    
	TreeMap<Person, List<PhoneNumber>> tm = new TreeMap<Person, List<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	List<PhoneNumber> numberList = tm.get(personToAdd);
    	if (numberList == null) {
    		numberList = new ArrayList<PhoneNumber>();
    		numberList.add(numberToAdd);
    		tm.put(personToAdd, numberList);
    	} else {
    		numberList.add(numberToAdd);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumber(Person personToLookup){
    	if (tm.containsKey(personToLookup)) {
    		return tm.get(personToLookup);
    	} else {
    		System.out.println("This person doesn't exist.");
    		return null;
    	}
    }
    
    public static void main(String[] args) {
    	PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Jane");
		PhoneNumber num1 = new PhoneNumber("5105551234");
		PhoneNumber num2 = new PhoneNumber("5105551235");
		PhoneNumber num3 = new PhoneNumber("5105551236");
		
		myBook.addEntry(person1, num1);
		person1.changeName("Eungie");
		System.out.println((myBook.getNumber(person1)));
		
		// After modify a PhoneBook to hold Multiple Numbers
		myBook.addEntry(person1, num2);
		myBook.addEntry(person1, num3);
		System.out.println((myBook.getNumber(person1)));
    }

}

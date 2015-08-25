import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<String, PhoneNumber> tm = new TreeMap<String, PhoneNumber>();
	TreeMap<String, ArrayList<PhoneNumber>> multi = new TreeMap<String, ArrayList<PhoneNumber>>();
    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	if (tm.containsKey(personToAdd.toString())) {
    		multi.get(personToAdd.toString()).add(numberToAdd);
    		tm.put(personToAdd.toString(), numberToAdd);
    	} else {
    		tm.put(personToAdd.toString(), numberToAdd);
    		ArrayList<PhoneNumber> value = new ArrayList<PhoneNumber>();
    		value.add(numberToAdd);
    		multi.put(personToAdd.toString(), value);
    	}
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
    	return tm.get(personToLookup.toString());
    }
    
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	return multi.get(personToLookup.toString());
    }
    
    
    public static void main(String[] args) {
    	PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Sally");
		PhoneNumber num1 = new PhoneNumber("5105551234");
		PhoneNumber num2 = new PhoneNumber("5105551235");

		myBook.addEntry(person1, num1);
		myBook.addEntry(person1, num2);
		PhoneNumber numReturned = myBook.getNumber(person1);
		System.out.println(numReturned.toString());
    }
}

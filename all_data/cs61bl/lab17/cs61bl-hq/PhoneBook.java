import java.util.*;

public class PhoneBook<T> implements Comparable<T>{
    private TreeMap<Person, ArrayList<PhoneNumber>> book = new TreeMap<Person, ArrayList<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	ArrayList<PhoneNumber> numbers;
    	if (book.containsKey(personToAdd)) {
    		numbers = book.get(personToAdd);
    	} else {
    		numbers = new ArrayList<PhoneNumber>();
    	}
    	numbers.add(numberToAdd);
		book.put(personToAdd, numbers);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumbers(Person personToLookup) {
    	ArrayList<PhoneNumber> num = book.get(personToLookup);
    	if (num != null) {
    		return num;
    	} else {
    		return null;
    	}
    }

	@Override
	public int compareTo(T arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}

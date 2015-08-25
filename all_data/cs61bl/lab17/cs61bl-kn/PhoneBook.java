import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
	TreeMap<Person, List<PhoneNumber>> myPhoneBook = new TreeMap<Person,List<PhoneNumber>>(new myComp());

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
    	List<PhoneNumber> p = myPhoneBook.get(personToAdd);
    	if (p == null) {
    		p = new LinkedList<PhoneNumber>();
    	}
    	p.add(numberToAdd);
    	myPhoneBook.put(personToAdd, p);
    }

    /*
     * Access an entry in the phone book. 
     */
    public List<PhoneNumber> getNumbers(Person personToLookup){
    	// TODO Add your own code
    	return myPhoneBook.get(personToLookup);
    }
}
    
     class myComp implements Comparator<Person>{

		@Override
		public int compare(Person o1, Person o2) {
			return o1.toString().compareTo(o2.toString());
		}

}

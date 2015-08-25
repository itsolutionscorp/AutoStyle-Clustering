import java.util.*;

public class PhoneBook {
	public HashMap<Person, PhoneNumber> book = new HashMap<Person, PhoneNumber>();

	/*
	 * Adds a person with this name to the phone book and associates with the
	 * given PhoneNumber.
	 */
	public void addEntry(Person personToAdd, PhoneNumber numberToAdd) {
		book.put(personToAdd, numberToAdd);
	}

	/*
	 * Access an entry in the phone book.
	 */
	public PhoneNumber getNumber(Person personToLookup) {
		return book.get(personToLookup);
	}
	
	public static void main (String [] args){
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Jane");
		PhoneNumber num1 = new PhoneNumber("5105551234");
		System.out.println(person1.hashCode());
		myBook.addEntry(person1, num1);
		System.out.println(person1.hashCode());
		person1.changeName("Greg");
		System.out.println(person1.hashCode());
		System.out.println(person1.hashCode());
	}

}

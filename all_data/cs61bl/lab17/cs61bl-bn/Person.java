import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {

	private String myName;
	protected List<PhoneNumber> phoneNumbers;

	public Person(String name) {
		this.myName = name;
		phoneNumbers = new ArrayList<PhoneNumber>();
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
	}

	public int hashCode() {
		int result = myName.charAt(0) * 31 + myName.charAt(1);

		return result;
	}

	public int compareTo(Person o) {
		if (hashCode() < o.hashCode()) {
			return -1;
		} else if (hashCode() == o.hashCode()) {
			return 0;
		} else {
			return 1;
		}
	}
}

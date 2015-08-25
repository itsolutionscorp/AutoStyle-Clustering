public class Person implements Comparable<Person> {

	private String myName;

	public Person(String name) {
		this.myName = name;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
	}
	
	// TODO add additional methods
	
	public int compareTo(Person j) {
		Person comparison = (Person) j;
		return myName.compareTo(comparison.myName);
	}
	
	public String getName() {
		return myName;
	}

	
}

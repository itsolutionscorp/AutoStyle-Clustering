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

	@Override
	public int compareTo(Person o) {
		return myName.compareTo(o.myName);
	}
	
	// TODO add additional methods
	
}

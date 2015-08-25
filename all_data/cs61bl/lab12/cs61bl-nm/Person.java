public class Person {

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
	
	public boolean equals(Person otherPerson) {
		return this == otherPerson;
	}
	
	public int hashCode() {
		return (int) myName.hashCode();
	}
	
}

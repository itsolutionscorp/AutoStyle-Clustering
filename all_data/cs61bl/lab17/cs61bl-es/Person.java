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
	@Override
	public int hashCode() {
		return myName.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.toString().equals(o.toString());
	}
	@Override
	public int compareTo(Person other) {
		return this.toString().compareTo(other.toString());
	}
}

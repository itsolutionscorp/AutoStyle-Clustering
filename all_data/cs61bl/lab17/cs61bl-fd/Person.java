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
	public int compareTo(Person p) {
		return myName.compareTo(p.toString());
	}

	@Override
	public boolean equals(Object obj) {
		return (myName.equals(obj.toString()));
	}

	@Override
	public int hashCode() {
		return myName.hashCode();
	}

}

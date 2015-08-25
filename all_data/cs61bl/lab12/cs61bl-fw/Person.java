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
	public int hashCode() {
		return myName.hashCode();
	}
	
	public boolean equals(Object o) {
		return this.myName == ((Person)o).myName;
	}
	
	public boolean equals(Person p) {
		return this.myName.equals(p.myName);
	}

}

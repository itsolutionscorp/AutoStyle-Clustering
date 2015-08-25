public class Person {

	private String myName;

	public Person(String name) {
		this.myName = name;
	}
	public Person (Person p) {
		this.myName = p.myName;
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
	public boolean equals (Object other) {
		if (!(other instanceof Person)) return false;
		Person pOther = (Person)other;
		if (!(myName.equals(pOther.myName))) return false;
		return true;
	}
	
	@Override
	public int hashCode () {
		return myName.hashCode();
	}
	// TODO add additional methods
	
}

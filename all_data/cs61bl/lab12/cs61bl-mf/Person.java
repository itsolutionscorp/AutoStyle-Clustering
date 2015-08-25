public class Person {

	private String myName;
	private boolean hasChangedName;

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
		hasChangedName = true;
	}

	public boolean hasChangedName() {
		return hasChangedName;
	}

}

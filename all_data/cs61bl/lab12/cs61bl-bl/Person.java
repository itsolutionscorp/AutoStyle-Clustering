public class Person {

	private String myName;
	private boolean nameChange;

	public Person(String name) {
		this.myName = name;
		nameChange = false;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
		nameChange = true;
	}
	
	public boolean hasNameChanged() {
		return nameChange;
	}
	
}

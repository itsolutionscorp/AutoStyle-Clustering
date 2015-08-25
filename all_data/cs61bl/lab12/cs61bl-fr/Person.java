public class Person {

	private String myName;
	private boolean flagChange;

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
		flagChange = true;
	}
	
	// TODO add additional methods
	public boolean flagChange() {
		return flagChange;
	}

	public void resetFlagChange() {
		flagChange = false;
	}
	
}

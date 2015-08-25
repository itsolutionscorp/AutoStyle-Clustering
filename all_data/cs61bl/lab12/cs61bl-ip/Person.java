public class Person {

	private String myName;
	private boolean changedName;

	public Person(String name) {
		this.myName = name;
		this.changedName = false;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
		this.changedName = true;
	}
	
	public boolean changedName() {
		return changedName;
	}
}

public class Person {

	private String myName;
	private boolean nameChangedFlag;

	public Person(String name) {
		this.myName = name;
		this.nameChangedFlag = false;
	}
	
	public boolean hasChanged() {
		return nameChangedFlag;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
		this.nameChangedFlag = true;
	}
	
	// TODO add additional methods
	
}

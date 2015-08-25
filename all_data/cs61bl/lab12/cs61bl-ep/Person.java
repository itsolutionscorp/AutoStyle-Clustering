public class Person {

	private String myName;
	private boolean nameHasBeenChanged;
	private boolean addedToBook;

	public Person(String name) {
		this.myName = name;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		if (addedToBook) {
			this.myName = newName;
			nameHasBeenChanged = true;
		} else {
			this.myName = newName;
		}
	}
	
	public void isAdded() {
		addedToBook = true;
	}
	
	public boolean changedName() {
		return nameHasBeenChanged;
	}
	
}

public class Person {

	private String myName;
	private boolean nameChanged;

	public Person(String name) {
		this.myName = name;
		this.nameChanged = false;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
		this.nameChanged = true;
	}
	
	// TODO add additional methods
	public boolean nameStatus(){
		return nameChanged;
	}
	
}

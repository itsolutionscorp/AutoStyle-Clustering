public class Person {

	private String myName;
	private boolean hasChanged = false;

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
	public int hashCode() {
		return this.myName.hashCode();
	}

}

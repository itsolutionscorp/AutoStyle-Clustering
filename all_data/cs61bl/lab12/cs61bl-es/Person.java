public class Person {

	private String myName;
	private boolean changedName;

	public Person(String name) {
		this.myName = name;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = new String(newName);
	}
	
	@Override
	public int hashCode() {
		return myName.hashCode();
	}
	
}

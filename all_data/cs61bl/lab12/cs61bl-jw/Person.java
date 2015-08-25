public class Person {

	private String myName;

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
	public int hashCode() {
		int hashVal = 0;
		for (int i = 0; i < myName.length(); i++) {
			hashVal = (127 * hashVal + myName.charAt(i)) % 16908799;
		}
		return hashVal;
	}

}

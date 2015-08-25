public class Person {

	private String myName;
	private int replaceHash;

	public Person(String name) {
		this.myName = name;
		this.replaceHash = hashCode();
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
		replaceHash++;
	}
	
	// TODO add additional methods
	public int hashCode() {
		return replaceHash;
	}
	
	public boolean equals(Object obj) {
		if (this.hashCode() == obj.hashCode()) {
			return true;
		} return false;
	}
	
}

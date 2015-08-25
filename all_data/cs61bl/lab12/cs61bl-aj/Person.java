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
	
	public boolean equals(Person p) {
		if (this.toString() == p.toString()) {
			return true; 
		}
		return false; 
	}
	
	public int hashCode() {
		return (int) this.myName.charAt(0) + (int) this.myName.charAt(1);
	
	}
	// TODO add additional methods
	
}

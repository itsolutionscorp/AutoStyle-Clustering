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
	
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person p = (Person) obj;
			if (this.myName.equals(p.myName)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public int hashCode() {
		return myName.hashCode();
	}
	
}

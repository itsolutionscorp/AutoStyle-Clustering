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
	@Override
	public int hashCode() {
		return this.myName.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == this.getClass() && this.myName == ((Person) obj).myName) {
			return true;
		} else
			return false;
	}
	
}

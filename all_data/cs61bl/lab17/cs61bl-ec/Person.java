public class Person implements Comparable {

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
	public boolean equals(Object obj) {
		Person guy = (Person) obj;
		return guy.myName.equals(this.myName);
	}
	
	@Override
	public int hashCode() {
		return myName.hashCode();
	}
	
	@Override
	public int compareTo(Object obj) {
		Person guy = (Person) obj;
		return this.myName.compareTo(guy.myName);
	}
}

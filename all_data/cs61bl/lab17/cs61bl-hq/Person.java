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
	
	// ADDED METHOD; Get the hash code of the person
	public int hashCode() {
		return myName.hashCode();
	}
	
	@Override
	public int compareTo(Object o) {
		Person p = (Person) o;
		return this.myName.compareTo(p.myName);
	}
	
	public int compareTo(Person p) {
		return this.myName.compareTo(p.myName);
	}

	@Override
	public boolean equals(Object o) {
		Person p = (Person) o;
		return this.myName.equals(p.myName);
	}
	
	public boolean equals(Person p) {
		return this.myName.equals(p.myName);
	}
	
}
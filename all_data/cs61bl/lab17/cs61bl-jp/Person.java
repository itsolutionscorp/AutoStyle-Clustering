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
	public int compareTo(Object obj2) {
		Person otherPerson = (Person) obj2;
		return this.myName.compareTo(otherPerson.myName);
	}


	public int compareTo(Person person2) {
		return this.myName.compareTo(person2.myName);
	}

	
	public boolean equals(Object obj2) {
		Person otherPerson = (Person) obj2;
		return this.myName.equals(otherPerson.myName);
	}


	public boolean equals(Person person2) {
		return this.myName.equals(person2.myName);
	}

}

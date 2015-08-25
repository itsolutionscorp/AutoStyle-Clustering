public class Person {

	private String myName;
	private int nameChangeCount;

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
		nameChangeCount++;
	}
	
	public boolean equals (Person a) {
		return myName.equals(a.toString());
	}
	
	public int returnChangeCount () {
		return nameChangeCount;
	}
}

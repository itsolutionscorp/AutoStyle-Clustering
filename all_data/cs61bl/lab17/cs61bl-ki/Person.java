@SuppressWarnings("rawtypes")
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

	public boolean equals(Object o) {
		return o instanceof Person && ((Person) o).toString().equals(myName);
	}

	@Override
	public int compareTo(Object o) {
		return myName.compareTo(((Person) o).toString());
	}

}
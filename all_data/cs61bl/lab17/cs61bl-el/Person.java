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

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return myName.compareTo(o.toString());
	}
	// TODO add additional methods

}

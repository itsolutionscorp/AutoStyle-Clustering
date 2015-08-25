

public class Person implements Comparable<Person>{

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
	public boolean equals(Person person) {
		return myName.equals(person);
	}
	
	public int hashCode() {
		return myName.length();
	}
	
	public boolean isEmpty() {
		return (myName.length() == 0);
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return o.myName.compareTo(myName);
	}



}

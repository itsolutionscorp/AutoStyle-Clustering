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
	
	public boolean equals(Object obj)
	{
		return myName.equals(((Person)obj).myName);
	}
	
	public int hashCode()
	{
		return myName.hashCode();
	}
	
}

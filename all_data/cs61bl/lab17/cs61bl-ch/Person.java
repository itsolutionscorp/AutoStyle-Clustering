import java.lang.Comparable.*;
public class Person implements Comparable{

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
	public int compareTo(Object p) {
		// TODO Auto-generated method stub
		return this.myName.compareTo(((Person) p).myName);
	}

	
	
}

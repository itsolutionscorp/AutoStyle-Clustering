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
	@Override
	public int compareTo(Person p) {
		// TODO Auto-generated method stub
		if(myName.compareTo(p.myName)<0)
			return -1;
		else if(myName.compareTo(p.myName)==0)
			return 0;
		else
			return 1;
	}
}

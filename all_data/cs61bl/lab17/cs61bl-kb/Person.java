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


	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		if (this.toString().compareTo(arg0.toString()) == 0) {
			return 0;
		}
		else if (this.toString().compareTo(arg0.toString()) == -1) {
			return -1;
		}
		else if (this.toString().compareTo(arg0.toString()) == 1) {
			return 1;
		}
		return 0;
	}

}

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
	public int compareTo(Object obj) {
		Person other = (Person) obj;
		char[] myArray = myName.toCharArray();
		char[] otherArray = other.toString().toCharArray();
		if (myName.equals(other.toString())) {
			return 0;
		} else if (myArray[0] == otherArray[0]) {
			return myName.substring(1).compareTo(obj.toString().substring(1));			
		} else if (myArray[0] < otherArray[0]) {
			return -1;
		} else {
			return 1;
		}
	}
	
	
}

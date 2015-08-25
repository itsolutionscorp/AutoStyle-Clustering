public class Person implements Comparable{

	private String myName;
    int hashCode;
	public Person(String name) {
		this.myName = name;
		hashCode=myName.hashCode();
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
	public int hashCode(){
		return myName.hashCode();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return myName.compareTo(((Person)o).toString());
	}
    
	
	
}

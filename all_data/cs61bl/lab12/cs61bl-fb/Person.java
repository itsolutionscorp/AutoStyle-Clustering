public class Person {

	private String myName;

	public Person(String name) {
		this.myName = name;
	}

	
	public String toString() {
		return myName;
	}

	
	public void changeName(String newName) {
		this.myName = newName;
	}
	
	public int hashCode(){
		int placeholder = myName.hashCode();
		
		return placeholder;
	}
	
}

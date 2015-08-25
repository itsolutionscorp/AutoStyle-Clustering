public class Person implements Comparable<Person>{

	private String myName;
	private boolean hasChanged = false;

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
		hasChanged = true;
	}
	
	// TODO add additional methods
//	public int hashCode(){
//		return myName.hashCode();
//	}
	
	public int compareTo(Person p){
//		if(this.equals(p)!=true){
//			return -1;
//		}
//		if(this==null){
//			return 0;
//		}
//		if(p.myName.equals(myName)){
//			return 0;
//		}
		return p.myName.compareTo(myName);
	}
	
	protected boolean hasBeenChanged(){
		return hasChanged;
	}
	
}

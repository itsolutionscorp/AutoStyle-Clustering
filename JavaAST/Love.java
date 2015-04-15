
public class Love {
	private String name1;
	private String name2;
	
	public Love(String n1, String n2){
		name1 = n1;
		name2 = n2;
	}
	
	public String pairing(){
		return name1 + name2;
	}
	
	public String encourage(){
		int i = 0;
		if (i==0){
			for (int j = 0; j < i; j++){
				System.out.println("Love!");
			}
		}
		System.out.println(pairing());
		return "You are the best!";
	}
}

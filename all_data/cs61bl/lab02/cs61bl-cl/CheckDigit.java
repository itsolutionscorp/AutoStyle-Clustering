public class CheckDigit {

	public static void main (String [ ] args) {
		int id = 0;
		try {
			id = Integer.parseInt (args[0]);
		} catch (NumberFormatException e) {
			System.out.println ("The argument has to be a sequence of digits.");
			System.exit (1);
		}

		boolean isLegal = true;
		// TODO Your code here
		int idlenght = String.valueOf(id).length();  
		int lastdig= id% 10; 
		int newid= id/10;
		int sum=0;
		while (idlenght>1){
			int a= newid%10;
			sum= sum+a;
			idlenght=idlenght-1;
			newid= newid/10;
		}
		if (lastdig!= sum%10){
			isLegal = false;
		}
		if (isLegal) {
			System.out.println (id + " is legal");
		} else {
			System.out.println (id + " is not legal");
		}
	}
}

		

	

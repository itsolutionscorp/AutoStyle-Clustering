public class AccountTester {

	public static void main(String[] args) {
		Account Kathy;
		Kathy= new Account(500);
		System.out.println("Kathy: " + Kathy.balance());
		
		Account Megan;
		Megan = new Account(100, Kathy);
		System.out.println("Megan: "+Megan.balance());
		
		
		//attempt $50 withdrawal from Megan
		System.out.println();
		System.out.println("$50 withdrawal from Megan");
		Megan.withdraw(50);
		System.out.println("Megan: "+Megan.balance());
		System.out.println("Kathy: "+ Kathy.balance());
		
		//attempt $200 withdrawal from Megan
		System.out.println();
		System.out.println("$200 withdrawal from  Megan");
		Megan.withdraw(200);
		System.out.println("Megan: "+Megan.balance());
		System.out.println("Kathy: "+ Kathy.balance());
		
		//attempt $700 withdrawal from Megan
		System.out.println();
		System.out.println("$700 withdrawal from Megan");
		Megan.withdraw(700);
		
		
		
		
	}

}
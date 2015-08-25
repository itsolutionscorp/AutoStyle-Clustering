public class AccountTester {

	public static void main(String[] args) {
	/*	Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		if (mike.withdraw(200)) {
			System.out.println("This is true");
		} else { 
			System.out.println("This is false");
		}; 
		System.out.println(mike.balance()); 
		Account anotherAccount;
		anotherAccount = new Account(500);
		mike.merge(anotherAccount);
		System.out.println(mike.balance());
		System.out.println(anotherAccount.balance());
	*/	
		
		Account Megan;
		Account Kathy;
		Kathy = new Account(500);
		Megan = new Account(100, Kathy);
		
		//Test 1 withdraw 50
		System.out.println(Megan.balance());
		Megan.withdraw(50);
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance()); 
		//Test 2 withdraw 200
		System.out.println(Megan.balance());
		Megan.withdraw(200);
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance()); 
		
		//Test 3 withdraw 700
		System.out.println(Megan.balance());
		Megan.withdraw(700);
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance());
		
	}

}
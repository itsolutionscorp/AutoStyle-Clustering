public class AccountTester {

	public static void main(String[] args) {
		Account Kathy, Megan, Lauren, mike, Jordan, Pepper;
		mike = new Account(1000);									
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		//Modifying withdrawal behavior test
		System.out.println(mike.withdraw(200));			// when Mike has sufficient funds
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(1000));	   // when Mike doesn't have suff. funds
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(-100));	  // when the withdraw amount is negative
		System.out.println(mike.balance());

		//Merging accounts test
		Jordan = new Account(200);
		Pepper = new Account(300);
		Jordan.merge(Pepper);
		System.out.println(Jordan.balance());
		System.out.println(Pepper.balance());
		
		//Overdraft protection test
		Kathy = new Account(500);						// one-argument constructor of Account
		Megan = new Account(100,Kathy);			// two-argument constructor of Account
		Lauren = new Account(30,Megan);
		Lauren.withdraw(10);							// when Lauren has sufficient funds
		System.out.println(Lauren.balance());
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance());
		Lauren.withdraw(100);				           // when Lauren doesn't have suff. funds but Megan does
		System.out.println(Lauren.balance());
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance());
		Lauren.withdraw(500);						  // when Lauren and Megan don't have suff. funds but Kathy does
		System.out.println(Lauren.balance());
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance());
		Lauren.withdraw(1000);                    // when Lauren, Megan, and Kathy all don't have sufficient funds
		System.out.println(Lauren.balance());
		System.out.println(Megan.balance());
		System.out.println(Kathy.balance());
	}

}
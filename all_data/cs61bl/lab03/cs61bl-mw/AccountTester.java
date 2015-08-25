public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		boolean tester1,tester2;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		tester1 = mike.withdraw(200);
		System.out.println(tester1);
		System.out.println(mike.balance());
		tester2 = mike.withdraw(2000);
		System.out.println(tester2);
		
		//test for merge
		Account anotherAcct = new Account(1000);
		mike.merge(anotherAcct);
		System.out.println(mike.balance());
		
		//test overdraft protection
		Account Kathymom = new Account(1000);
		Account Kathy = new Account(500, Kathymom);
		Account megan = new Account(100,Kathy);
		System.out.println("megan " + megan.balance() + " kathy " + Kathy.balance() + " Kathymom " + Kathymom.balance());
		megan.withdraw(1700);
		System.out.println("megan " + megan.balance() + " kathy " + Kathy.balance() + " Kathymom " + Kathymom.balance());
		
	}

}
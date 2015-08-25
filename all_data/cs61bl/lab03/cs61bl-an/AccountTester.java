public class AccountTester {

	public static void main(String[] args) {
		// basic tests
		Account mike;
		boolean testBoolean = false;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		testBoolean = mike.withdraw(200);
		System.out.println(testBoolean);
		System.out.println(mike.balance());
		testBoolean = mike.withdraw(-100);
		System.out.println(testBoolean);
		System.out.println(mike.balance());
		testBoolean = mike.withdraw(1500);
		System.out.println(testBoolean);
		System.out.println(mike.balance());
		
		//merge
		Account anotherAcct = new Account(5000);
		mike.merge(anotherAcct);
		System.out.println(mike.balance());
		System.out.println(anotherAcct.balance());
		
		//simple parent case
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(150);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		testBoolean = megan.withdraw(700);
		System.out.println(testBoolean);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		
		// recursive case
		Account shelly = new Account(10, megan);
		System.out.println(megan.hasEnough(400));
		shelly.withdraw(400);
		System.out.println(shelly.balance());
		System.out.println(kathy.balance());
		System.out.println(megan.balance());
	
	}

}
public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		if (mike.withdraw(200)){
			System.out.println("Withdraw Success");
		} else {
			System.out.println("Withdraw Failure");
		}
		System.out.println(mike.balance());
		Account Jack = new Account(400);
		System.out.println("before merge:");
		System.out.println("	mike's balance: "+ mike.balance());
		System.out.println("	Jack's balance: "+ Jack.balance());
		mike.merge(Jack);
		System.out.println("after merge:");
		System.out.println("	mike's balance: "+ mike.balance());
		System.out.println("	Jack's balance: "+ Jack.balance());
		
		Account denil = new Account(1000);
		Account kathy = new Account(500, denil);
		Account megan = new Account(100, kathy);//megan's parent is kathy, kathy's parent is denil
		//try withdraw 50 on megan
		if (megan.withdraw(50)) {
			System.out.println("try withdraw 50 on megan");
			System.out.println("Withdraw Success");
			System.out.println("	megan's balance: " + megan.balance());
			System.out.println("	kathy's balance: " + kathy.balance());
			System.out.println("	denil's balance: " + denil.balance());
		} else {
			System.out.println("withdraw Failure");
		}
		
		megan.deposit(50);
		//try withdraw 200 on megan
		if (megan.withdraw(200)) {
			System.out.println("try withdraw 200 on megan");
			System.out.println("	Withdraw Success");
			System.out.println("	megan's balance: " + megan.balance());
			System.out.println("	kathy's balance: " + kathy.balance());
			System.out.println("	denil's balance: " + denil.balance());
		} else {
			System.out.println("	withdraw Failure");
		}
		
		megan.deposit(200);
		//try withdraw 700 on megan
		if (megan.withdraw(700)) {
			System.out.println("try withdraw 700 on megan");
			System.out.println("	Withdraw Success");
			System.out.println("	megan's balance: " + megan.balance());
			System.out.println("	kathy's balance: " + kathy.balance());
			System.out.println("	denil's balance: " + denil.balance());
		} else {
			System.out.println("	withdraw Failure");
		}
	}

}
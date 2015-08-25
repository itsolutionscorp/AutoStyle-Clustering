public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		//Withdraw Tests
		System.out.println("Withdraw tests");
		Account alan;
		alan = new Account(100);
		System.out.println(false == alan.withdraw(1000)); //false
		System.out.println(false == alan.withdraw(-10)); //false
		System.out.println(true == alan.withdraw(50)); //true
		System.out.println(true == alan.withdraw(50)); //true
		//Merge Tests
		System.out.println("Merge Tests");
		Account jay;
		alan = new Account(100);
		jay = new Account(1000);
		alan.merge(jay);
		System.out.println(1100 == alan.balance()); //Balance should be 1100
		System.out.println(0 == jay.balance()); //Balance should be 0
		//Overdraft Protection
		System.out.println("Overdraft protection tests");
		jay = new Account(1000);
		alan = new Account(100, jay);
		alan.withdraw(500);
		System.out.println(0 == alan.balance()); //Balance should be 0
		System.out.println(600 == jay.balance()); //Balance should be 600
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(50 == megan.balance()); //Balance should be 50
		System.out.println(500 == kathy.balance()); //Balance should be 500
		kathy = new Account(500);
		megan = new Account(100, kathy);
		megan.withdraw(200);
		System.out.println(0 == megan.balance()); //Balance should be 0
		System.out.println(400 == kathy.balance()); //Balance should be 400
		kathy = new Account(500);
		megan = new Account(100, kathy);
		System.out.println(false == megan.withdraw(700)); //false
		System.out.println(100 == megan.balance()); //Balance should be 100
		System.out.println(500 == kathy.balance()); //Balance should be 500
		kathy = new Account(500);
		megan = new Account(100, kathy);
		alan = new Account(200, megan);
		alan.withdraw(250);
		System.out.println(0 == alan.balance()); //Balance should be 0
		System.out.println(50 == megan.balance()); //Balance should be 50
		System.out.println(500 == kathy.balance()); //Balance should be 500
		kathy = new Account(500);
		megan = new Account(100, kathy);
		alan = new Account(200, megan);
		alan.withdraw(500);
		System.out.println(0 == alan.balance()); //Balance should be 0
		System.out.println(0 == megan.balance()); //Balance should be 0
		System.out.println(300 == kathy.balance()); //Balance should be 300
		jay = new Account(1000);
		kathy = new Account(500, jay);
		megan = new Account(100, kathy);
		alan = new Account(200, megan);
		alan.withdraw(1000);
		System.out.println(0 == alan.balance()); //Balance should be 0
		System.out.println(0 == megan.balance()); //Balance should be 0
		System.out.println(0 == kathy.balance()); //Balance should be 0
		System.out.println(800 == jay.balance()); //Balance should be 800
		jay = new Account(1000);
		kathy = new Account(500, jay);
		megan = new Account(100, kathy);
		alan = new Account(200, megan);
		alan.withdraw(2000);
		System.out.println(200 == alan.balance()); //Balance should be 200
		System.out.println(100 == megan.balance()); //Balance should be 100
		System.out.println(500 == kathy.balance()); //Balance should be 500
		System.out.println(1000 == jay.balance()); //Balance should be 1000
	}

}
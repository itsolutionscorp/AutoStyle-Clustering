public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		if (mike.withdraw(100000)) {
			System.out.println("ya");
		}
		else {System.out.println("no");
		}
		Account bob = new Account(2000);
		Account steve = new Account(1000);
		steve.merge(bob);
		System.out.println("this should be 3000: " + steve.balance());
		System.out.println("this should be 0: " + bob.balance());
		
		Account kathy = new Account(500);
		Account megan = new Account(100, kathy);
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		
		Account kathy2 = new Account(500);
		Account megan2 = new Account(100, kathy2);
		megan2.withdraw(200);
		System.out.println(megan2.balance());
		System.out.println(kathy2.balance());
		
		Account kathy3 = new Account(500);
		Account megan3 = new Account(100, kathy3);
		megan3.withdraw(700);
		System.out.println(megan3.balance());
		System.out.println(kathy3.balance());
		
		Account grandparent = new Account(1000);
		Account kathy4 = new Account(500, grandparent);
		Account megan4 = new Account(100, kathy4);
		megan4.withdraw(700);
		System.out.println(megan4.balance());
		System.out.println(kathy4.balance());
		System.out.println(grandparent.balance());
		
		Account grandparent2 = new Account(1);
		Account kathy5 = new Account(500, grandparent2);
		Account megan5 = new Account(100, kathy5);
		megan5.withdraw(700);
		System.out.println(megan5.balance());
		System.out.println(kathy5.balance());
		System.out.println(grandparent2.balance());
		
		
		
	}

}
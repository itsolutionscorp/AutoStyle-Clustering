public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(-100);
		System.out.println(mike.balance());
		mike.withdraw(900);
		System.out.println(mike.balance());
		mike.withdraw(100);
		System.out.println(mike.balance());
		Account ike;
		ike = new Account(1000);
		mike.merge(ike);
		System.out.println(mike.balance());
		System.out.println(ike.balance());
		Account kathy = new Account (500);
		Account megan = new Account(100, kathy); 
		megan.withdraw(50);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(200);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		megan.withdraw(1000);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		Account george = new Account(1000);
		kathy.myparentAccount = george;
		megan.withdraw(1000);
		System.out.println(megan.balance());
		System.out.println(kathy.balance());
		System.out.println(george.balance());
		System.out.println("-----------");
		Account bob = new Account(100);
		Account cat = new Account (25, bob);
		Account meg = new Account(25, cat);
		meg.withdraw(100);
		System.out.println(bob.balance());
		System.out.println(cat.balance());
		System.out.println(meg.balance());
		
		
		
	}

}
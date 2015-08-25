public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		
		System.out.println(mike.withdraw(-100));
		System.out.println(mike.withdraw(100));
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(2000));
		System.out.println(mike.balance());
		
		System.out.println("Testing account merge");
		Account mike2;
		mike2 = new Account(10);
		mike.merge(mike2);
		System.out.println(mike2.balance());
		System.out.println(mike.balance());
		
		System.out.println("Testing parent withdrawal");
		Account megan;
		Account kathy;
		kathy = new Account(500);
		megan = new Account(100, kathy);
		System.out.println("megan's balance: " + megan.balance());
		System.out.println("parent's balance: " + kathy.balance());
	
		System.out.println(megan.withdraw(50));
		System.out.println("megan's balance: " + megan.balance());
		System.out.println("parent's balance: " + kathy.balance());
		
		megan.deposit(50);
		System.out.println("megan's balance: " + megan.balance());
		System.out.println("parent's balance: " + kathy.balance());
		
		System.out.println(megan.withdraw(200));
		System.out.println("megan's balance: " + megan.balance());
		System.out.println("parent's balance: " + kathy.balance());
		
		System.out.println(megan.withdraw(700));
		System.out.println("megan's balance: " + megan.balance());
		System.out.println("parent's balance: " + kathy.balance());
	}

}
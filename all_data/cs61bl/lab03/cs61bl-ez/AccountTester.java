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
		mike.withdraw(2000);
		System.out.println(mike.balance());
		Account sam;
		sam = new Account(0);
		System.out.println("Sam's original balance" + sam.balance());
		sam.merge(mike);
		System.out.println("Sam's merged balance is " + sam.balance());
		System.out.println("Mike's merged balance is " + mike.balance());
		
		
		Account bigDaddy = new Account(20000);
		Account kathy = new Account(500,bigDaddy);
		Account megan = new Account(100,kathy);
		
		System.out.println("megan's balance " + megan.balance());
		System.out.println("kathy's balance" + kathy.balance());
		System.out.println("bigDaddy's balance " + bigDaddy.balance());
		
		
		megan.withdraw(200);
		System.out.println("megan withdraws 200");
		System.out.println("megan's balance " + megan.balance());
		System.out.println("kathy's balance " + kathy.balance());
		System.out.println("bigDaddy's balance " + bigDaddy.balance());
		
		megan.withdraw(1500);
		System.out.println("megan withdraws 1500");
		System.out.println("megan's balance " + megan.balance());
		System.out.println("kathy's balance " + kathy.balance());
		System.out.println("bigDaddy's balance " + bigDaddy.balance());
		
		
	}

}
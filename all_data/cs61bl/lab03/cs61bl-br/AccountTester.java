public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		if (mike.withdraw(200))
			System.out.println("balance after withdrawal: " + mike.balance());
		
		
		Account doshi = new Account(666);
		System.out.println(doshi.balance());
		mike.merge(doshi);
		System.out.println(mike.balance());
		
		
		
		Account GrandDaddy = new Account(10000);
		
		Account BigDaddy = new Account(5000,GrandDaddy);
		
		Account alex = new Account(1000,BigDaddy);
		
		alex.withdraw(15489);
		System.out.println(alex.balance() + " and " + BigDaddy.balance() + " and " + GrandDaddy.balance() );
		
		
		
		
	}

}
public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(200)== true);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(-100)== false);
		System.out.println(mike.balance());
		System.out.println(mike.withdraw(1000)== false);
		System.out.println(mike.balance());
		
		Account dad = new Account(1000);
		Account mum = new Account(1000);
		dad.merge(mum);
		
		System.out.println(dad.balance() == 2000);
		System.out.println(mum.balance() == 0);
		Account ob = new Account(2000);
		Account kathy = new Account(1000, ob);
		Account megan = new Account (200, kathy);
		megan.withdraw(1300);
		System.out.println(megan.balance() == 0);
		System.out.println(kathy.balance() == 0);
		System.out.println(ob.balance() == 1900);
		System.out.println("mouse".substring(1));
		
		

		
	}

}
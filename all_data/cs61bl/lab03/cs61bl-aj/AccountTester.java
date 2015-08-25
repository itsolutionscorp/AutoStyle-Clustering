public class AccountTester {

	public static void main(String[] args) {
/*		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		mike.withdraw(10000); //to test the return feature of withdraw
	*/	
		Account albert = new Account(300);
	//	mike.merge(albert);
	//	System.out.println(albert.balance());
	//	System.out.println(mike.balance());
		
		albert.deposit(200);
		Account bazz = new Account(100, albert);
		
		bazz.withdraw(50);
		System.out.println(albert.balance());
		System.out.println(bazz.balance());
		bazz.deposit(50);
		bazz.withdraw(200);
		System.out.println(albert.balance());
		System.out.println(bazz.balance());
		albert.deposit(100);
		bazz.deposit(100);
		bazz.withdraw(700);
		System.out.println(albert.balance());
		System.out.println(bazz.balance());
	}

}
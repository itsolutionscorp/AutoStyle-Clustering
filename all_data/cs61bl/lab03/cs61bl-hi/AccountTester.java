public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		
		mike.withdraw(100000); //test the insufficient funds part
		
		//test the boolean aspect of withdraw
		if(mike.withdraw(100000)) // test the false boolean aspect
		{
			System.out.print("fail");
		}
		else if (mike.withdraw(20)) // test the true
		{
			System.out.print("success");
		}
		else
		{
			System.out.print("both fail");
		}
		
		//testing merge
		Account joe;
		joe = new Account (1000);
		mike.merge(joe);
		
		//testing parentAccount
		Account grandad = new Account (1000);
		Account dad = new Account(100,grandad);
		Account kid = new Account (700, dad);
		kid.withdraw(1000);
	}

}
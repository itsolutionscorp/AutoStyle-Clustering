public class AccountTester {

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		
		Account kevin;
		kevin = new Account(500,mike);
	
		
		System.out.println(kevin.balance());
		System.out.println(mike.balance());
		
		mike.withdraw(1000);
		System.out.println(kevin.balance());
		System.out.println(mike.balance());
		
		
		
	}

}
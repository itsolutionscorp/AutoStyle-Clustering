public class AccountTester {

	public static void main(String[] args) {
		//starting tests
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());
		


		//Tests for modified withdraw
		Account emily = new Account(2000);
		Account jake = new Account(1000, emily);
		Account sarah = new Account(100, jake);
	
		sarah.withdraw(200);
		System.out.println(sarah.balance());
		System.out.println(jake.balance());
		System.out.println(emily.balance());
		
		sarah.withdraw(1300);
		System.out.println(sarah.balance());
		System.out.println(jake.balance());
		System.out.println(emily.balance());
		
		sarah.withdraw(1700);
		System.out.println(sarah.balance());
		System.out.println(jake.balance());
		System.out.println(emily.balance());
		
		
	}

}
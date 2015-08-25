public class AccountTester {

	public static void main(String[] args) {
		Account megan = new Account (500);
		megan.withdraw(300); 
		megan.withdraw(300); 
		
		Account kathy = new Account (400); 
		kathy.parentAccount = new Account (3000); 
		kathy.parentAccount.parentAccount = new Account (20000); 
		kathy.withdraw(15000); 
		
		Account joey = new Account (1500); 
		joey.withdraw(1000); 
		joey.withdraw(1000);
		
		Account rachel = new Account (300); 
		joey.merge(rachel);
		rachel.balance();
		joey.balance();
		

	}

}

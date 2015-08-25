public class AccountTester {
	/*All tests have expected output*/

	public static void main(String[] args) {
		Account mike;
		mike = new Account(1000);
		System.out.println(mike.balance());
		mike.deposit(100);
		System.out.println(mike.balance());
		mike.withdraw(200);
		System.out.println(mike.balance());

		//Test insufficient funds// 
		Account john; 
		john = new Account(100); 
		john.withdraw(200); 

		//Test account linking with parent accounts// 
		Account anuraag; 
		Account kathleen; 
		anuraag = new Account(500, john); 
		kathleen = new Account(1000, anuraag); 
		kathleen.withdraw(1400); 
		System.out.println(anuraag.balance()); 
		System.out.println(kathleen.balance()); 

		//Test account linking when not all parent accounts are accessed// 
		Account sarah; 
		Account dave; 
		Account tracy; 
		Account josh; 
		josh = new Account(1000); 
		tracy = new Account(500, josh); 
		dave = new Account(200, tracy); 
		sarah = new Account(100, dave); 
		sarah.withdraw(700); 
		System.out.println(sarah.balance()); 
		System.out.println(dave.balance()); 
		System.out.println(tracy.balance()); 
		System.out.println(josh.balance()); 

		//Test merge method// 
		Account abhinav; 
		abhinav = new Account(1000); 
		abhinav.merge(josh); 
		System.out.println(abhinav.balance()); 
		System.out.println(josh.balance()); 

	}

}
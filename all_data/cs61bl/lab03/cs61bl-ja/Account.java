/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this.myBalance = balance;
		 
	}
	
	
	public Account (int balance , String name){
		this.parentAccount = new Account (500); 
		parentAccount.myBalance = balance;
	}
	
	
	
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.myBalance = this.myBalance + amount;
		}
	}
		

	public boolean withdraw (int amount) {
		
		if (amount < 0){
			System.out.println("Can't withdraw negative amount"); 
			return false;
		}

		if (amount <= this.myBalance ){
			System.out.println("Balance before withdraw  " + this.myBalance); 
			//withdrawPossible = true;
			this.myBalance = this.myBalance - amount ;			
			System.out.println("Balance after withdraw  " + this.myBalance);				
			return true; 
		}
		else if (parentAccount == null){
			//withdrawPossible = false;
			System.out.println("sorry, there is No overdraft protection");
			System.out.println("Insufficent Funds :( ");
			
			return false;
		}
		else {
			amount = amount - this.myBalance;
			if (parentAccount.myBalance > amount) {
				amount = 0; 
				parentAccount.myBalance = parentAccount.myBalance - amount ;
				System.out.println("over draft protection : possible!");
				System.out.println("current parentAccount balance "  + parentAccount); 
				return true;
			}else {
				amount = amount - parentAccount.myBalance ; 
				parentAccount.myBalance = 0; 
				return withdraw(amount);
			}
		}
		
	}


	public void  merge (Account anotherAcct){
		this.myBalance = this.myBalance + anotherAcct.myBalance; 
		anotherAcct.myBalance = 0; 
	}


	public int balance() {
		System.out.println("Current Balance after withdraw " + this.myBalance); 
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	public Account parentAccount; 

}

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
		this.parentAccount = null;
	}
	
	public Account(int balance, Account parent) {
		this.myBalance = balance;
		this.parentAccount = parent;
	}

	/**
	 * Add the given amount to the account.
	 */
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.myBalance = this.myBalance + amount;
		}
	}

	/**
	 * Subtract the given amount from the account if possible. If the amount
	 * would leave a negative balance, print an error message and leave the
	 * balance unchanged.
	 */
	public boolean withdraw(int amount) {
		
		
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return false;
		}
			
		if (this.myBalance - amount >= 0) { 
			this.myBalance -= amount; 
			return true; 
		}
		
		if (this.parentAccount != null) {
			System.out.println("Talk to yo mamma");
			 
			if (this.parentAccount.withdraw(Math.abs((this.myBalance - amount)))){
				this.myBalance = 0;			
				return true; 
				}
		}
			
		return false;	
			
			
	
		
	}

	public void merge(Account anotherAcct) {
		
		this.myBalance += anotherAcct.myBalance;
		anotherAcct.myBalance = 0;
		
	}
	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	private Account parentAccount;

}

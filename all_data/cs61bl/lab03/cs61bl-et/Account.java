/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	Account parentAccount;
	
	
	
	
	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this.myBalance = balance;
		parentAccount = null;
		
	}
	
	public Account(int balance, Account parentAccount){
		this.myBalance = balance;
		this.parentAccount = parentAccount;
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
			
		else if (this.myBalance < amount) {
			if (parentAccount == null) {
			System.out.println("Insufficient funds");
			return false;}
			
			else if (parentAccount.myBalance < amount) {
				System.out.println("Insufficient funds");
				return false; }
			
			else if (parentAccount.myBalance > amount) {
				parentAccount.myBalance = parentAccount.myBalance - amount;
				return true;
			}
		}
		else {
			this.myBalance = this.myBalance - amount;
			return true;
		}
		return true;
		
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	
	/**
	 * Merge account "anotherAcct" into this one by
	 * removing all the money from anotherAcct and
	 * adding that amount to this one.
	 */
	public void merge (Account anotherAcct) {
	   this.myBalance = this.myBalance + anotherAcct.balance();}

}

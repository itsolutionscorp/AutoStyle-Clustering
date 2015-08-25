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
	
	/**
	 * Initialize with parent Account
	 */

	public Account(int balance, Account parent) {
		this.myBalance = balance;
		this.parentAccount = parent;
	}
	/**
	 * Add the given amount to the account.
	 */
	public boolean deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
			return false;
		} else {
			this.myBalance = this.myBalance + amount;
			return true;
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
		} else if (this.myBalance < amount && this.parentAccount == null) {
			System.out.println("Insufficient funds");
			return false;
		} else if (this.myBalance < amount && parentAccount.withdraw(amount - this.myBalance)) {
			this.myBalance = 0;
			return true;
		} else if (this.myBalance >= amount) {
			this.myBalance = this.myBalance - amount;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	Account parentAccount;
	
	

/**
 * Merge account "anotherAcct" into this one by
 * removing all the money from anotherAcct and
 * adding that amount to this one.
 */
public void merge (Account anotherAcct) {
    // TODO Put your own code here
	this.myBalance = this.myBalance + anotherAcct.myBalance;
	anotherAcct.myBalance = 0;
	}
}

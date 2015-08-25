/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance and no parent account.
	 */
	public Account(int balance) {
		this.myBalance = balance;
		this.myParent = null;
	}
	
	/**
	 * Initialize an account with a given balance and a given parent account.
	 */
	public Account(int balance, Account parent) {
		this.myBalance = balance;
		this.myParent = parent;
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
		} else if (this.myBalance < amount) {
			if (this.myParent == null) {
				System.out.println("Insufficient funds");
			} else {
				int difference = amount - this.myBalance;
				this.myParent.withdraw(difference);
				this.withdraw(this.myBalance);
				return true;
			}
			return false;
		} else {
			this.myBalance = this.myBalance - amount;
			return true;
		}
	}

	/**
	 * Merge account "anotherAcct" into this one by
	 * removing all the money from anotherAcct and
	 * adding that amount to this one.
	 */
	public void merge (Account anotherAcct) {
		int balance = anotherAcct.balance();
	    anotherAcct.withdraw(balance);
	    this.deposit(balance);
	}
	
	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	private Account myParent;

}

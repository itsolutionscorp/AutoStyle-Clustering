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
		this.myParentAccount = null;
	}
	
	public Account (int initBalance, Account parentAccount){
		this.myBalance = initBalance;
		this.myParentAccount = parentAccount;
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
	 * would leave a negative balance, return false.  Otherwise, return
	 * true.
	 */
	public boolean withdraw(int amount) {
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return false;
		} 
		else if (this.myBalance < amount) {
			if (this.myParentAccount == null){
				System.out.println("No overdraft protection.  Insufficient funds.");
				return false;
			}
			else if (this.myParentAccount.myBalance > amount){
				System.out.println("Overdraft protection");
				this.myParentAccount.withdraw(amount - myBalance);
				this.withdraw(myBalance);
				return true;
			}
			else {
				System.out.println("Overdraft protection.  Insufficient funds.");
				return false;
			}
		} 
		else {
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
	    this.myBalance += anotherAcct.myBalance;
	}
	
	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	private Account myParentAccount;

}
